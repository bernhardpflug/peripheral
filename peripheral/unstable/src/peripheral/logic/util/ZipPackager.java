/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.logic.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import peripheral.designer.preview.PreviewDialog;
import peripheral.logic.DisplayConfiguration;
import peripheral.logic.datatype.Directory;
import peripheral.logic.datatype.RestructuredFile;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.symboladapter.SymbolAdapter;
import peripheral.logic.value.ConstValue;
import peripheral.logic.value.Value;

/**
 *
 * @author Andi
 */
public class ZipPackager {

    private static final String RESOURCE_PATH= "resources/";

    /**
     * Searches for all files and directories in display configuration,
     * calculates new paths for save in zip files and renames
     * correlating files/dir
     * @return
     */
    public static ArrayList<RestructuredFile> getRestructuredFiles() {
        ArrayList<RestructuredFile> result = new ArrayList<RestructuredFile>();

        DisplayConfiguration dc = DisplayConfiguration.getInstance();

        //map that holds all files sorted by their correlating file names
        Map<String,List<File>> fileMap = new HashMap<String,List<File>>();
        //same for dirs
        Map<String,List<File>> dirMap = new HashMap<String,List<File>>();

        /***********************************************
        *********** SEARCH for all files in DC *********
        ************************************************/

        //BACKGROUNDIMAGE
        addToMap(fileMap,dc.getBackgroundImageFile());

        for (SymbolAdapter adapter : dc.getAdapter()) {

            //SYMBOLS
            //search for files in symbols and add them to the map
            for (PositioningTool pt : adapter.getTool().getElements()) {
                for (Symbol symbol : pt.getSymbols()) {

                    //add every file of each symbol
                    addToMap(fileMap,symbol.getFile());

                    //if there exists a second file in symbol add it too
                    if (symbol.getSecondFile()!= null) {
                        addToMap(fileMap,symbol.getSecondFile());
                    }
                }
            }

            //FILES AND DIRECTORIES IN VARPOOL
            for (Value value : adapter.getVarpool().values()) {

                //check all constValue whether they include a file or a directory
                if (value instanceof ConstValue) {

                    Object val = value.getValue();

                    if (val.getClass().equals(File.class)) {

                        addToMap(fileMap,(File)val);
                    }
                    else if (val.getClass().equals(Directory.class)) {
                        addToMap(dirMap,(Directory)val);
                    }
                }
            }
        }

        /****************************************************
        ********** rename all correlating files/dirs ********
        *** and save their new path in restructured image ***
        *****************************************************/

        //go through all items in map and create new restructed file
        //with old file in it and additional new path with renamed
        //filename for zip file
        for (String key : fileMap.keySet()) {
            List<File> corFilenames = fileMap.get(key);

            for (int i=0; i < corFilenames.size(); i++) {
                RestructuredFile restructuredFile = new RestructuredFile(corFilenames.get(i));
                restructuredFile.setNewPath(getNewFilePath(corFilenames.get(i),i));
                result.add(restructuredFile);
            }
        }

        //for all directories create extended path with folder name and add
        //all files in this dir to restructured files
        for (String key : dirMap.keySet()) {
            List<File> dirs = dirMap.get(key);

            for (int i=0; i < dirs.size(); i++) {
                //folder
                File folder = dirs.get(i);
                //get new name of folder
                String folderName = getNewFilePath(folder,i)+"/";

                //for each file in this folder create restructured file
                if (folder.isDirectory()) {

                    //get all image files in folder
                    File [] files = folder.listFiles(new FileFilter() {
                        public boolean accept(File pathname) {
                            return PreviewDialog.isExtentionSupported(pathname);
                        }
                    });

                    //add every file in this folder with new foldername and original file name
                    for (int j=0; j < files.length; j++) {
                        RestructuredFile restructuredFile = new RestructuredFile(files[j]);
                        restructuredFile.setNewPath(folderName+files[j].getName());
                        result.add(restructuredFile);
                    }
                }
                else {
                    System.err.println("ZipPackager:getRestructuredFiles: "+folder.getAbsolutePath()+
                            " was set as directory in ConstValue but is no folder on disk");
                }
            }
        }

        return result;
    }

