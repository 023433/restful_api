package dev.j.api.restful.blog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.j.api.restful.blog.vo.post.Summary;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Getter
@Setter
@ToString(exclude = {"author", "categories", "summary"})
@Table(name = "b_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "b_no", example = "no")
    @JsonProperty("b_no")
    @Column(name = "b_no", nullable = false)
    private Long no;

    @ApiModelProperty(notes = "b_subject", example = "subject")
    @JsonProperty("b_subject")
    @Column(name = "b_subject", nullable = false)
    private String subject;

    @ApiModelProperty(notes = "b_publish", example = "publish")
    @JsonProperty("b_publish")
    @Column(name = "b_publish", nullable = false)
    private Boolean publish = true;

    @ApiModelProperty(notes = "b_view_count", example = "view_count")
    @JsonProperty("b_view_count")
    @Column(name = "b_view_count", nullable = false)
    private int viewCount;

    @ManyToOne
    @RestResource(exported = false)
    @ApiModelProperty(notes = "b_author", example = "author")
    @JsonProperty("b_author")
    @JoinColumn(name = "b_author", nullable = false)
    private User author;
    
    @Column(
        name = "b_create_date", 
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", 
        updatable = false
    )
    @JsonProperty("createDate")
    private LocalDateTime createDate;

    @Column(
        name = "b_update_date", 
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    @JsonProperty("updateDate")
    private LocalDateTime updateDate;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "b_post_category",
        joinColumns = @JoinColumn(name = "b_post_no", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "b_category_no", nullable = false)
    )
    private List<Category> categories = new ArrayList<>();

    @OneToOne(
        fetch = FetchType.LAZY, 
        mappedBy ="post", 
        cascade = CascadeType.ALL, 
        optional = false
    )
    private Summary summary;

}