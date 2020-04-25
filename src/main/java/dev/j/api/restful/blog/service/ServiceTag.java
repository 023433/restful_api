package dev.j.api.restful.blog.service;

import dev.j.api.restful.blog.repository.tag.RepositoryTag;
import dev.j.api.restful.blog.vo.post.tag.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceTag extends AbstractService{

    @Autowired
    private RepositoryTag repositoryTag;
	public List<Tag> getTags() {
		return repositoryTag.findAll();
	}

}