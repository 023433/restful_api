package dev.j.api.restful.common.component.actuator;

import org.springframework.stereotype.Component;

@Component
public class ComponentStorage extends AbstractComponent {

	public String getDirSize(String dir) {
        String size = "";

        if (isWindows()) {

        } else if (isUnix()) {
            System.out.println("sss : " + dir);
            String[] cmd = { "/bin/sh", "-c", "cd", dir, "pwd"};
            size = executorToString(cmd);
        }

		return size;
	}
    
}