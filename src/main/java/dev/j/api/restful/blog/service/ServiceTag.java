package dev.j.api.restful.blog.service;

import dev.j.api.restful.blog.repository.post.tag.RepositoryTag;
import dev.j.api.restful.blog.vo.post.tag.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service
public class ServiceTag extends AbstractService{

    @Autowired
	private RepositoryTag repositoryTag;
	
	public List<Tag> getTags() {
		return repositoryTag.findAll();
	}

	public Page<Tag> getTags(String pageNo, String pageSize) {
		int page = Integer.parseInt(pageNo);
		int size = Integer.parseInt(pageSize);
		Sort sort = Sort.by(Order.desc("no"));
		
		Pageable pageable = PageRequest.of(page, size, sort);

		return repositoryTag.findAll(pageable);
	}

}