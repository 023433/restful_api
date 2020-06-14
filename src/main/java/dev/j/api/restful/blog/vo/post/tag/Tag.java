package dev.j.api.restful.blog.vo.post.tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "b_tag")
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

  public Tag(String title){
    this.title = title;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "b_no", nullable = false)
  private Long no;

  @Column(name = "b_title", nullable = false, unique = true)
  private String title;

  @Override
  public boolean equals(Object obj) {
    Tag temp = (Tag) obj;
    return title.equals(temp.getTitle());
  }

}