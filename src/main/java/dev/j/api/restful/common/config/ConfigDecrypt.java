package dev.j.api.restful.common.config;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.j.api.restful.common.property.CommonProperties;


@Configuration
public class ConfigDecrypt {

    @Value("${ENC_PWD}")
	String encryptorPassword;

    @Bean
	public StandardPBEStringEncryptor decryptService() {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

		EnvironmentPBEConfig config = new EnvironmentPBEConfig();
		config.setAlgorithm(CommonProperties.CONFIG_KEY_ENC_ALGORITHM);
        config.setPassword(encryptorPassword);
		encryptor.setConfig(config);
		
		return encryptor;
	}
    
}