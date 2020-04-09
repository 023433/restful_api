package dev.j.api.restful.blog.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.j.api.restful.blog.vo.post.Content;
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
@ToString(exclude = {"author", "categories", "content"})
@JsonIgnoreProperties(value = {"content"})
@Table(name = "b_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "no", example = "no")
    @JsonProperty("no")
    @Column(name = "b_no", nullable = false)
    private Long no;

    @ApiModelProperty(notes = "subject", example = "subject")
    @JsonProperty("subject")
    @Column(name = "b_subject", nullable = false)
    private String subject;

    @ApiModelProperty(notes = "publish", example = "publish")
    @JsonProperty("publish")
    @Column(name = "b_publish", nullable = false)
    private Boolean publish = true;

    @ApiModelProperty(notes = "viewCount", example = "viewCount")
    @JsonProperty("viewCount")
    @Column(name = "b_view_count", nullable = false)
    private int viewCount;

    @ManyToOne
    @RestResource(exported = false)
    @ApiModelProperty(notes = "author", example = "author")
    @JsonProperty("author")
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

    @ManyToMany(
        fetch = FetchType.LAZY,
        cascade = CascadeType.DETACH
    )
    @JoinTable(
        name = "b_post_category",
        joinColumns = @JoinColumn(name = "b_post_no", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "b_category_no", nullable = false)
    )
    @JsonBackReference
    @RestResource(exported = false)
    private List<Category> categories = new ArrayList<>();

    @OneToOne(
        fetch = FetchType.LAZY, 
        mappedBy ="post"
    )
    @JsonProperty("summary")
    @RestResource(exported = false)
    @JsonManagedReference
    private Summary summary;

    @OneToOne(
        fetch = FetchType.LAZY, 
        mappedBy ="post"
    )
    @JsonProperty("content")
    @RestResource(exported = false)
    @JsonManagedReference
    private Content content;

}