package hawk.springframework.blogapp.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import hawk.springframework.blogapp.domain.Article;
import hawk.springframework.blogapp.domain.Tag;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Long> {
	Page <Article> findByTags(Set<Tag> tags, Pageable pageable);
}
