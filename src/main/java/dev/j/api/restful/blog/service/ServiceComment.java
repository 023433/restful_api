package dev.j.api.restful.blog.service;

import dev.j.api.restful.blog.repository.post.comment.RepositoryComment;
import dev.j.api.restful.blog.repository.post.comment.RepositoryCommentGuest;
import dev.j.api.restful.blog.repository.post.comment.RepositoryCommentUser;
import dev.j.api.restful.blog.vo.post.comment.Comment;
import dev.j.api.restful.blog.vo.post.comment.CommentGuest;
import dev.j.api.restful.blog.vo.post.comment.CommentUser;
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
        Sort sort = Sort.by(Order.desc("no"));
        
        Pageable pageable = PageRequest.of(page, size, sort);
      
        return repositoryComment.findAllWithByPostPublish(pageable, true);
    }

    
    public Page<Comment> getComments(String postNo, String pageNo, String pageSize) {
        int page = Integer.parseInt(pageNo);
        int size = Integer.parseInt(pageSize);
        Sort sort = Sort.by(Order.desc("createDate"));
        
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
}