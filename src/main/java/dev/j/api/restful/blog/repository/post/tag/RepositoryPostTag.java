package dev.j.api.restful.blog.repository.post.tag;

import dev.j.api.restful.blog.vo.post.tag.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryPostTag extends JpaRepository<PostTag, Long>, JpaSpecificationExecutor<PostTag>{
}