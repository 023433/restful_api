package dev.j.api.restful.blog.repository.post.comment;

import dev.j.api.restful.blog.vo.post.comment.CommentGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryCommentGuest extends JpaRepository<CommentGuest, Long>, JpaSpecificationExecutor<CommentGuest>{

}