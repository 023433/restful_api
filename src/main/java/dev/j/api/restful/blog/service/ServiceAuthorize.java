package dev.j.api.restful.blog.service;

import dev.j.api.restful.blog.repository.RepositoryUser;
import dev.j.api.restful.blog.vo.User;
import dev.j.api.restful.common.component.ComponentEncrypt;
import dev.j.api.restful.common.component.ComponentJwtToken;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceAuthorize {

	@Autowired
	private ComponentJwtToken componentJwtToken;

	@Autowired
	private ComponentEncrypt componentEncrypt;

	@Autowired
	private RepositoryUser repositoryUser;

	public String createJwtToken(String userId) {
		return componentJwtToken.createToken(userId);
	}

	public String getJwtToken(String userId, String userPw) {
		Optional<User> userOp = repositoryUser.findById(userId);

		if(userOp.isEmpty()){
			return null;
		}

		User user = userOp.get();

		String savedUserPw = user.getUserPw();

		if(componentEncrypt.matches(userPw, savedUserPw)){
			List<String> roles = user.getRoles();
			return componentJwtToken.createToken(userId, roles);
		}

		return null;
	}

}
