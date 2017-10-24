package hawk.springframework.blogapp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import hawk.springframework.blogapp.domain.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {
	Optional <Tag> findByName(String name);
}
