package dev.j.api.restful.blog.repository.post.summary;

import dev.j.api.restful.blog.vo.post.summary.SummaryCategory;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositorySummaryCategory extends JpaRepository<SummaryCategory, Long>, JpaSpecificationExecutor<SummaryCategory>{

    @EntityGraph(attributePaths = {"post"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<SummaryCategory> findAllWithByPostPublish(Pageable pageable, Boolean publish);

    @EntityGraph(attributePaths = {"post"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<SummaryCategory> findAllWithByPostPublishAndCategoryCategoryNo(Pageable pageable, Boolean publish, Long categoryNo);

    @EntityGraph(attributePaths = {"post"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<SummaryCategory> findAllWithByPostPublishAndPostSubjectContains(Pageable pageable, Boolean publish, String subject);

    @EntityGraph(attributePaths = {"post"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<SummaryCategory> findAllWithByPostPublishAndPostCreateDateBetween(Pageable pageable, Boolean publish, LocalDateTime start, LocalDateTime end);
}