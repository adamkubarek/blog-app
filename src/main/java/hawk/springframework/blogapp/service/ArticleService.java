package hawk.springframework.blogapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import hawk.springframework.blogapp.domain.Article;

public interface ArticleService {

	Page <Article> findAllPageable(Pageable pageable);
}
