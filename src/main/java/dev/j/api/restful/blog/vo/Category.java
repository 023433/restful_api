package dev.j.api.restful.blog.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = {"parentNo", "childCategory", "posts"})
@Table(name = "b_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "b_no", example = "no")
    @JsonProperty("b_no")
    @Column(name = "b_no", nullable = false)
    private Long no;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_parent_no")
    private Category parentNo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentNo", cascade = CascadeType.ALL)
    private List<Category> childCategory;

    @ManyToMany(mappedBy = "categories")
    private List<Post> posts;

    @ApiModelProperty(notes = "b_title", example = "title")
    @JsonProperty("b_title")
    @Column(name = "b_title", nullable = false)
    private String title;

}