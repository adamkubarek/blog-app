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
	
		Optional <Tag> springTagOptional = tagRepository.findByName("Spring");
		if(!springTagOptional.isPresent()) {
			throw new RuntimeException("Spring tag nie istnieje");
		}
		
		Optional <Tag> algorithmTagOptional = tagRepository.findByName("Algorytmy");
		if(!algorithmTagOptional.isPresent()) {
			throw new RuntimeException("Algorytmy tag nie istnieje");
		}
		
		Tag javaTag = javaTagOptional.get();
		Tag springTag = springTagOptional.get();
		Tag algorithmTag = algorithmTagOptional.get();
		
		Article firstArticle = new Article();
		firstArticle.setAuthor("admin");

		firstArticle.setContent("Jednym ze sposobów rozwiązywania złożonych problemów jest zastosowanie algorytmów równoległych."
				+ " Oznacza to, że program nie jest wykonywany tylko jeden raz na jednym procesorze, ale wielokrotnie równolegle"
				+ " na wielu różnych maszynach. Podejście takie jest stosowane od lat w superkomputerach, jednak w takiej realizacji"
				+ " jest ono bardzo kosztowne. Nowym pomysłem jest tutaj zastosowanie sieci zwykłych komputerów tworzących klaster."
				+ " Całe zadanie jest wtedy rozdzielane na wiele maszyn i obliczane równolegle przy pomocy tysięcy procesorów. Czasami"
				+ " taką potężną sieć rozproszoną nazywa się z j. angielskiego grid. Przykładem jej zastosowania może być program SETI@home,"
				+ " gdzie dane z nasłuchu kosmosu analizują dziesiątki tysięcy komputerów należących do zwykłych użytkowników. Maszyny"
				+ " są podłączone do Internetu, przez który przesyłają wyniki pracy uruchomionych na nich aplikacji. Rozwinięciem tego rozwiązania"
				+ " jest projekt parasolowy BOINC@home, który obejmuje kilkadziesiąt tego typu projektów co SETI@home, zajmujących się"
				+ " zagadnieniami z wielu dziedzin nauki, nie tylko ścisłych.Obecnie algorytmy równoległe mogą być wykorzystywane na zwykłych domowych"
				+ " komputerach, ponieważ ogromna większość z nich posiada procesory wielordzeniowe, które w uproszczeniu są połączeniem kilku procesorów w jeden.");
		firstArticle.generateShortDescription();
		firstArticle.setTitle("Sposoby rozwiązywania problemów");
		firstArticle.setTime(CurrentTime.get());
		Set <Tag> tags = new HashSet<>();
		tags.add(algorithmTag);
		firstArticle.setTags(tags);

		articleRepository.save(firstArticle);
		
		Article secondArticle = new Article();
		secondArticle.setAuthor("admin");
		secondArticle.setContent("Speaking at the International Astronautical Congress, Elon Musk said that the first goal"
				+ " of his SpaceX missions is to find water on other planets. He also spoke about colonising Mars. In 2022,"
				+ " two 106-metre-tall SpaceX rockets with construction materials will go to Mars, followed by two more cargo"
				+ " rockets in 2024. In that same year, two flights of 100 passengers each will go to Mars to build a city using"
				+ " the cargo which will have been delivered.");
		secondArticle.generateShortDescription();
		secondArticle.setTitle("Finding Water on Other Planets");
		secondArticle.setTime(CurrentTime.get());
		Set <Tag> tags2 = new HashSet<>();
		tags2.add(springTag);
		secondArticle.setTags(tags2);

		articleRepository.save(secondArticle);
		
		
		Article thirdArticle = new Article();
		thirdArticle.setAuthor("admin");
		
		thirdArticle.setContent("Bacon ipsum dolor amet burgdoggen turducken meatball ground round spare ribs fatback brisket. "
				+ "Shankle turkey beef ribs biltong, capicola pastrami sausage frankfurter turducken jowl pork loin filet mignon"
				+ " brisket meatloaf meatball. Sausage prosciutto ball tip hamburger short loin. Capicola beef shoulder turducken"
				+ " ball tip bresaola picanha pig alcatra. Hamburger shank filet mignon burgdoggen. Capicola prosciutto cow, beef ribs"
				+ " shoulder fatback filet mignon meatloaf bacon tongue. Landjaeger t-bone pork chop strip steak tri-tip. Swine porchetta meatball tail."
				+ " Sirloin bacon pig ribeye corned beef swine. Shank cow filet mignon short ribs alcatra. Pancetta cow corned beef jerky, cupim burgdoggen turducken "
				+ "beef prosciutto strip steak doner. Jerky short loin biltong porchetta strip steak swine. Shankle swine tenderloin boudin, tri-tip pig andouille "
				+ "brisket. Andouille ham kevin, turducken ribeye pork chop short ribs kielbasa leberkas cupim. Leberkas ribeye pork, spare ribs shoulder alcatra filet mignon.");
		thirdArticle.generateShortDescription();
		thirdArticle.setTitle("Becon ipsum");
		thirdArticle.setTime(CurrentTime.get());
		Set <Tag> tags3 = new HashSet<>();
		tags3.add(javaTag);
		tags3.add(algorithmTag);
		thirdArticle.setTags(tags3);
		articleRepository.save(thirdArticle);
		
		
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
