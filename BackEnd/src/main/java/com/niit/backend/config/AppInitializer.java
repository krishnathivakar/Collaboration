/*package com.niit.backend.config;

import java.io.File;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//web.xml - Java based configuration.
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.niit.collaboration")
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	
	@Override
	protected Class[] getRootConfigClasses() {
		
		return new Class[] { ApplicationContextConfig.class, WebSocketConfig.class };
	}

	@Override
	protected Class[] getServletConfigClasses() {
		
		return new Class[] { ApplicationContextConfig.class, WebSocketConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] { "/" };
	}
	
	private int maxUploadSizeInMb = 5 * 1024 * 1024; // 5 MB
	@Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		
		//String path = "resources/img/";
		String path = "D://Collaboration_images";
        File uploadDirectory = new File(path);

        // register a MultipartConfigElement
        MultipartConfigElement multipartConfigElement =
                new MultipartConfigElement(uploadDirectory.getAbsolutePath(),
                        maxUploadSizeInMb, maxUploadSizeInMb * 2, maxUploadSizeInMb / 2);

        registration.setMultipartConfig(multipartConfigElement);
       
    }

	//To use request scope without Spring MVC, you should declare RequestContextListener in web.xml 
	public void onStartup(ServletContext servletContext) throws ServletException {
        //add listener
        servletContext.addListener(new RequestContextListener());
	}
}*/