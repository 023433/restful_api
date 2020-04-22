package dev.j.api.restful.blog.repository.post;

import dev.j.api.restful.blog.vo.post.Summary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositorySummary extends JpaRepository<Summary, Long>, JpaSpecificationExecutor<Summary>{

    @EntityGraph(attributePaths = {"post"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<Summary> findAllWithPostBy(Pageable pageable);

    @EntityGraph(attributePaths = {"post"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<Summary> findAllWithByPostPublish(Pageable pageable, Boolean publish);

    @EntityGraph(attributePaths = {"post"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<Summary> findAllWithByCategoryCategoryNo(Pageable pageable, Long categoryNo);

}