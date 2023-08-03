package com.medicalcenter.roleservice.beans;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BeanFactory {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
