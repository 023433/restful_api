package dev.j.api.restful.blog.repository.post.comment;

import dev.j.api.restful.blog.vo.post.comment.CommentPostLoad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryCommentPostLoad extends JpaRepository<CommentPostLoad, Long>, JpaSpecificationExecutor<CommentPostLoad>{
  @EntityGraph(attributePaths = {"auth", "guest"}, type = EntityGraph.EntityGraphType.LOAD)
  Page<CommentPostLoad> findAllWithByPostPublish(Pageable pageable, Boolean publish);

  @EntityGraph(attributePaths = {"auth", "guest"}, type = EntityGraph.EntityGraphType.LOAD)
  Page<CommentPostLoad> findAllWithByPostNoAndPostPublish(Pageable pageable, Long postNo, Boolean publish);
}
