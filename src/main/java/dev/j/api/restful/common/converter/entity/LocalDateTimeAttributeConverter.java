package dev.j.api.restful.common.converter.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.util.StringUtils;

@Converter
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, String>{

	@Override
	public String convertToDatabaseColumn(LocalDateTime attribute) {
        if(attribute == null) {
            return "";
        } else {
            return attribute.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        }
	}

	@Override
	public LocalDateTime convertToEntityAttribute(String dbData) {
        if(StringUtils.isEmpty(dbData)) {
            return null;
        } else {
            return LocalDateTime.parse(dbData, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        }
	}



    
}