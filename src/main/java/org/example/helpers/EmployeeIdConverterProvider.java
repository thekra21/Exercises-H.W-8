package org.example.helpers;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;
import org.example.dto.EmpSDDto;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class EmployeeIdConverterProvider implements ParamConverterProvider {
    
    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (rawType.getName().equals(EmpSDDto.class.getName())) {
            return new EmpSDDtoParamConverter();
        }
        return null;
    }

}
