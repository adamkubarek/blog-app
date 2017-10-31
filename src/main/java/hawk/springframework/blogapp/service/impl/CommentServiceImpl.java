package hawk.springframework.blogapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hawk.springframework.blogapp.domain.Article;
import hawk.springframework.blogapp.domain.Comment;
import hawk.springframework.blogapp.exceptions.NotFoundException;
import hawk.springframework.blogapp.repository.ArticleRepository;
import hawk.springframework.blogapp.repository.CommentRepository;
import hawk.springframework.blogapp.service.CommentService;
import hawk.springframework.blogapp.util.CurrentTime;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {
	private final CommentRepository commentRepository;
	private final ArticleRepository articleRepository;
	
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

	@Transactional
	@Override
	public void deleteComment(Long commentId) {
		Optional <Comment> commentOptional = commentRepository.findById(commentId);
		if (!commentOptional.isPresent()) {
			log.debug("comment id="+commentId+" does not exist");
			throw new NotFoundException("Nie znaleziono komentarza o zadanym id");
		} else {
			Comment comment = commentOptional.get();
			Article detachedArticle = comment.getArticle();
			detachedArticle.detachCommentFromArticle(comment);
			articleRepository.save(detachedArticle);
			
			commentRepository.delete(comment);
		}
	}

	@Transactional
	@Override
	public void addNewComment(Long articleId, Comment comment) {
		Optional <Article> articleOptional = articleRepository.findById(articleId);
		if(!articleOptional.isPresent()) {
			log.debug("Could not add comment to article id=" + articleId + "article not found");
			throw new NotFoundException("Nie znaleziono artykułu o zadanym id");
		} else {
			Article article = articleOptional.get();
			
			comment.setArticle(article);
			comment.setTime(CurrentTime.get());
			article.addNewComment(comment);
			articleRepository.save(article); // NO NEED TO SAVE commentRepository because Article object has CascadeType.ALL
		}
	}
}
