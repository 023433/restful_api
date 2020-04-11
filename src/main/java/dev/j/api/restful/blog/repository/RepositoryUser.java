package dev.j.api.restful.blog.repository;

import dev.j.api.restful.blog.vo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryUser extends JpaRepository<User, String>, JpaSpecificationExecutor<User>{

}