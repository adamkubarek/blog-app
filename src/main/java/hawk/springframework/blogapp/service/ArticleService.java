package hawk.springframework.blogapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import hawk.springframework.blogapp.domain.Article;

public interface ArticleService {

	Page <Article> findAllPageable(Pageable pageable);
	List <Article> findAllArticles();
	Article findArticleById(Long articleId);
	void saveArticle(Article article);
	void deleteArticle(Long articleId);
}
