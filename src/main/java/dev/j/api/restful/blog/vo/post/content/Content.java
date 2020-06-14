package dev.j.api.restful.blog.vo.post.content;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.j.api.restful.blog.vo.post.Post;
import dev.j.api.restful.blog.vo.post.category.PostCategory;
import dev.j.api.restful.blog.vo.post.tag.PostTag;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
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
  @Column(name = "b_post_no", nullable = false, unique = true)
  private Long postNo;

  @MapsId
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "b_post_no", insertable = false, updatable = false)
  @JsonManagedReference
  private Post post;

  @Lob
  @Column(name = "b_content", nullable = false)
  private String content;
  
  @OneToMany(mappedBy = "post")
  @JsonManagedReference
  private List<PostCategory> category = new ArrayList<>();

  @OneToMany(mappedBy = "post")
  @JsonManagedReference
  private List<PostTag> tag = new ArrayList<>();
}