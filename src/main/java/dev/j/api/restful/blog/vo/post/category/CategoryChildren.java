package dev.j.api.restful.blog.vo.post.category;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "b_category")
public class CategoryChildren extends AbstractCategory{

    @ManyToOne(
        fetch = FetchType.EAGER
    )
    @JsonBackReference
    @JoinColumn(name = "b_parent_no", insertable = false, updatable = false, nullable = true)
    private CategoryChildren parent;
    
    @OneToMany(
        mappedBy = "parent"
    )
    @JsonManagedReference
    private List<CategoryChildren> children;

    @Transient
    private int count;
}