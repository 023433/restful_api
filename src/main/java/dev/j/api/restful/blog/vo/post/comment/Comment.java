package dev.j.api.restful.blog.vo.post.comment;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "b_comment")
public class Comment extends AbstractComment {


}