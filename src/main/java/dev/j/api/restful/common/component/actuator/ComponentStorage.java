package dev.j.api.restful.common.component.actuator;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import org.springframework.stereotype.Component;

@Component
public class ComponentStorage extends AbstractComponent {

	public String getDirSize(String dir) {
        String size = "";

        if (isWindows()) {

        } else if (isUnix()) {
            System.out.println("sss : " + dir);
            String[] cmd = { "/bin/sh", "-c", "(du -s " +")"};
            size = executorToString("/bin/sh -c (cd " + dir + "; /bin/du -s )");

            StringBuffer output = new StringBuffer();

            Process p;

            try {
                String command="/bin/du -s";
                File dir2 = new File("/home/user/project");//path
                p = Runtime.getRuntime().exec(command,null,dir2);
                p.waitFor();
                BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                            String line = "";           
                while ((line = reader.readLine())!= null) {
                    output.append(line + "\n");
                }

                System.out.println(output.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

		return size;
	}
    
}