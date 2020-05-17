package dev.j.api.restful.blog.service;

import dev.j.api.restful.blog.vo.post.content.MainImage;
import dev.j.api.restful.blog.vo.post.summary.Thumbnail;
import dev.j.api.restful.common.property.PropertyLog;
import dev.j.api.restful.common.property.PropertyPath;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class ServiceAttach extends AbstractService {
    private final Marker marker = MarkerFactory.getMarker(PropertyLog.MARKER_BLOG);

    public Thumbnail attachThumbnail(HttpServletRequest request, MultipartFile attachImage) {

        log.info(marker, "attachThumbnail");

        String today = componentFileUpload.getToday();
        String url = componentFileUpload.getUrl();

        String originalFileName = attachImage.getOriginalFilename();
        String savePath = PropertyPath.TEMP + "thumbnail/" + today + "/";

        String saveFileName = componentFileUpload.saveImageFile(savePath, attachImage);
  

        Thumbnail thumbnail = new Thumbnail();

        thumbnail.setOriginalFileName(originalFileName);
        thumbnail.setSaveFileName(saveFileName);
        thumbnail.setSavePath(savePath);
        thumbnail.setUrl(url);
		return thumbnail;
	}

	public MainImage attachMainImage(HttpServletRequest request, MultipartFile attachImage) {

        log.info(marker, "attachMainImage");

        String today = componentFileUpload.getToday();
        String url = componentFileUpload.getUrl();

        String originalFileName = attachImage.getOriginalFilename();
        String savePath = PropertyPath.TEMP + "mainimage/" + today + "/";

        String saveFileName = componentFileUpload.saveImageFile(savePath, attachImage);

		MainImage mainImage = new MainImage();
   
        mainImage.setOriginalFileName(originalFileName);
        mainImage.setSaveFileName(saveFileName);
        mainImage.setSavePath(savePath);
        mainImage.setUrl(url);
		return mainImage;
	}

}