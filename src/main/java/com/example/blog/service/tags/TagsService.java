package com.example.blog.service.tags;

import com.example.blog.repository.repository.TagsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagsService {
    private TagsRepository tagsRepository;

    public TagsService(TagsRepository tagsRepository) {
        this.tagsRepository = tagsRepository;
    }

    public List<String> findAllTags() {
        return tagsRepository.findAllTags().orElse(null);
    }
}
