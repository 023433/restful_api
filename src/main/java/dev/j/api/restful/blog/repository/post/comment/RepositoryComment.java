package dev.j.api.restful.blog.repository.post.comment;

import dev.j.api.restful.blog.vo.post.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RepositoryComment extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment>{

  @Modifying
  @Query("UPDATE Comment c SET c.orderNo = c.orderNo + 1 WHERE c.groupNo = :groupNo AND c.orderNo >= :orderNo")
  void updateOrderNo(@Param("groupNo") int groupNo, @Param("orderNo") int orderNo);


  @Query("SELECT COALESCE(MAX(c.orderNo), 0) FROM Comment c WHERE c.groupNo = :groupNo")
  int getMaxOrderNo(@Param("groupNo") int parentNo);

  @Query(
      " SELECT " +
      "  COALESCE(MIN(c.orderNo), 0) " +
      " FROM Comment c " +
      " WHERE c.groupNo = :groupNo " +
      " AND c.orderNo > :orderNo" +
      " AND c.depthNo <= :depthNo")
  int getMinOrderNo(
      @Param("groupNo") int groupNo, 
      @Param("orderNo") int orderNo, 
      @Param("depthNo") int depthNo);
        
}