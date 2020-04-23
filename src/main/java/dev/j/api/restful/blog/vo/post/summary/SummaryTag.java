package dev.j.api.restful.blog.vo.post.summary;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.j.api.restful.blog.vo.Post;
import dev.j.api.restful.blog.vo.post.category.PostCategory;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "b_post_summary")
public class SummaryTag extends AbstractSummary {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_post_no", insertable = false, updatable = false)
    @JsonManagedReference
    private Post post;
    
    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    private List<PostTag> tag = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    private List<PostCategory> category = new ArrayList<>();
}