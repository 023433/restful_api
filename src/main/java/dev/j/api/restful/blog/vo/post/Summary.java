package dev.j.api.restful.blog.vo.post;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.j.api.restful.blog.vo.Post;
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
@Table(name = "b_post_summary")
public class Summary {

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

    @Column(name = "b_thumbnail", nullable = false)
    private String thumbnail;

    @Column(name = "b_summary", nullable = false)
    private String summary;

}