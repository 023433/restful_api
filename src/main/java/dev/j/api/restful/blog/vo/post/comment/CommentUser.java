package dev.j.api.restful.blog.vo.post.comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.CascadeType;
import dev.j.api.restful.blog.vo.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = "comment")
@JsonIgnoreProperties(value = {"no", "commentNo"})
@Table(name = "b_comment_user")
public class CommentUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "b_no", nullable = false)
  private Long no;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "b_comment_no", insertable = false, updatable = false)
  @JsonBackReference
  private Comment comment;

  @Column(name = "b_comment_no", nullable = false)
  private Long commentNo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "b_author", nullable = false, insertable = false, updatable = false)
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private User user;

  @Column(name = "b_author", nullable = false)
  private String author;
}