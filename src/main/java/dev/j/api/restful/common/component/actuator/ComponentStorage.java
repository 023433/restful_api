package dev.j.api.restful.common.component.actuator;

import org.springframework.stereotype.Component;

@Component
public class ComponentStorage extends AbstractComponent {

	public int getDirSize(String dir) {
        String size = "0";

        if (isWindows()) {

        } else if (isUnix()) {
            String[] cmd = { "/bin/sh", "-c", "(du -s " + dir + ")"};
            size = executorToString(cmd);
            size = size.substring(0, size.indexOf("\t"));
            size = size.replaceAll("[^0-9]", "");
        }
        
		return parseInt(size);
	}
    
}