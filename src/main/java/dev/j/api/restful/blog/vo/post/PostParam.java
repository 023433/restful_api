package dev.j.api.restful.blog.vo.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostParam {

    private String subject;
    private String[] tags;
    private String content;
    private String[] category;
    private String publish;
    private String thumbnailOriginalName;
    private String thumbnailSaveName;
    private String thumbnailSavePath;
}