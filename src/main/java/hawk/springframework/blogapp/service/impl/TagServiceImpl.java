package hawk.springframework.blogapp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hawk.springframework.blogapp.domain.Tag;
import hawk.springframework.blogapp.repository.TagRepository;
import hawk.springframework.blogapp.service.TagService;

@Service
public class TagServiceImpl implements TagService {

	private TagRepository tagRepository;

	@Autowired
	public TagServiceImpl(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	@Override
	public List <Tag> getAllTags() {
		List <Tag> tags = new ArrayList<>();
		tagRepository.findAll().iterator().forEachRemaining(tags::add);
		return tags;
	}
	
}
