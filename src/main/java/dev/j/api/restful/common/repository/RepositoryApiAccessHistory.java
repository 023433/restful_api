package dev.j.api.restful.common.repository;

import dev.j.api.restful.common.vo.ApiAccessHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

@RepositoryRestResource
@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
public interface RepositoryApiAccessHistory extends JpaRepository<ApiAccessHistory, Integer>, JpaSpecificationExecutor<ApiAccessHistory>{

}