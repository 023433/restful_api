package dev.j.api.restful.common.component;

import dev.j.api.restful.common.property.PropertyLog;
import dev.j.api.restful.common.property.PropertyPath;
import dev.j.api.restful.common.property.PropertyUrl;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
public class ComponentFileUpload {

    @Value("${ENV}")
    String env;

    private final Marker marker = MarkerFactory.getMarker(PropertyLog.MARKER_BLOG);

    private String DIR_IMG_PATH;
    private String URL_IMG_PATH;

    private final String dateFormat = "yyyyMMdd";
    private final SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.KOREA);

    @PostConstruct
    public void init() {
        if (env == "PRD" || env.equals("PRD")) {
            // 운영
            DIR_IMG_PATH = PropertyPath.IMG_PRD;
            URL_IMG_PATH = PropertyUrl.URL_IMG_PRD;
        } else if (env == "DEV" || env.equals("DEV")) {
            // 개발
            DIR_IMG_PATH = PropertyPath.IMG_DEV;
            URL_IMG_PATH = PropertyUrl.URL_IMG_DEV;

        }
    }

    public String saveImageFile(String savePath, MultipartFile file) {

        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        String saveName = uuid + "." + fileExtension;

        mkDirs(DIR_IMG_PATH + File.separator + savePath);

        File saveFile = new File(DIR_IMG_PATH + File.separator + savePath, saveName);
        
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            log.error(marker, "ComponentFileUpload.saveImageFile.IOException : " + e.getMessage());
            return null;
        }

        return saveName;
    }

    public Boolean moveTo(String source, String destination){
        try {
            File sourceFile = new File(DIR_IMG_PATH + source);
            File destinationFile = new File(DIR_IMG_PATH + destination);

            FileUtils.moveFile(sourceFile, destinationFile);
            return true;
        } catch (IOException e) {
            log.error(marker, "ComponentFileUpload.moveTo.IOException : " + e.getMessage());
            return false;
        }
    }

    private void mkDirs(String path){
        File dir = new File(path);

        if(!dir.exists()){
            dir.mkdirs();
        }
    }

    public String getUrl(){
        return URL_IMG_PATH;
    }

    public String getToday(){
        return format.format(Calendar.getInstance().getTime());
    }
}