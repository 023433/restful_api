package dev.j.api.restful.blog.repository.post.summary;

import dev.j.api.restful.blog.vo.post.summary.Summary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositorySummary extends JpaRepository<Summary, Long>, JpaSpecificationExecutor<Summary>{

}