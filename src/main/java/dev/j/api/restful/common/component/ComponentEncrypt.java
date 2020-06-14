package dev.j.api.restful.common.component;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.j.api.restful.common.config.ConfigDecrypt;
import dev.j.api.restful.common.property.PropertyEncrypt;

@Component
public class ComponentEncrypt {

  @Autowired
  private ConfigDecrypt configDecrypt;

  public String encrypt(String string) {
    return configDecrypt.decryptService().encrypt(string);
  }

  public String decrypt(String string) {
    return configDecrypt.decryptService().decrypt(string);
  }

  public String encrypt(String string, StandardPBEStringEncryptor encryptor) {
    return encryptor.encrypt(string);
  }

  public String decrypt(String string, StandardPBEStringEncryptor encryptor) {
    return encryptor.decrypt(string);
  }

  public boolean matches(String rawString, String encodedString) {
    return rawString.equals(decrypt(encodedString));
  }

  public boolean matchesMd5(String rawString, String encodedString) {
    return encryptMd5(rawString).equals(encodedString);
  }

  public StandardPBEStringEncryptor decryptService(String encryptorPassword) {
    StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

    EnvironmentPBEConfig config = new EnvironmentPBEConfig();
    config.setAlgorithm(PropertyEncrypt.ENC_ALGORITHM);
    config.setPassword(encryptorPassword);
    encryptor.setConfig(config);

    return encryptor;
  }

  public String encryptMd5(String sourceString) {
    try {

      String result = sourceString;
      if(sourceString != null) {
        MessageDigest md = MessageDigest.getInstance("MD5"); //or "SHA-1"
        md.update(sourceString.getBytes());
        BigInteger hash = new BigInteger(1, md.digest());
        result = hash.toString(16);
        while(result.length() < 32) { //40 for SHA-1
          result = "0" + result;
        }
      }
      return result;
    } catch (Exception e) {
      return null;
    }
  }
	
}