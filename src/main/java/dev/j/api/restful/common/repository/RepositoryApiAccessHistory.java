package dev.j.api.restful.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import dev.j.api.restful.common.vo.ApiAccessHistory;

@RepositoryRestResource(path = "acc")
public interface RepositoryApiAccessHistory extends JpaRepository<ApiAccessHistory, Integer>, JpaSpecificationExecutor<ApiAccessHistory>{

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    List<ApiAccessHistory> findAll(); 

    @PreAuthorize("hasRole('ROLE_ADMIN')")
	@Override
	void delete(ApiAccessHistory apiAccessHistory);
    
}