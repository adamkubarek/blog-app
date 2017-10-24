package hawk.springframework.blogapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hawk.springframework.blogapp.domain.Comment;
import hawk.springframework.blogapp.repository.CommentRepository;
import hawk.springframework.blogapp.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	
	@Autowired
	public CommentServiceImpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	@Transactional
	@Override
	public List<Comment> getAllComments() {
		List <Comment> comments = new ArrayList<>();
		commentRepository.findAll().iterator().forEachRemaining(comments::add);
		return comments;
	}

}
