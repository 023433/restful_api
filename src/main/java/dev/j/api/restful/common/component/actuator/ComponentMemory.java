package dev.j.api.restful.common.component.actuator;

import org.springframework.stereotype.Component;

@Component
public class ComponentMemory extends AbstractComponent {
  
  public double getTotalMemory() {

    double totalMemory = 0;

    if (isWindows()) {
      totalMemory = getWinTotalMemory();
    } else if (isUnix()) {
      totalMemory = getLinuxTotalMemory();
    }

    return totalMemory;
  }

  public double getFreeMemory() {

    double freeMemory = 0;

    if (isWindows()) {
      freeMemory = getWinFreeMemory();
    } else if (isUnix()) {
      freeMemory = getLinuxFreeMemory();
    } 

    return freeMemory;
  }


  private double getWinTotalMemory(){
    double totalMemory = executor("wmic ComputerSystem get TotalPhysicalMemory");
    return totalMemory / 1073741824L;
  }

  private double getWinFreeMemory(){
    double memory = executor("wmic OS get FreePhysicalMemory");
    double e = Math.floor(Math.log(memory)/Math.log(1024));

    return (memory/Math.pow(1024, Math.floor(e)));
  }

  private double getLinuxTotalMemory(){
    String[] cmd = {"/bin/sh", "-c", "cat /proc/meminfo | grep 'MemTotal'"};
    double memory = executor(cmd);

    double e = Math.floor(Math.log(memory)/Math.log(1024));

    return (memory/Math.pow(1024, Math.floor(e)));
  }

  private double getLinuxFreeMemory(){
    String[] cmd = {"/bin/sh", "-c", "cat /proc/meminfo | grep 'MemFree'"};
    double memory = executor(cmd);

    double e = Math.floor(Math.log(memory)/Math.log(1024));

    return (memory/Math.pow(1024, Math.floor(e)));
  }

}