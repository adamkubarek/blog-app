package hawk.springframework.blogapp.repository;

import org.springframework.data.repository.CrudRepository;

import hawk.springframework.blogapp.domain.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {

}