    /**
     * internal method to add file/dir to map at position of its filename
     * and create new arraylist if filename doen't exist in list
     * @param reMap
     * @param file
     */
    private static void addToMap(Map<String,List<File>> reMap,File file) {

        //if map already contains list for this filename add file to this list
        if (reMap.containsKey(file.getName())) {
            reMap.get(file.getName()).add(file);
        }
        //else create new entry in map with new arraylist
        else {
            ArrayList<File> filesList = new ArrayList<File>();
            filesList.add(file);
            reMap.put(file.getName(), filesList);
        }
    }

    /**
     * Generates a new filepath for file according to index in correlation list
     * this method adds the index like this:
     * file.ext => resources/file1.ext
     * file => resources/file1
     * dir => resources/dir1
     * @param file
     * @param oldFile
     * @param index
     */
    private static String getNewFilePath(File oldFile, int index) {

        String name = oldFile.getName();

        if (index ==0) {
            return RESOURCE_PATH+name;
        }
        //if more than one file exists add index at end of filename
        else {
            int extensionIndex = name.lastIndexOf(".");

            if (extensionIndex == -1) {
                extensionIndex = name.length();
            }
            return RESOURCE_PATH + name.substring(0, extensionIndex) + index + name.substring(extensionIndex, name.length());
        }
        
    }

