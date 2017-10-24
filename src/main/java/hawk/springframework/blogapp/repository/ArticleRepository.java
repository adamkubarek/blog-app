package hawk.springframework.blogapp.repository;

import org.springframework.data.repository.CrudRepository;

import hawk.springframework.blogapp.domain.Article;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
