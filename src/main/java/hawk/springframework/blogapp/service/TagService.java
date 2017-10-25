package hawk.springframework.blogapp.service;

import java.util.List;

import hawk.springframework.blogapp.domain.Tag;

public interface TagService {
	List <Tag> getAllTags();
	Tag saveTag(Tag tag);
}
