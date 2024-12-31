package com.example.springbootlearning.controllerandMapping;

import java.beans.PropertyEditorSupport;


public class StringDataPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(text.trim().toLowerCase());

    }

}
