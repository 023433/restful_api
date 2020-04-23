package dev.j.api.restful.blog.repository.post;

import dev.j.api.restful.blog.vo.post.summary.SummaryCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositorySummaryCategory extends JpaRepository<SummaryCategory, Long>, JpaSpecificationExecutor<SummaryCategory>{

    @EntityGraph(attributePaths = {"post"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<SummaryCategory> findAllWithPostBy(Pageable pageable);

    @EntityGraph(attributePaths = {"post"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<SummaryCategory> findAllWithByPostPublish(Pageable pageable, Boolean publish);

    @EntityGraph(attributePaths = {"post"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<SummaryCategory> findAllWithByCategoryCategoryNo(Pageable pageable, Long categoryNo);

}