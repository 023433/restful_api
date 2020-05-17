package dev.j.api.restful.blog.vo.post.summary;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "b_post_summary")
public class Summary extends AbstractSummary {

}