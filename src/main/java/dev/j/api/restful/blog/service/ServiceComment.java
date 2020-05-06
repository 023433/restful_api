package dev.j.api.restful.blog.service;

import dev.j.api.restful.blog.repository.post.comment.RepositoryComment;
import dev.j.api.restful.blog.repository.post.comment.RepositoryCommentGuest;
import dev.j.api.restful.blog.repository.post.comment.RepositoryCommentUser;
import dev.j.api.restful.blog.vo.post.comment.Comment;
import dev.j.api.restful.blog.vo.post.comment.CommentGuest;
import dev.j.api.restful.blog.vo.post.comment.CommentUser;
import dev.j.api.restful.common.property.PropertyJwtToken;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class ServiceComment extends AbstractService{

    @Autowired
    private RepositoryComment repositoryComment;
    
    @Autowired
    private RepositoryCommentGuest repositoryCommentGuest;

    @Autowired
    private RepositoryCommentUser repositoryCommentUser;
    
    public Page<Comment> getComments(String postNo, String pageNo, String pageSize) {
        int page = Integer.parseInt(pageNo);
        int size = Integer.parseInt(pageSize);
        Sort sort = Sort.by(Order.asc("groupNo"), Order.asc("orderNo"));
        
        Pageable pageable = PageRequest.of(page, size, sort);
      
        return repositoryComment.findAllWithByPostNoAndPostPublish(pageable, Long.valueOf(postNo), true);
    }


    public Page<Comment> getCommentsNewest() {

        Sort sort = Sort.by(Order.desc("createDate"), Order.desc("no"));
        
        Pageable pageable = PageRequest.of(0, 5, sort);
      
        return repositoryComment.findAllWithByPostPublish(pageable, true);
	}


    @Transactional
	public Comment saveComment(HttpServletRequest request, Comment comment) {
        System.out.println(comment);
        Comment parent = new Comment();

        int depthNo = 0;
        int orderNo = 0;
        int groupNo = 0;
        Long postNo = 0L;

        Long parentNo = comment.getParentNo();

        if(parentNo != null){
            Optional<Comment> optParent = repositoryComment.findById(parentNo);

            if(optParent.isPresent()){
                parent = optParent.get();
                depthNo = parent.getDepthNo();
                orderNo = parent.getOrderNo();
                groupNo = parent.getGroupNo();
                postNo = parent.getPostNo();
            }

            orderNo = repositoryComment.getMinOrderNo(groupNo, orderNo, depthNo);
        }

        if(orderNo == 0){
            orderNo = repositoryComment.getMaxOrderNo(groupNo) + 1;
        }

        if(comment.getPostNo() == null){
            comment.setPostNo(postNo);
        }

        comment.setOrderNo(orderNo);
        comment.setDepthNo(depthNo + 1);
        comment.setGroupNo(groupNo);

        if(orderNo > 1){
            repositoryComment.updateOrderNo(groupNo, orderNo);
        }

        CommentGuest guest = comment.getGuest();

        comment.setGuest(null);

        repositoryComment.save(comment);

        if(parentNo == null){
            comment.setGroupNo(comment.getNo().intValue());
            comment.setDepthNo(1);
            comment.setOrderNo(1);
            repositoryComment.save(comment);
        }

        if(guest != null){
            guest.setComment(comment);
            guest.setCommentNo(comment.getNo());
            guest.setIpAddress(request.getRemoteAddr());
            
            repositoryCommentGuest.save(guest);
    
            comment.setGuest(guest);

            return comment;
        }
        
        String jwtToken = request.getHeader(PropertyJwtToken.STR_TOKEN);
        String userId = componentJwtToken.getUserId(jwtToken);

        if(userId != null){
            CommentUser auth = new CommentUser();

            auth.setAuthor(userId);
            auth.setComment(comment);
            auth.setCommentNo(comment.getNo());
    
            repositoryCommentUser.save(auth);
            
            comment.setAuth(auth);
        }

      
        return comment;

	}


    @Transactional
	public Comment updateComment(HttpServletRequest request, Comment comment) {

        System.out.println(comment);
        Comment savedComment = new Comment();
        CommentGuest guest = comment.getGuest();

        Optional<Comment> saved = repositoryComment.findById(comment.getNo());

        if(saved.isPresent()){
            savedComment = saved.get();
        }else{
            return null;
        }

        if(guest != null){
            String pw = guest.getPw();
            String savedPw = savedComment.getGuest().getPw();

            if( !componentEncrypt.matches(pw, savedPw)){
                return null;
            }
  
        }


        String jwtToken = request.getHeader(PropertyJwtToken.STR_TOKEN);
        String userId = componentJwtToken.getUserId(jwtToken);

        if(userId != null){
            CommentUser auth = savedComment.getAuth();

            String savedUserId = auth.getAuthor();

            if( !userId.equals(savedUserId)){
                return null;
            }
        }

        savedComment.setContent(comment.getContent());
        savedComment.setSecret(comment.getSecret());

        repositoryComment.save(savedComment);

		return savedComment;
	}
}