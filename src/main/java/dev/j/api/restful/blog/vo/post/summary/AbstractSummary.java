package dev.j.api.restful.blog.vo.post.summary;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.j.api.restful.blog.vo.post.Post;
import javax.persistence.CascadeType;
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
  protected Long postNo;

  @MapsId
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "b_post_no", insertable = false, updatable = false)
  @JsonManagedReference
  protected Post post;

  @Column(name = "b_thumbnail", nullable = false)
  protected String thumbnail;

  @Column(name = "b_save_path", nullable = false)
  protected String savePath;

  @Column(name = "b_summary", nullable = false)
  protected String summary;
}