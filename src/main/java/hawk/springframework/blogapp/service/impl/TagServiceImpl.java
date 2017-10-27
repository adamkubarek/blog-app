package hawk.springframework.blogapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hawk.springframework.blogapp.domain.Article;
import hawk.springframework.blogapp.domain.Tag;
import hawk.springframework.blogapp.repository.ArticleRepository;
import hawk.springframework.blogapp.repository.TagRepository;
import hawk.springframework.blogapp.service.TagService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TagServiceImpl implements TagService {

	private TagRepository tagRepository;
	private ArticleRepository articleRepository;

	@Autowired
	public TagServiceImpl(TagRepository tagRepository, ArticleRepository articleRepository) {
		this.tagRepository = tagRepository;
		this.articleRepository = articleRepository;
	}

	@Transactional
	@Override
	public List <Tag> getAllTags() {
		List <Tag> tags = new ArrayList<>();
		tagRepository.findAll().iterator().forEachRemaining(tags::add);
		return tags;
	}

	@Override
	public Tag saveTag(Tag tag) {
		String hashName = tag.getName();
		tag.setName(hashName);
		return tagRepository.save(tag);
	}

	@Override
	public void deleteTag(Long tagId) {
		Optional <Tag> tagOptional = tagRepository.findById(tagId);
		if(!tagOptional.isPresent()) {
			log.debug("tag id = "+ tagId + " not found"); 
		} else {
			Tag tag = tagOptional.get();
			log.debug("tag id " + tag.getId() + " to delete");
			
			for (Article article : tag.getArticles()) {
				log.debug("detaching article id " + article.getId() + " from tag id " + tag.getId());
				article.removeTagFromSet(tag);
				articleRepository.save(article);
			}
			
			tagRepository.deleteById(tagId);
		}
	}

	@Override
	public Tag findTagByName(String tagName) {
		Optional <Tag> tagOptional = tagRepository.findByName(tagName);
		if (!tagOptional.isPresent()) {
			log.debug("Error tag name "+tagName+" not found");
			throw new RuntimeException();
		} else {
			Tag tag = tagOptional.get();
			return tag;
			
		}

	}
}

