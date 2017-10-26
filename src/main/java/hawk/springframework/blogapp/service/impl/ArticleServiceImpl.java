package hawk.springframework.blogapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hawk.springframework.blogapp.domain.Article;
import hawk.springframework.blogapp.repository.ArticleRepository;
import hawk.springframework.blogapp.service.ArticleService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService{

	private ArticleRepository articleRepository;

	@Autowired
	public ArticleServiceImpl(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	@Transactional
	@Override
	public Page<Article> findAllPageable(Pageable pageable) {
		return articleRepository.findAll(pageable);
	}

	@Transactional
	@Override
	public List <Article> findAllArticles() {
		List <Article> articles = new ArrayList<>();
		articleRepository.findAll().iterator().forEachRemaining(articles::add);
		return articles;
	}

	@Transactional
	@Override
	public Article findArticleById(Long articleId) {
		Optional <Article> articleOptional = articleRepository.findById(articleId);
		if (!articleOptional.isPresent()) {
			log.debug("Article id="+ articleId+" does not exist");
			throw new RuntimeException();
			// TODO exception handling
		} else {
			Article article = articleOptional.get();
			return article;
		}
	}

}
