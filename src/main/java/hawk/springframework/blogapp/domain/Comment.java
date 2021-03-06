package hawk.springframework.blogapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min=1, max=30, message="Nazwa z przedziału 1-30 znaków")
	private String userName;
	@NotNull
	@Size(min=10, max=200, message="Treść z przedziału 10-200 znaków")
	private String content;
	private String time;
	@ManyToOne
	private Article article;

	public Comment() {
	}

	@Override
	public String toString() {
		return String.format("Comment [id=%s, userName=%s, content=%s, time=%s, article=%s]", id, userName, content,
				time, article);
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
		Comment other = (Comment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
