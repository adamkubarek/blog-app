package hawk.springframework.blogapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hawk.springframework.blogapp.domain.Article;
import hawk.springframework.blogapp.domain.Comment;
import hawk.springframework.blogapp.repository.ArticleRepository;
import hawk.springframework.blogapp.repository.CommentRepository;
import hawk.springframework.blogapp.service.CommentService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private ArticleRepository articleRepository;
	
	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository, ArticleRepository articleRepository) {
		this.commentRepository = commentRepository;
		this.articleRepository = articleRepository;
	}

	@Transactional
	@Override
	public List<Comment> getAllComments() {
		List <Comment> comments = new ArrayList<>();
		commentRepository.findAll().iterator().forEachRemaining(comments::add);
		return comments;
	}

	@Override
	public void deleteComment(Long commentId) {
		Optional <Comment> commentOptional = commentRepository.findById(commentId);
		if (!commentOptional.isPresent()) {
			log.debug("comment id="+commentId+" does not exist");
		} else {
			Comment comment = commentOptional.get();
			
			Article detachedArticle = comment.getArticle();
			detachedArticle.detachCommentFromArticle(comment);
			articleRepository.save(detachedArticle);
			
			commentRepository.delete(comment);
			
		}
	}

}
