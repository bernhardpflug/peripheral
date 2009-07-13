/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package peripheral.viz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import peripheral.logic.util.ZipPackager;

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
    runStartupScript();

    deleteDirectory(new File(TEMP_FOLDER));
    (new File(getScriptName())).delete();
  }

  private void unzipJar() {
    ZipPackager.unzip("Visualization.jar", "tmp");
  }

  private void runStartupScript() throws InterruptedException, IOException {
    extractScriptFromJar();

    Runtime rt = Runtime.getRuntime();
    Process proc = rt.exec(getExecString());
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
  }

  private void extractScriptFromJar() throws IOException {
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
}
