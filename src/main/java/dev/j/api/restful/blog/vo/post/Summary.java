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
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Getter
@Setter
@ToString(exclude = "post")
@Table(name = "b_post_summary")
public class Summary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "b_no", example = "no")
    @JsonProperty("b_no")
    @Column(name = "b_no", nullable = false)
    private Long no;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_post_no", insertable = false, updatable = false)
    @ApiModelProperty(notes = "b_post_no", example = "post_no")
    @JsonProperty("b_post_no")
    @RestResource(exported = false)
    private Post post;

    @ApiModelProperty(notes = "b_thumbnail", example = "thumbnail")
    @JsonProperty("b_thumbnail")
    @Column(name = "b_thumbnail", nullable = false)
    private String thumbnail;

    @ApiModelProperty(notes = "b_summary", example = "summary")
    @JsonProperty("b_summary")
    @Column(name = "b_summary", nullable = false)
    private String summary;

}