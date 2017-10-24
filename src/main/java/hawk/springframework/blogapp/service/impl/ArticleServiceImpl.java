package hawk.springframework.blogapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hawk.springframework.blogapp.domain.Article;
import hawk.springframework.blogapp.repository.ArticleRepository;
import hawk.springframework.blogapp.service.ArticleService;

@Service
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

}
