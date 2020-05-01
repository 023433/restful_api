package dev.j.api.restful.blog.vo.post.summary;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.j.api.restful.blog.vo.post.Post;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public class AbstractSummary {

    @Id
    @Column(name = "b_post_no", nullable = false, unique = true)
    private Long postNo;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_post_no", insertable = false, updatable = false)
    @JsonManagedReference
    private Post post;

    @Column(name = "b_thumbnail", nullable = false)
    private String thumbnail;

    @Column(name = "b_summary", nullable = false)
    private String summary;
}