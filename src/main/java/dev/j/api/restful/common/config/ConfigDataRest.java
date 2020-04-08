package dev.j.api.restful.common.config;

import dev.j.api.restful.blog.vo.Category;
import dev.j.api.restful.blog.vo.Post;
import dev.j.api.restful.blog.vo.post.Content;
import dev.j.api.restful.blog.vo.post.Summary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class ConfigDataRest {

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer(){
        return new RepositoryRestConfigurer() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.setBasePath("/");
                config.exposeIdsFor(Post.class);
                config.exposeIdsFor(Category.class);
                config.exposeIdsFor(Summary.class);
                config.exposeIdsFor(Content.class);
            }
        };
    }
    
}