package dev.j.api.restful.common.component.actuator;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "os-memory")
public class ActuatorMemory {

    private static String OS = System.getProperty("os.name").toLowerCase();

    @ReadOperation
    public Map<String, String> getServerInfo() {

        Map<String, String> map = new HashMap<>();

        double freeMemory = 0;
        double totalMemory = 0;

        if (isWindows()) {
            totalMemory = getWinTotalMemory();
            freeMemory = getWinFreeMemory();
        } else if (isUnix()) {
            totalMemory = getLinuxTotalMemory();
            freeMemory = getLinuxFreeMemory();
        } else {

        }

        map.put("freeMemory", String.format("%.2f",  freeMemory));
        map.put("totalMemory", String.format("%.2f",  totalMemory));
   
        return map;
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
        double memory = executor("cat /proc/meminfo | grep 'MemTotal'");
        System.out.println("getLinuxTotalMemory : " + memory);

        double e = Math.floor(Math.log(memory)/Math.log(1024));

        return (memory/Math.pow(1024, Math.floor(e)));
    }

    private double getLinuxFreeMemory(){
        double memory = executor("cat /proc/meminfo | grep 'MemFree'");

        System.out.println("getLinuxFreeMemory : " + memory);
        double e = Math.floor(Math.log(memory)/Math.log(1024));

        return (memory/Math.pow(1024, Math.floor(e)));
    }

    private double executor(String exec) {
        try {
            InputStream inputStream = Runtime.getRuntime().exec(exec).getInputStream();
            String text = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        System.out.println("executor " + exec + " : " + text);

            text = text.replaceAll("[^0-9]", "");
            return Double.valueOf(text);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

  
    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
    }

}