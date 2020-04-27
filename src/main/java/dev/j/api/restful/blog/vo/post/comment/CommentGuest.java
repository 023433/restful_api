package dev.j.api.restful.blog.vo.post.comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = "comment")
@Table(name = "b_comment_guest")
public class CommentGuest {
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

    @Column(name = "b_name", nullable = false)
    private String name;
    
    @Column(name = "b_pw", nullable = false)
    private String pw;
}