package dev.j.api.restful.blog.vo.post.category;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "b_category")
public class CategoryParent extends AbstractCategory{

    @ManyToOne(
        fetch = FetchType.EAGER
    )
    @JsonManagedReference
    @JoinColumn(name = "b_parent_no", insertable = false, updatable = false, nullable = true)
    private CategoryParent parent;

}