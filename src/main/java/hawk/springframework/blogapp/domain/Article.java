package hawk.springframework.blogapp.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Article {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	private String shortDescription;
	
	@Lob
	private String content;
	
	private String author;
	private String time;
	
	@ManyToMany
	@JoinTable(name="article_tag", 
	joinColumns= @JoinColumn(name="article_id"),
	inverseJoinColumns = @JoinColumn(name="tag_id"))
	private Set<Tag> tags = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="article")
	private List<Comment> comments = new ArrayList<>();
}
