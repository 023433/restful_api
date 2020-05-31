package dev.j.api.restful.common.component.actuator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

@Component
public class ComponentStorage extends AbstractComponent {

    public String getDirSize(String dir) {
        String size = "";

        if (isWindows()) {

        } else if (isUnix()) {
            // create a process and execute
            try {
            System.out.println("aaaa : " + dir);
            String[] cmd = { "/bin/sh", "-c", "(du -s)"};

                Process p = Runtime.getRuntime().exec(cmd, null, new File(dir));
                InputStream inputStream = p.getInputStream();
                return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("sss : " + dir);
            String[] cmd = { "/bin/sh", "-c", "(du -s '" + dir + "')"};

            size = executorToString(cmd);
        }

		return size;
	}
    
}