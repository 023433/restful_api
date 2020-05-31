package dev.j.api.restful.common.component.actuator;

import org.springframework.stereotype.Component;

@Component
public class ComponentStorage extends AbstractComponent {

	public String getDirSize(String dir) {
        String size = "";

        if (isWindows()) {

        } else if (isUnix()) {
            String[] cmd = { "/bin/sh", "-c", "(du -s " + dir + ")"};
            size = executorToString(cmd);
            size = size.substring(0, size.indexOf("/"));
        }

		return size;
	}
    
}