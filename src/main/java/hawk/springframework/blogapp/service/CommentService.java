package hawk.springframework.blogapp.service;

import java.util.List;

import hawk.springframework.blogapp.domain.Comment;

public interface CommentService {
	List <Comment> getAllComments();
	void deleteComment(Long commentId);
	void addNewComment(Long articleId, Comment comment);
}
