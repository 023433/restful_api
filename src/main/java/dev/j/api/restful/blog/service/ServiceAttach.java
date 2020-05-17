package dev.j.api.restful.blog.service;

import dev.j.api.restful.blog.vo.post.content.MainImage;
import dev.j.api.restful.blog.vo.post.summary.Thumbnail;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ServiceAttach extends AbstractService {

    public Thumbnail attachThumbnail(HttpServletRequest request, MultipartFile attachImage) {

        String today = componentFileUpload.getToday();
        String url = componentFileUpload.getUrl();

        String originalFileName = attachImage.getOriginalFilename();
        String savePath = "/thumbnail/" + today + "/";

        String saveFileName = componentFileUpload.saveImageFile(savePath, attachImage);
  

        Thumbnail thumbnail = new Thumbnail();

        thumbnail.setOriginalFileName(originalFileName);
        thumbnail.setSaveFileName(saveFileName);
        thumbnail.setSavePath(savePath);
        thumbnail.setUrl(url);
		return thumbnail;
	}

	public MainImage attachMainImage(HttpServletRequest request, MultipartFile attachImage) {

        String today = componentFileUpload.getToday();
        String url = componentFileUpload.getUrl();

        String originalFileName = attachImage.getOriginalFilename();
        String savePath = "/mainimage/" + today + "/";

        String saveFileName = componentFileUpload.saveImageFile(savePath, attachImage);

		MainImage mainImage = new MainImage();
   
        mainImage.setOriginalFileName(originalFileName);
        mainImage.setSaveFileName(saveFileName);
        mainImage.setSavePath(savePath);
        mainImage.setUrl(url);
		return mainImage;
	}

}