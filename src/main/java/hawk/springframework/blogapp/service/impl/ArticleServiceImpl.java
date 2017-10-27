package hawk.springframework.blogapp.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import hawk.springframework.blogapp.domain.Article;
import hawk.springframework.blogapp.domain.Tag;
import hawk.springframework.blogapp.repository.ArticleRepository;
import hawk.springframework.blogapp.repository.TagRepository;
import hawk.springframework.blogapp.service.ArticleService;
import hawk.springframework.blogapp.util.CurrentTime;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService{

	private ArticleRepository articleRepository;
	private TagRepository tagRepository;

	@Autowired
	public ArticleServiceImpl(ArticleRepository articleRepository, TagRepository tagRepository) {
		this.articleRepository = articleRepository;
		this.tagRepository = tagRepository;
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

	@Transactional
	@Override
	public void saveArticle(Article article) {
		List <Tag> chosenTags = new ArrayList<>();
		Set <Tag> convertedChosenTags = new HashSet<>();
		chosenTags = (List<Tag>) tagRepository.findAllById(article.getTagsId());
		for (Tag tag : chosenTags) {
			convertedChosenTags.add(tag);
		}
		
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String authorName = auth.getName();
	    
	    article.setAuthor(authorName);
	    article.setShortDescription(article.getContent().substring(0,200)+"...");
		article.setTags(convertedChosenTags);
		article.setTime(CurrentTime.get());
		
		articleRepository.save(article);
		
	}
}
