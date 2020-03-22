package dev.j.api.restful.common.converter.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.util.StringUtils;

@Converter
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, String>{

	@Override
	public String convertToDatabaseColumn(LocalDate attribute) {
        if(attribute == null) {
            return "";
        } else {
            return attribute.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
	}

	@Override
	public LocalDate convertToEntityAttribute(String dbData) {
        if(StringUtils.isEmpty(dbData)) {
            return null;
        } else {
            return LocalDate.parse(dbData, DateTimeFormatter.ISO_LOCAL_DATE);
        }
	}



    
}