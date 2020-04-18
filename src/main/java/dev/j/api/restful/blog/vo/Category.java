package dev.j.api.restful.blog.vo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "b_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_no", nullable = false)
    private Long no;

    @ManyToOne(
        fetch = FetchType.EAGER
    )
    @JsonManagedReference
    @JoinColumn(name = "b_parent_no", insertable = false, updatable = false, nullable = true)
    private Category parent;

    @Column(name = "b_parent_no")
    private Long parentNo;


    @Column(name = "b_title", nullable = false)
    private String title;

}