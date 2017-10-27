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

@Entity
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
	@Transient
	private Set<Long> tagsId = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="article")
	private List<Comment> comments = new ArrayList<>();

	public void removeTagFromSet(Tag tag) {
		this.tags.remove(tag);
	}
	
	public void detachCommentFromArticle(Comment comment) {
		this.comments.remove(comment);
	}
	
	public Article() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public Set<Long> getTagsId() {
		return tagsId;
	}

	public void setTagsId(Set<Long> tagsId) {
		this.tagsId = tagsId;
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

	public void addNewComment(Comment comment) {
		this.comments.add(comment);
	}
	
}
