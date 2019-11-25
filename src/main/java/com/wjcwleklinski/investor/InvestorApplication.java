package com.wjcwleklinski.investor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@SpringBootApplication
public class InvestorApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvestorApplication.class, args);
	}

//
//	@Bean
//	@Primary
//	public Formatter<LocalDate> localDateFormatter() {
//		return new LocalDateFormatter();
//	}

//	@Bean
//	public Formatter<LocalDate> localDateFormatter() {
//		return new Formatter<LocalDate>() {
//			@Override
//			public LocalDate parse(String text, Locale locale) throws ParseException {
//				return LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
//			}
//
//			@Override
//			public String print(LocalDate object, Locale locale) {
//				return DateTimeFormatter.ISO_DATE.format(object);
//			}
//		};
//	}
//
//	@Bean
//	public ObjectMapper objectMapper() {
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//		objectMapper.registerModule(new JavaTimeModule());
//		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//
//		return objectMapper;
//	}

}
