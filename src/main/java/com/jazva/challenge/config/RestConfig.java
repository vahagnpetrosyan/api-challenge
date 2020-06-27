package com.jazva.challenge.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * The type Rest config.
 */
@Configuration
public class RestConfig extends WebMvcConfigurerAdapter {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        final Optional<HttpMessageConverter<?>> optConverter = converters
                .stream()
                .filter(c -> c instanceof AbstractJackson2HttpMessageConverter).findFirst();

        optConverter.ifPresent(c -> {
            final AbstractJackson2HttpMessageConverter converter =
                    (AbstractJackson2HttpMessageConverter) c;
            converter.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            converter.getObjectMapper().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            converter.getObjectMapper().disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            converter.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        });
    }
}
