package dev.j.api.restful.blog.repository.post.category;

import dev.j.api.restful.blog.vo.post.category.PostCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface RepositoryPostCategory extends JpaRepository<PostCategory, Long>, JpaSpecificationExecutor<PostCategory>{
    @Query("SELECT " +
            "  new dev.j.api.restful.blog.vo.post.category.PostCategory( " +
            "    ps.categoryNo, " +
            "    COUNT(ps.categoryNo) " +
            "  ) " +
            "FROM " +
            "  PostCategory ps " +
            "  INNER JOIN Post p on p.no = ps.postNo " +
            "WHERE p.publish = 1 " +
            "GROUP BY " +
            "  ps.categoryNo")
    List<PostCategory> findPostCount();
}