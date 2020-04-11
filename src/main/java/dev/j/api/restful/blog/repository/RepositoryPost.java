package dev.j.api.restful.blog.repository;

import dev.j.api.restful.blog.vo.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryPost extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post>{

    @EntityGraph(attributePaths = {"summary"}, type = EntityGraph.EntityGraphType.LOAD)
    Page<Post> findAllWithSummaryBy(Pageable pageable);
}