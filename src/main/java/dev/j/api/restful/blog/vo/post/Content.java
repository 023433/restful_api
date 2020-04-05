package dev.j.api.restful.blog.vo.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.j.api.restful.blog.vo.Post;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = "post")
@Table(name = "b_post_content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "b_no", example = "no")
    @JsonProperty("b_no")
    @Column(name = "b_no", nullable = false)
    private Long no;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_post_no")
    private Post post;

    @ApiModelProperty(notes = "b_main_image", example = "mainImage")
    @JsonProperty("b_main_image")
    @Column(name = "b_main_image", nullable = false)
    private String mainImage;

    @ApiModelProperty(notes = "b_content", example = "content")
    @JsonProperty("b_content")
    @Column(name = "b_content", nullable = false)
    private String content;

}