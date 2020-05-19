package dev.j.api.restful.blog.vo.post.content;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostImage {
    String originalFileName;
    String saveFileName;
    String savePath;
    String url;
}