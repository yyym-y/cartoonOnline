package org.yyym.back.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourcesConfig implements WebMvcConfigurer
{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/cover/**")
                .addResourceLocations("file:/E:/sources/cartoon_online/cover/");
        registry.addResourceHandler("/carousel/**")
                .addResourceLocations("file:/E:/sources/cartoon_online/carousel/");
        registry.addResourceHandler("/video/**")
                .addResourceLocations("file:/E:/sources/cartoon_online/video/");
    }

}
