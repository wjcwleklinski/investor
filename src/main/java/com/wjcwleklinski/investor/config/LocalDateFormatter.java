//package com.wjcwleklinski.investor.config;
//
//import org.springframework.format.Formatter;
//import org.springframework.stereotype.Component;
//
//import java.text.ParseException;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Locale;
//
//public class LocalDateFormatter implements Formatter<LocalDate> {
//
//    private static final String dateFormat = "dd-MM-yyyy";
//
//    @Override
//    public LocalDate parse(String s, Locale locale) throws ParseException {
//        return LocalDate.parse(s, DateTimeFormatter.ISO_DATE);
//    }
//
//    @Override
//    public String print(LocalDate localDate, Locale locale) {
//        return DateTimeFormatter.ISO_DATE.format(localDate);
//    }
//}
