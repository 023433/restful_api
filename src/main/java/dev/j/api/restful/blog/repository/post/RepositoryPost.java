package dev.j.api.restful.blog.repository.post;

import dev.j.api.restful.blog.vo.post.Post;
import dev.j.api.restful.blog.vo.post.PostCount;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RepositoryPost extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post>{
    Page<Post> findAllWithByCategoryCategoryNo(Pageable pageable, Long categoryNo);

    @Query("SELECT " +
            "  new dev.j.api.restful.blog.vo.post.PostCount( " +
            "    p.createDate, " +
            "    COUNT(p.createDate) " +
            "  ) " +
            "FROM " +
            "  Post p " +
            "WHERE p.publish = 1 " +
            "AND DATE_FORMAT(p.createDate,'%Y-%m') = :date " +
            "GROUP BY " +
            "  DATE_FORMAT(p.createDate,'%Y-%m-%d')")
    List<PostCount> findGroupByCreateDate(@Param("date") String date);
}