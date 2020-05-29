package dev.j.api.restful.common.component.actuator;

import io.micrometer.core.instrument.util.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class AbstractComponent {

  private static String OS = System.getProperty("os.name").toLowerCase();

  protected String executorToString(String exec) {
    try {
      InputStream inputStream = Runtime.getRuntime().exec(exec).getInputStream();
      return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }

  protected String executorToString(String ...exec) {
    try {
      InputStream inputStream = Runtime.getRuntime().exec(exec).getInputStream();
      return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    } catch (IOException e) {
      e.printStackTrace();
      return "";
    }
  }

  protected double executor(String exec) {
    try {
      InputStream inputStream = Runtime.getRuntime().exec(exec).getInputStream();
      String text = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

      text = text.replaceAll("[^0-9]", "");
      return Double.valueOf(text);
    } catch (IOException e) {
      e.printStackTrace();
      return 0;
    }
  }

  protected double executor(String ...exec) {
    try {
      InputStream inputStream = Runtime.getRuntime().exec(exec).getInputStream();
      String text = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

      text = text.replaceAll("[^0-9]", "");
      return Double.valueOf(text);
    } catch (IOException e) {
      e.printStackTrace();
      return 0;
    }
  }

  protected static boolean isWindows() {
    return (OS.indexOf("win") >= 0);
  }

  protected static boolean isUnix() {
    return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
  }

}