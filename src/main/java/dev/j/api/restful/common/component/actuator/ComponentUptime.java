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

  private double getWinUptime(){
    String uptime = executorToString("wmic os get lastbootuptime");
    uptime = uptime.replaceAll("[^0-9]", "");
    uptime = uptime.substring(0, 12);

    String year = uptime.substring(0, 4);
    String month = uptime.substring(4, 6);
    String day = uptime.substring(6, 8);
    String hour = uptime.substring(8, 10);
    String min = uptime.substring(10, 12);
    String sec = "00";

    Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.YEAR, parseInt(year));
    calendar.set(Calendar.MONTH, parseInt(month)-1);
    calendar.set(Calendar.DATE, parseInt(day));
    calendar.set(Calendar.HOUR_OF_DAY, parseInt(hour));
    calendar.set(Calendar.MINUTE, parseInt(min));
    calendar.set(Calendar.SECOND, parseInt(sec));

    return calendar.getTimeInMillis();
  }

  private double getLinuxUptime(){
    String[] cmd = { "/bin/sh", "-c", "(uptime -s)"};
    String uptime = executorToString(cmd);

    System.out.println(uptime);
    return 0;
    // uptime = uptime.substring(0, uptime.indexOf("."));
    // Calendar calendar = Calendar.getInstance();

    // calendar.add(Calendar.SECOND, -parseInt(uptime));

    // return calendar.getTimeInMillis();
  }
}