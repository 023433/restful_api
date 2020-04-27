package dev.j.api.restful.blog.repository.post.tag;

import dev.j.api.restful.blog.vo.post.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryTag extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag>{
}