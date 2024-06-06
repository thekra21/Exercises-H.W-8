package org.example.helpers;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;
import org.example.dto.EmpSDDto;
import org.glassfish.jersey.internal.inject.ParamConverters;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class EmpSDDtoParamConverter implements ParamConverter {


    @Override
    public Object fromString(String value) {

        EmpSDDto empId = new EmpSDDto();
        empId.setDeptCode(value.substring(0, 2));
        empId.setIdSeq(Integer.parseInt(value.substring(2, 6)));
        empId.setHirYear(Integer.parseInt(value.substring(6)));

        return empId;
    }

    @Override
    public String toString(Object value) {
        return value.toString();
    }  



}
