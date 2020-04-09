package dev.j.api.restful.blog.vo.post;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Getter
@Setter
@ToString(exclude = "post")
@Table(name = "b_post_content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "no", example = "no")
    @JsonProperty("no")
    @Column(name = "b_no", nullable = false)
    private Long no;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_post_no", insertable = false, updatable = false)
    @JsonBackReference
    @JsonProperty("post")
    @RestResource(exported = false)
    private Post post;

    @ApiModelProperty(notes = "post_no", example = "post_no")
    @JsonProperty("postNo")
    @Column(name = "b_post_no", nullable = false)
    private String postNo;

    @ApiModelProperty(notes = "main_image", example = "mainImage")
    @JsonProperty("mainImage")
    @Column(name = "b_main_image", nullable = false)
    private String mainImage;

    @ApiModelProperty(notes = "content", example = "content")
    @JsonProperty("content")
    @Column(name = "b_content", nullable = false)
    private String content;

}