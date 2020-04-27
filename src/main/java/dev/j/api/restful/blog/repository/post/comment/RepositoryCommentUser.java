package dev.j.api.restful.blog.repository.post.comment;

import dev.j.api.restful.blog.vo.post.comment.CommentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryCommentUser extends JpaRepository<CommentUser, Long>, JpaSpecificationExecutor<CommentUser>{
}