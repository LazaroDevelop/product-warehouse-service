package net.developer.space.productwarehouseservice;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


@SpringBootApplication
public class ProductWarehouseServiceApplication {

	String mockXMLBean = "applicationContext.xml";

	//Old way using BeanFactory
	Resource resource = new ClassPathResource(mockXMLBean);
	BeanFactory beanFactory = new XmlBeanFactory(resource);

	String myBean = (String) beanFactory.getBean("myBean");

	//Standard Way using ApplicationContext
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext(mockXMLBean);
	A a = applicationContext.getBean("a", A.class);
	a.display();



	public static void main(String[] args) {
		SpringApplication.run(ProductWarehouseServiceApplication.class, args);
	}

}
