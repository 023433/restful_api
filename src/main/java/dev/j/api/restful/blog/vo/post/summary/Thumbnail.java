package dev.j.api.restful.blog.vo.post.summary;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Thumbnail {
  String originalFileName;
  String saveFileName;
  String savePath;
  String url;
}