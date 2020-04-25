package dev.j.api.restful.blog.vo.post.tag;

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
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString(exclude = {"post"})
@Table(name = "b_post_tag")
public class PostTag {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_no", nullable = false)
    private Long no;

    @ManyToOne
    @JoinColumn(name = "b_post_no", nullable = false, insertable = false, updatable = false)
    @JsonBackReference
    private Post post;

    @ManyToOne
    @JoinColumn(name = "b_tag_no", nullable = false, insertable = false, updatable = false)
    private Tag tag;

    @Column(name = "b_post_no", nullable = false)
    private Long postNo;

    @Column(name = "b_tag_no", nullable = false)
    private Long tagNo;
}