package dev.j.api.restful.blog.vo.post.comment;

import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "b_comment")
public class CommentPostLoad extends AbstractComment{

    @PostLoad
    private void postLoad(){
        if(!publish){
            content = "관리자 삭제된 댓글입니다.";
        }

        if(secret){
            content = "비밀 댓글 입니다";
        }
    }
}