    /**
     *Method to set all properties including files to relative paths
     * @param files
     */
    public static void setRestructuredFilePaths(ArrayList<RestructuredFile> files) {

        for (RestructuredFile file : files) {

            DisplayConfiguration dc = DisplayConfiguration.getInstance();

            //BACKGROUNDIMAGE
            if (dc.getBackgroundImageFile().equals(file.getOldFile())) {
                dc.setBackgroundImageFile(new File(file.getNewPath()));
                System.out.println("backgroundimage from "+file.getOldFile().getAbsolutePath()+" to "+file.getNewPath());
            }

            for (SymbolAdapter adapter : dc.getAdapter()) {

                //SYMBOLS
                for (PositioningTool pt : adapter.getTool().getElements()) {
                    for (Symbol symbol : pt.getSymbols()) {

                        if (symbol.getFile().equals(file.getOldFile())) {
                            symbol.setFile(new File(file.getNewPath()));
                            System.out.println("symbolfile from "+file.getOldFile().getAbsolutePath()+" to "+file.getNewPath());
                        }

                        if (symbol.getSecondFile()!= null && symbol.getSecondFile().equals(file.getOldFile())) {
                            symbol.setSecondFile(new File(file.getNewPath()));
                            System.out.println("second symbolfile from "+file.getOldFile().getAbsolutePath()+" to "+file.getNewPath());
                        }
                    }
                }

                //VARPOOL
                for (Value value : adapter.getVarpool().values()) {

                    //check all constValue whether they include a file or a directory
                    if (value instanceof ConstValue) {

                        Object val = value.getValue();

                        if (val.getClass().equals(File.class)) {

                            File valFile = (File)val;

                            if (valFile.equals(file.getOldFile())) {
                                ((ConstValue)value).setValue(new File(file.getNewPath()));
                                System.out.println("file constvalue from "+file.getOldFile().getAbsolutePath()+" to "+file.getNewPath());
                            }

                        } else if (val.getClass().equals(Directory.class)) {

                            Directory dir = (Directory)val;

                            if (dir.equals(file.getOldFile())) {
                                ((ConstValue)value).setValue(new Directory(file.getNewPath()));
                                System.out.println("directory constvalue from "+file.getOldFile().getAbsolutePath()+" to "+file.getNewPath());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * resets all entries in backgroundimage, symbols and varpool from newPath to
     * oldfile of restructured file
     * @param files
     */
    public static void resetRestructuredFilePaths(ArrayList<RestructuredFile> files) {

        for (RestructuredFile file : files) {

            DisplayConfiguration dc = DisplayConfiguration.getInstance();

            //BACKGROUNDIMAGE
            if (dc.getBackgroundImageFile().equals(new File(file.getNewPath()))) {
                dc.setBackgroundImageFile(file.getOldFile());
                System.out.println("reset backgroundimage from "+file.getNewPath()+" to "+file.getOldFile().getAbsolutePath());
            }

            for (SymbolAdapter adapter : dc.getAdapter()) {

                //SYMBOLS
                for (PositioningTool pt : adapter.getTool().getElements()) {
                    for (Symbol symbol : pt.getSymbols()) {

                        if (symbol.getFile().equals(new File(file.getNewPath()))) {
                            symbol.setFile(file.getOldFile());
                            System.out.println("reset symbol file from "+file.getNewPath()+" to "+file.getOldFile().getAbsolutePath());
                        }

                        if (symbol.getSecondFile()!= null && symbol.getSecondFile().equals(new File(file.getNewPath()))) {
                            symbol.setSecondFile(file.getOldFile());
                            System.out.println("reset symbol second file from "+file.getNewPath()+" to "+file.getOldFile().getAbsolutePath());
                        }
                    }
                }

                //VARPOOL
                for (Value value : adapter.getVarpool().values()) {

                    //check all constValue whether they include a file or a directory
                    if (value instanceof ConstValue) {

                        Object val = value.getValue();

                        if (val.getClass().equals(File.class)) {

                            File valFile = (File)val;

                            if (valFile.equals(new File(file.getNewPath()))) {
                                ((ConstValue)value).setValue(file.getOldFile());
                                System.out.println("reset file constvalue from "+file.getNewPath()+" to "+file.getOldFile().getAbsolutePath());
                            }

                        } else if (val.getClass().equals(Directory.class)) {

                            Directory dir = (Directory)val;

                            if (dir.equals(new File(file.getNewPath()))) {
                                ((ConstValue)value).setValue(file.getOldFile());
                                System.out.println("reset directory from "+file.getNewPath()+" to "+file.getOldFile().getAbsolutePath());
                            }
                        }
                    }
                }
            }
        }
    }

    public static void zip(String destFilename,File configFile, ArrayList<RestructuredFile> filesToZip) {

        int read = 0;
        FileInputStream in;
        byte[] data = new byte[1024];

        try {
            // connect Zip-Archiv with Stream
            ZipOutputStream out =
                    new ZipOutputStream(new FileOutputStream(destFilename));

            //add config file to filesToZip
            RestructuredFile configF = new RestructuredFile(configFile);
            configF.setNewPath(configFile.getName());
            filesToZip.add(configF);

            //Add each file that has been selected for save operation
            for (RestructuredFile file : filesToZip) {
                try {

                    ZipEntry entry = new ZipEntry(file.getNewPath());
                    in = new FileInputStream(file.getOldFile().getPath());

                    //Add new entry to archive
                    out.putNextEntry(entry);
                    // Append data to new entry
                    while ((read = in.read(data, 0, 1024)) != -1) {
                        out.write(data, 0, read);
                    }
                    out.closeEntry(); // Commit new entry
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void unzip(String zipFilename, String destinationDirectory) {
        final int BUFFER = 2048;

        try {

            // Specify file to decompress
            String inFileName = zipFilename;


            File sourceZipFile = new File(inFileName);
            File unzipDestinationDirectory = new File(destinationDirectory);

            // Open Zip file for reading
            ZipFile zipFile = new ZipFile(sourceZipFile, ZipFile.OPEN_READ);

            // Create an enumeration of the entries in the zip file
            Enumeration zipFileEntries = zipFile.entries();

            // Process each entry
            while (zipFileEntries.hasMoreElements()) {
                // grab a zip file entry
                ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();

                String currentEntry = entry.getName();
                System.out.println("Extracting: " + entry);

                File destFile =
                        new File(unzipDestinationDirectory, currentEntry);

                // grab file's parent directory structure
                File destinationParent = destFile.getParentFile();

                // create the parent directory structure if needed
                destinationParent.mkdirs();

                // extract file if not a directory
                if (!entry.isDirectory()) {
                    BufferedInputStream is =
                            new BufferedInputStream(zipFile.getInputStream(entry));
                    int currentByte;
                    // establish buffer for writing file
                    byte data[] = new byte[BUFFER];

                    // write the current file to disk
                    FileOutputStream fos = new FileOutputStream(destFile);
                    BufferedOutputStream dest =
                            new BufferedOutputStream(fos, BUFFER);

                    // read and write until last byte is encountered
                    while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
                        dest.write(data, 0, currentByte);
                    }
                    dest.flush();
                    dest.close();
                    is.close();
                }
            }
            zipFile.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
