package dev.j.api.restful.blog.vo.post.category;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.j.api.restful.blog.vo.Post;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "b_post_category")
public class PostCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_no", nullable = false)
    private Long no;

    @ManyToOne
    @JoinColumn(name = "b_post_no", nullable = false, insertable = false, updatable = false)
    @JsonBackReference
    private Post post;

    @ManyToOne
    @JoinColumn(name = "b_category_no", nullable = false, insertable = false, updatable = false)
    private CategoryParent category;
       
    @Column(name = "b_post_no", nullable = false)
    private Long postNo;

    @Column(name = "b_category_no", nullable = false)
    private Long categoryNo;

}