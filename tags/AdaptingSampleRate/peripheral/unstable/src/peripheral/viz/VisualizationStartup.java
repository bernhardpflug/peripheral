/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.viz;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *
 * @author Andi
 */
public class VisualizationStartup {

  private static final String TEMP_FOLDER = "tmp";

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws IOException, InterruptedException {
    VisualizationStartup startup = new VisualizationStartup();

    startup.startup();
  }

  private void startup() throws InterruptedException, IOException {
    unzipJar();
    startVisRunnable();

    deleteDirectory(new File(TEMP_FOLDER));
    (new File(getScriptName())).delete();
  }

  private void unzipJar() {
    unzip("Visualization.jar", "tmp");
  }

  private void startVisRunnable() throws InterruptedException, IOException {
    //extractScriptFromJar();

    Runtime rt = Runtime.getRuntime();
    //Process proc = rt.exec(getExecString());
    Process proc = rt.exec("java -Xmx512m -Djava.library.path=tmp/lib/processing-1.0.3-essentials/libraries/opengl/library -jar tmp/vis-runnable.jar");
    InputStream stderr = proc.getErrorStream();
    InputStreamReader isr = new InputStreamReader(stderr);
    BufferedReader br = new BufferedReader(isr);
    String line = null;
    System.out.println("<ERROR>");
    while ((line = br.readLine()) != null) {
      System.out.println(line);
    }
    System.out.println("</ERROR>");
    proc.waitFor();
    Thread.sleep(2000);
  }

  private void extractScriptFromJar() throws IOException, InterruptedException {
    String scriptName = getScriptName();
    InputStream is = getClass().getResourceAsStream("/script/" + scriptName);
    System.out.println((new File(".").getAbsoluteFile()));
    (new File(TEMP_FOLDER)).mkdir();
    FileOutputStream os = new FileOutputStream(new File(scriptName), false);

    StringBuilder sb = new StringBuilder();
    while (true) {
      int c = is.read();
      if (c == -1) {
        break;
      }
      os.write(c);
    }
    is.close();
    os.close();

    if (!isWindowsOS()) {
      File f = new File(scriptName);
      String cmd[] = {"chmod", "777", f.getAbsolutePath()};
      Process p = Runtime.getRuntime().exec(cmd);
      p.waitFor();
    }
  }

  private String getScriptName() {
    if (isWindowsOS()) {
      return "run.bat";
    }
    return "run";
  }

  private String getExecString() {
    if (isWindowsOS()) {
      return "cmd /c start run.bat";
    }
    return "./run";
  }

  private boolean isWindowsOS() {
    return System.getProperty("os.name").startsWith("Windows");
  }

  private boolean deleteDirectory(File path) {
    if (path.exists()) {
      File[] files = path.listFiles();
      for (int i = 0; i < files.length; i++) {
        if (files[i].isDirectory()) {
          deleteDirectory(files[i]);
        } else {
          files[i].delete();
        }
      }
    }
    return (path.delete());
  }

  private void unzip(String zipFilename, String destinationDirectory) {
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
