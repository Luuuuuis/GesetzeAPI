/*
 * Developed by Luuuuuis (@realluuuuuis)
 * Last modified 10.12.21, 17:41.
 * Copyright (c) 2021.
 */

package de.luuuuuis.gesetzeapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
//@ComponentScan(basePackages = "de.luuuuuis.gesetzeapi")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**", "/docs/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/docs/");
        registry
                .addViewController("/docs/")
                .setViewName("forward:/docs/index.html");
    }
}
