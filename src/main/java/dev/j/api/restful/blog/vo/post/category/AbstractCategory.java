package dev.j.api.restful.blog.vo.post.category;

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
public class AbstractCategory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "b_no", nullable = false)
  private Long no;

  @Column(name = "b_title", nullable = false)
  private String title;

  @Column(name = "b_parent_no")
  private Long parentNo;
}