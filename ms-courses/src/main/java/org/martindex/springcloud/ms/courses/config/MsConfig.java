package org.martindex.springcloud.ms.courses.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MsConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

