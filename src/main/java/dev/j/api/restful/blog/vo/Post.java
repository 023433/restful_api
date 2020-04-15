package dev.j.api.restful.blog.vo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.j.api.restful.blog.vo.post.category.PostCategory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "b_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_no", nullable = false)
    private Long no;

    @Column(name = "b_subject", nullable = false)
    private String subject;

    @Column(name = "b_publish", nullable = false)
    private Boolean publish = true;

    @Column(name = "b_view_count", nullable = false)
    private int viewCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "b_author", nullable = false, insertable = false, updatable = false)
    private User user;

    @Column(name = "b_author", nullable = false)
    private String author;
    
    @Column(
        name = "b_create_date", 
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", 
        updatable = false
    )
    private LocalDateTime createDate;

    @Column(
        name = "b_update_date", 
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP"
    )
    private LocalDateTime updateDate;


    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    private List<PostCategory> category = new ArrayList<>();

}