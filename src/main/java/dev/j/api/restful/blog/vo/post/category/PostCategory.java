package dev.j.api.restful.blog.vo.post.category;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.j.api.restful.blog.vo.post.Post;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Entity
@ToString
@Table(name = "b_post_category")
@NoArgsConstructor
@AllArgsConstructor
public class PostCategory {

    public PostCategory(Long categoryNo, Long count){
        this.categoryNo = categoryNo;
        this.count = count;
    }

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

    @Transient
    private Long count;

}