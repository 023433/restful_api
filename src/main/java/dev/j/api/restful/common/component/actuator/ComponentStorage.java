package dev.j.api.restful.common.component.actuator;

import org.springframework.stereotype.Component;

@Component
public class ComponentStorage extends AbstractComponent {

	public String getDirSize(String dir) {
        String size = "";

        if (isWindows()) {

        } else if (isUnix()) {
            System.out.println("sss : " + dir);
            String[] cmd = { "/bin/sh", "-c", "(du -s " +")"};
            size = executorToString("bash -c (cd " + dir + "; du -s )");
        }

		return size;
	}
    
}