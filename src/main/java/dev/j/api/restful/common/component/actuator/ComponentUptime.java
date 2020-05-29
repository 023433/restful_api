package dev.j.api.restful.common.component.actuator;

import java.util.Calendar;
import org.springframework.stereotype.Component;

@Component
public class ComponentUptime extends AbstractComponent {

  public double getSystemUptime() {
    if (isWindows()) {
      return getWinUptime();
    } else if (isUnix()) {
      return getLinuxUptime();
    } 
    return 0;
  }

  private int parseInt(String str){
    return Integer.parseInt(str);
  }

  private double getWinUptime(){
    String uptime = executorToString("wmic os get lastbootuptime");
    return extracted(uptime);
  }

  private double getLinuxUptime(){
    String[] cmd = {"/bin/sh", "-c", "uptime -s"};
    String uptime = executorToString(cmd);
    return extracted(uptime);
  }

  private double extracted(String uptime) {
    uptime = uptime.replaceAll("[^0-9]", "");
    uptime = uptime.substring(0, 14);

    String year = uptime.substring(0, 4);
    String month = uptime.substring(4, 6);
    String day = uptime.substring(6, 8);
    String hour = uptime.substring(8, 10);
    String min = uptime.substring(10, 12);
    String sec = uptime.substring(12, 14);

    System.out.println(uptime);
    System.out.println(year);
    System.out.println(month);
    System.out.println(day);
    System.out.println(hour);
    System.out.println(min);
    System.out.println(sec);

    Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.YEAR, parseInt(year));
    calendar.set(Calendar.MONTH, parseInt(month)-1);
    calendar.set(Calendar.DATE, parseInt(day));
    calendar.set(Calendar.HOUR_OF_DAY, parseInt(hour));
    calendar.set(Calendar.MINUTE, parseInt(min));
    calendar.set(Calendar.SECOND, parseInt(sec));

    System.out.println(calendar.getTimeInMillis());
    System.out.println(calendar.getTime());
    System.out.println(calendar.getTime().getTime());
    return calendar.getTimeInMillis();
  }
}