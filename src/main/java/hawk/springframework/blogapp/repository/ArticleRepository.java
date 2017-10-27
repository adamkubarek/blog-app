package hawk.springframework.blogapp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import hawk.springframework.blogapp.domain.Article;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {

}
