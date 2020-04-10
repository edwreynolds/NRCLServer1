package com.eprworld.nrcl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.eprworld.nrcl.controller.HomeController;
import com.eprworld.nrcl.util.VersionInfo;


/**
 * Project Creation Details:
 * Project contains: Spring Web MVC, Quartz Scheduler, spring-boot-devtools, and lombok
 * 		Java 8, Maven
 * 
 * Installed support for 'lombok' in Eclipse IDE
 * Open console window in M2 repo containing lombok jar file and:  java -jar lombok-###.jar
 * 
 * Using JSP, by default Spring Boot does not support that.  To enable it.
 * See file: SpringBootWithJSP.txt
 * 
 * Added new file logback-spring,xml to directory src/main/resources
 * 
 * Edited file src/main/resources/application.properties
 * 		Specify log config file, port nbr to use, some JSP config
 * 
 * Updated pom.xml to include webjars for bootstrap and jquery
 * 
 * 
 * Classifier Server App
 * 	# Java / Spring Boot app that interfaces over a network to two different
 * components.  On one interface this app acts as a TCP server on the other interface
 * its a TCP client.
 * # Prototype a 
 * Quartz scheduler to manage disk space usage, periodically deleting files
 * Use Google Protocol Buffers in exchange of messages with a C++ app
 * 
 * 
 * @author developer
 *
 */
@SpringBootApplication
public class NrclServer1Application {

	private static final Logger Log = LoggerFactory.getLogger(NrclServer1Application.class);
	
	public static void main(String[] args) {
		
		
		ApplicationContext applicationContext = SpringApplication.run(NrclServer1Application.class, args);
		Log.info("NrclServer1Application start, version={}", VersionInfo.appVersionNbr);
		System.out.println("NrclServer1Application startup, version="+VersionInfo.appVersionNbr);
		
//		String[] beanNames = applicationContext.getBeanDefinitionNames();
//		Arrays.sort(beanNames);
//		for (String beanName : beanNames) {
//			Log.info("Bean Name: {}",beanName);
//		}
		
		HomeController homeCtrl = (HomeController) applicationContext.getBean("homeController");
		System.out.println("URL:  http://localhost:"+homeCtrl.getAppHttpServerPortNbr());
		
//		SpringApplication.run(NrclServer1Application.class, args);
	}

}
