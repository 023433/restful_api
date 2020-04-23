package dev.j.api.restful.blog.repository.tag;

import dev.j.api.restful.blog.vo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryTag extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag>{
}