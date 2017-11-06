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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Article {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(max=100, message="Maksymalna długość tytułu to 100 znaków")
	private String title;
	@Lob
	private String shortDescription;
	@Lob
	@NotNull
	@Size(min=100, message="Artykuł musi posiadać przynajmniej 100 znaków")
	private String content;
	private String author;
	private String time;
	
	@ManyToMany
	@JoinTable(name="article_tag", 
	joinColumns= @JoinColumn(name="article_id"),
	inverseJoinColumns = @JoinColumn(name="tag_id"))
	private Set<Tag> tags = new HashSet<>();
	@Transient
	private Set<Long> tagsId = new HashSet<>();
	@OneToMany(cascade = CascadeType.ALL, mappedBy="article")
	private List<Comment> comments = new ArrayList<>();
	
	public void generateShortDescription() {
		if(content.length() >= 500) {
			setShortDescription(content.substring(0, 499)+"..."); 
		} else {
			setShortDescription(getContent()); 
		}
	}
	
	public void addNewComment(Comment comment) {
		this.comments.add(comment);
	}
	
	public void removeTagFromSet(Tag tag) {
		this.tags.remove(tag);
	}
	
	public void detachCommentFromArticle(Comment comment) {
		this.comments.remove(comment);
	}
	
	public Article() {
	}

	@Override
	public String toString() {
		return String.format("Article [id=%s, title=%s, shortDescription=%s, content=%s, author=%s, time=%s]", id,
				title, shortDescription, content, author, time);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
