package com.codingdojo.tasks.validators;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.codingdojo.tasks.models.Task;

@Component
public class TaskValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Task.class.equals(clazz);
    }
    
    
    @Override
    public void validate(Object target, Errors errors) {
        Task task = (Task) target; 
    	if (task.getStart_date().after(task.getEnd_date())) {
            errors.rejectValue("end_date", "Match");
        }
    }
}
