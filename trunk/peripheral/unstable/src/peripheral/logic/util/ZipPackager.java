/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.logic.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import peripheral.logic.DisplayConfiguration;
import peripheral.logic.positioningtool.PositioningTool;
import peripheral.logic.symboladapter.Symbol;
import peripheral.logic.symboladapter.SymbolAdapter;

/**
 *
 * @author Andi
 */
public class ZipPackager {

    public static Set<File> getFilesToZip(DisplayConfiguration dc) {
        Set<File> filesToZip = new HashSet<File>();

        filesToZip.add(dc.getBackgroundImageFile());

        for (SymbolAdapter adapter : dc.getAdapter()) {
            for (PositioningTool pt : adapter.getTool().getElements()) {
                for (Symbol symbol : pt.getSymbols()) {
                    filesToZip.add(symbol.getFile());
                }
            }
        }

        return filesToZip;
    }

    public static void zip(String destFilename, File configFile) {
        DisplayConfiguration dc = DisplayConfiguration.getInstance();

        int read = 0;
        FileInputStream in;
        byte[] data = new byte[1024];

        Set<File> filesToZip = getFilesToZip(dc);

        filesToZip.add(configFile);

        try {
            // Zip-Archiv mit Stream verbinden
            ZipOutputStream out =
                    new ZipOutputStream(new FileOutputStream(destFilename));
            // Archivierungs-Modus setzen
            //out.setMethod(ZipOutputStream.DEFLATED);
            // Hinzufügen der einzelnen Einträge

            for (File file : filesToZip) {
                try {
                    //stdout.println(args[i]);
                    // Eintrag für neue Datei anlegen
                    //System.out.println(file.toURI());
                    ZipEntry entry = new ZipEntry(getRelativePath(file));
                    in = new FileInputStream(file.getPath());
                    // Neuer Eintrag dem Archiv hinzufügen
                    out.putNextEntry(entry);
                    // Hinzufügen der Daten zum neuen Eintrag
                    while ((read = in.read(data, 0, 1024)) != -1) {
                        out.write(data, 0, read);
                    }
                    out.closeEntry(); // Neuen Eintrag abschließen
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

    public static String getRelativePath(File file) {
        //if windows system, make unix style pathname
        if (File.separatorChar == '\\') {
            return file.getPath().replace(":", "").replace('\\', '/');
        }
        else if (file.getPath().startsWith("/")){
            return file.getPath().substring(1).replace(":", "");
        }
        return file.getPath().replace(":", "");
    }

    public static void unzip(String zipFilename, String destinationDirectory) {
        final int BUFFER = 2048;

        try {
            System.out.println("Example of ZIP file decompression.");

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
