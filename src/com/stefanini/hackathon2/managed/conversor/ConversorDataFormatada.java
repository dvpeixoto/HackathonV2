package com.stefanini.hackathon2.managed.conversor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="conversorDataFormatada")
public class ConversorDataFormatada implements Converter {

 @Override
 public Object getAsObject(FacesContext arg0, UIComponent arg1, String dataString) {
  return LocalDateTime.parse(dataString);
 }

 @Override
 public String getAsString(FacesContext arg0, UIComponent arg1, Object localDateUnformated) {
  LocalDateTime Date = (LocalDateTime) localDateUnformated;
  return Date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
 }
}
