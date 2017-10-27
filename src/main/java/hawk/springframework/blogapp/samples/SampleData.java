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
	
		Optional <Tag> sportTagOptional = tagRepository.findByName("Spring");
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
//		Set <Article> articles = new HashSet<>();
//		articles.add(firstArticle);
//		sportTag.setArticles(articles);
		
		articleRepository.save(firstArticle);
		
		Article secondArticle = new Article();
		secondArticle.setAuthor("admin");
		secondArticle.setShortDescription("This is short description of a first Article");
		secondArticle.setContent("This is short description of a first Article This is short description of a first Article This is short description of a first Article This is short description of a first Article");
		secondArticle.setTitle("Second article");
		secondArticle.setTime(CurrentTime.get());
		secondArticle.setTags(tags);
//		Set <Article> articles2 = new HashSet<>();
//		articles2.add(secondArticle);
//		sportTag.setArticles(articles2);

		articleRepository.save(secondArticle);
		
		Article thirdArticle = new Article();
		thirdArticle.setAuthor("admin");
		thirdArticle.setShortDescription("This is short description of a first Article");
		thirdArticle.setContent("This is short description of a first Article This is short description of a first Article This is short description of a first Article This is short description of a first Article");
		thirdArticle.setTitle("Third article");
		thirdArticle.setTime(CurrentTime.get());
		thirdArticle.setTags(tags);
		articleRepository.save(thirdArticle);
		
		Article fourthArticle = new Article();
		fourthArticle.setAuthor("admin");
		fourthArticle.setShortDescription("This is short description of a first Article");
		fourthArticle.setContent("This is short description of a first Article This is short description of a first Article This is short description of a first Article This is short description of a first Article");
		fourthArticle.setTitle("Fourth article");
		fourthArticle.setTime(CurrentTime.get());
		fourthArticle.setTags(tags);
		articleRepository.save(fourthArticle);
		
		Article fifthArticle = new Article();
		fifthArticle.setAuthor("admin");
		fifthArticle.setShortDescription("This is short description of a first Article");
		fifthArticle.setContent("This is short description of a first Article This is short description of a first Article This is short description of a first Article This is short description of a first Article");
		fifthArticle.setTitle("Fifth article");
		fifthArticle.setTime(CurrentTime.get());
		fifthArticle.setTags(tags);
		articleRepository.save(fifthArticle);
		
		Article sixthArticle = new Article();
		sixthArticle.setAuthor("admin");
		sixthArticle.setShortDescription("This is short description of a first Article");
		sixthArticle.setContent("This is short description of a first Article This is short description of a first Article This is short description of a first Article This is short description of a first Article");
		sixthArticle.setTitle("Sixth article");
		sixthArticle.setTime(CurrentTime.get());
		sixthArticle.setTags(tags);
		articleRepository.save(sixthArticle);
		
		
		Article seventhArticle = new Article();
		seventhArticle.setAuthor("admin");
		seventhArticle.setShortDescription("This is short description of a first Article");
		seventhArticle.setContent("This is short description of a first Article This is short description of a first Article This is short description of a first Article This is short description of a first Article");
		seventhArticle.setTitle("Seventh article");
		seventhArticle.setTime(CurrentTime.get());
		seventhArticle.setTags(tags);
		articleRepository.save(seventhArticle);
		
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
