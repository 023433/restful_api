package dev.j.api.restful.blog.repository.post;

import dev.j.api.restful.blog.vo.post.content.Content;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryContent extends JpaRepository<Content, Long>, JpaSpecificationExecutor<Content>{

  @EntityGraph(attributePaths = {"post"}, type = EntityGraph.EntityGraphType.LOAD)
  Content findOneByPostNo(Long postNo);

  @EntityGraph(attributePaths = {"post"}, type = EntityGraph.EntityGraphType.LOAD)
  Content findOneByPostNoAndPostPublish(Long postNo, Boolean publish);
    
}