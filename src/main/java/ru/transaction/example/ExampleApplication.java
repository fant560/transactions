package ru.transaction.example;

import org.springframework.context.support.GenericXmlApplicationContext;
import ru.transaction.example.entity.Contact;
import ru.transaction.example.service.ContactService;

import java.util.List;


public class ExampleApplication {
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("classpath:context.xml");
		context.refresh();
		ContactService contactService = context.getBean("contactService",
				ContactService.class);
		contactService.findAll().forEach(System.out::println);

		Contact contact = contactService.findById(1L);
		contact.setFirstName("Peter");
		contactService.save(contact);
		System.out.println("Contact saved successfully: " + contact);
		System.out.println("Contact count: " + contactService.countAll());

	}
}
