package dev.j.api.restful.blog.repository.post;

import dev.j.api.restful.blog.vo.post.summary.SummaryTag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositorySummaryTag extends JpaRepository<SummaryTag, Long>, JpaSpecificationExecutor<SummaryTag>{

    @EntityGraph(attributePaths = {"post"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<SummaryTag> findAllWithByTagTagTitle(Pageable pageable, String title);

}