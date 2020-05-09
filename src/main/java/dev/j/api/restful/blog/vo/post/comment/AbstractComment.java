package dev.j.api.restful.blog.vo.post.comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.j.api.restful.blog.vo.post.Post;
import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"post"})
@MappedSuperclass
public class AbstractComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_no", nullable = false)
    private Long no;

    @Column(name = "b_parent_no")
    private Long parentNo;

    @Column(name = "b_content", nullable = false, length = 1000)
	protected String content;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_post_no", insertable = false, updatable = false)
    @JsonBackReference
    private Post post;

    @Column(name = "b_post_no", nullable = false)
    private Long postNo;

    @Column(name = "b_group_no", nullable = false)
    private int groupNo;

    @Column(name = "b_depth_no", nullable = false)
    private int depthNo;

    @Column(name = "b_order_no", nullable = false)
    private int orderNo;

    @Column(name = "b_publish", nullable = false)
	protected Boolean publish = true;

    @Column(name = "b_secret", nullable = false)
	protected Boolean secret = true;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "comment")
    @JoinColumn(name = "b_no", insertable = false, updatable = false)
    @JsonManagedReference
    private CommentUser auth;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "comment")
    @JoinColumn(name = "b_no", insertable = false, updatable = false)
    @JsonManagedReference
    private CommentGuest guest;

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


}