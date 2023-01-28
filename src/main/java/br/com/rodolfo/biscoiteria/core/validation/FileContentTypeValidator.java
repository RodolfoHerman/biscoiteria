package br.com.rodolfo.biscoiteria.core.validation;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

    private List<String> allowedList;

    @Override
    public void initialize(FileContentType constraintAnnotation) {
        this.allowedList = Arrays.asList(constraintAnnotation.allowed());
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        return Objects.isNull(value) || allowedList.contains(value.getContentType());
    }
}
