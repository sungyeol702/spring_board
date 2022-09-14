package com.myspring.www.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@ComponentScan(basePackages = {"com.myspring.www.ctrl","com.myspring.www.handler"})
@EnableWebMvc
public class ServletConfigure  implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");	
		registry.addResourceHandler("/upload/**")
		.addResourceLocations("file:///C:/_java/lec/_spring/uploaded/");
		}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver viweResolver = new InternalResourceViewResolver();
		viweResolver.setViewClass(JstlView.class); // jstl방식셋팅
		viweResolver.setPrefix("/WEB-INF/views/");
		viweResolver.setSuffix(".jsp");
		registry.viewResolver(viweResolver);
	}
	//파일첨부를위한 빈
		@Bean(name = "multipartResolver")
		public MultipartResolver getMultipartResolver() throws IOException{
			StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
			return multipartResolver;
			
		}
	

}
