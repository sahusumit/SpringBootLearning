package com.example.springbootlearning.controllerandMapping;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePropertyEditor extends PropertyEditorSupport {
    private final SimpleDateFormat dateFormat;


    public DatePropertyEditor(String dateFormat) {
        this.dateFormat = new SimpleDateFormat(dateFormat);
        this.dateFormat.setLenient(false); // Disable leniency for strict parsing
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException{
        try {
            Date date = dateFormat.parse(text);
            setValue(date);
        }catch (ParseException ex){
            throw new IllegalArgumentException("Invalid date format. Expected format: " + dateFormat.toPattern(), ex);
        }
    }

    @Override
    public String getAsText() {
        Date value = (Date) getValue();
        return (value != null) ? dateFormat.format(value) : "";
    }

}
