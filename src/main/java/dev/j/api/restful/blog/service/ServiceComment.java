package dev.j.api.restful.blog.service;

import dev.j.api.restful.blog.repository.post.comment.RepositoryComment;
import dev.j.api.restful.blog.repository.post.comment.RepositoryCommentGuest;
import dev.j.api.restful.blog.repository.post.comment.RepositoryCommentUser;
import dev.j.api.restful.blog.vo.post.comment.Comment;
import dev.j.api.restful.blog.vo.post.comment.CommentGuest;
import dev.j.api.restful.blog.vo.post.comment.CommentUser;
import java.util.Optional;
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

    public Page<Comment> getComments(String pageNo, String pageSize) {
        int page = Integer.parseInt(pageNo);
        int size = Integer.parseInt(pageSize);
        Sort sort = Sort.by(Order.asc("groupNo"), Order.asc("orderNo"));
        
        Pageable pageable = PageRequest.of(page, size, sort);
      
        return repositoryComment.findAllWithByPostPublish(pageable, true);
    }

    
    public Page<Comment> getComments(String postNo, String pageNo, String pageSize) {
        int page = Integer.parseInt(pageNo);
        int size = Integer.parseInt(pageSize);
        Sort sort = Sort.by(Order.asc("groupNo"), Order.asc("orderNo"));
        
        Pageable pageable = PageRequest.of(page, size, sort);
      
        return repositoryComment.findAllWithByPostNoAndPostPublish(pageable, Long.valueOf(postNo), true);
    }



    @Transactional
	public Comment saveCommentWithUser(Comment comment) {

        CommentUser auth = comment.getAuth();
        comment.setAuth(null);
        repositoryComment.save(comment);

        auth.setComment(comment);
        auth.setCommentNo(comment.getNo());

        repositoryCommentUser.save(auth);
        
        comment.setAuth(auth);
        
        return comment;
	}

    @Transactional
	public Comment saveCommentWithGuest(Comment comment) {

        CommentGuest guest = comment.getGuest();

        comment.setGuest(null);
        repositoryComment.save(comment);

        String pw = guest.getPw();

        guest.setPw(componentEncrypt.encrypt(pw));
        guest.setComment(comment);
        guest.setCommentNo(comment.getNo());

        repositoryCommentGuest.save(guest);

        comment.setGuest(guest);

        return comment;
    }
    

    @Transactional
	public Comment saveComment(Comment comment) {

        Comment parent = new Comment();

        int depthNo = 0;
        int orderNo = 0;
        int groupNo = 0;

        Long parentNo = comment.getParentNo();

        if(parentNo != null){
            Optional<Comment> optParent = repositoryComment.findById(parentNo);

            if(optParent.isPresent()){
                parent = optParent.get();
                depthNo = parent.getDepthNo();
                orderNo = parent.getOrderNo();
                groupNo = parent.getGroupNo();
            }

            orderNo = repositoryComment.getMinOrderNo(groupNo, orderNo, depthNo);
        }

        if(orderNo == 0){
            orderNo = repositoryComment.getMaxOrderNo(groupNo) + 1;
        }

        comment.setOrderNo(orderNo);
        comment.setDepthNo(depthNo + 1);
        comment.setGroupNo(groupNo);

        if(orderNo > 1){
            repositoryComment.updateOrderNo(groupNo, orderNo);
        }

        repositoryComment.save(comment);

        if(parentNo == null){
            comment.setGroupNo(comment.getNo().intValue());
            comment.setDepthNo(1);
            comment.setOrderNo(1);
            repositoryComment.save(comment);
        }

        return comment;
	}
}