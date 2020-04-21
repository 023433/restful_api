package dev.j.api.restful.blog.vo.post;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.j.api.restful.blog.vo.Post;
import dev.j.api.restful.blog.vo.post.category.PostCategory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
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
    @Column(name = "b_no", nullable = false)
    private Long no;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_post_no", insertable = false, updatable = false)
    @JsonManagedReference
    private Post post;

    @Column(name = "b_post_no", nullable = false, unique = true)
    private Long postNo;

    @Column(name = "b_main_image", nullable = false)
    private String mainImage;

    @Lob
    @Column(name = "b_content", nullable = false)
    private String content;
    
    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    private List<PostCategory> category = new ArrayList<>();
}