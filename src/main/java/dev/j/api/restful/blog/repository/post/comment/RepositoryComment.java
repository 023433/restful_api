package dev.j.api.restful.blog.repository.post.comment;

import dev.j.api.restful.blog.vo.post.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryComment extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment>{
    @EntityGraph(attributePaths = {"auth", "guest"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<Comment> findAllWithByPostPublish(Pageable pageable, Boolean publish);

    @EntityGraph(attributePaths = {"auth", "guest"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<Comment> findAllWithByPostNoAndPostPublish(Pageable pageable, Long postNo, Boolean publish);
}