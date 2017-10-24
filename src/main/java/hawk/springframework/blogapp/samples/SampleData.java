package hawk.springframework.blogapp.samples;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import hawk.springframework.blogapp.domain.Article;
import hawk.springframework.blogapp.domain.Comment;
import hawk.springframework.blogapp.domain.Tag;
import hawk.springframework.blogapp.repository.ArticleRepository;
import hawk.springframework.blogapp.repository.CommentRepository;
import hawk.springframework.blogapp.repository.TagRepository;
import hawk.springframework.blogapp.util.CurrentTime;

@Component
public class SampleData implements ApplicationListener <ContextRefreshedEvent> {

	private ArticleRepository articleRepository;
	private CommentRepository commentRepository;
	private TagRepository tagRepository;
	
	@Autowired
	public SampleData(ArticleRepository articleRepository, CommentRepository commentRepository,
			TagRepository tagRepository) {
		this.articleRepository = articleRepository;
		this.commentRepository = commentRepository;
		this.tagRepository = tagRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		injectSampleData();
	}

	private void injectSampleData() {
		Optional <Tag> javaTagOptional = tagRepository.findByName("Java");
		if(!javaTagOptional.isPresent()) {
			throw new RuntimeException("Java tag nie istnieje");
		}
	
		Optional <Tag> sportTagOptional = tagRepository.findByName("Sport");
		if(!sportTagOptional.isPresent()) {
			throw new RuntimeException("Sport tag nie istnieje");
		}
		
		Tag javaTag = javaTagOptional.get();
		Tag sportTag = sportTagOptional.get();
		
		Article firstArticle = new Article();
		firstArticle.setAuthor("admin");
		firstArticle.setShortDescription("This is short description of a first Article");
		firstArticle.setContent("This is short description of a first Article This is short description of a first Article This is short description of a first Article This is short description of a first Article");
		firstArticle.setTitle("First article");
		firstArticle.setTime(CurrentTime.get());
		Set <Tag> tags = new HashSet<>();
		tags.add(sportTag);
		tags.add(javaTag);
		firstArticle.setTags(tags);
		Set <Article> articles = new HashSet<>();
		articles.add(firstArticle);
		sportTag.setArticles(articles);
		
		articleRepository.save(firstArticle);
		Comment firstComment = new Comment();
		Comment secondComment = new Comment();
		
		firstComment.setTime(CurrentTime.get());
		firstComment.setUserName("John");
		firstComment.setContent("Świetny artykuł - polecam");
		firstComment.setArticle(firstArticle);
		
		secondComment.setTime(CurrentTime.get());
		secondComment.setUserName("Jack");
		secondComment.setContent("Great article");
		secondComment.setArticle(firstArticle);
		
		commentRepository.save(firstComment);
		commentRepository.save(secondComment);
	}
}
