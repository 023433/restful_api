package dev.j.api.restful.blog.vo.post.summary;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@MappedSuperclass
public class AbstractSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_no", nullable = false)
    private Long no;

    @Column(name = "b_post_no", nullable = false, unique = true)
    private Long postNo;

    @Column(name = "b_thumbnail", nullable = false)
    private String thumbnail;

    @Column(name = "b_summary", nullable = false)
    private String summary;
}