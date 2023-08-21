package br.com.vitor.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import br.com.vitor.model.Person;
@Service
public class PersonServices {
	private final AtomicLong counter = new AtomicLong();
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	public List<Person> findAll() {
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
			//Log
			logger.info("Finding person id (i): " + i);
		}
		return persons;
	
	}
	
	public Person findById(String id) {
		
		//Mock
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Vitor");
		person.setLastName("Lima");
		person.setAddress("Araraquara - São Paulo - Brasil" );
		person.setGender("Male");
		//Log
		logger.info("Finding one person");
		logger.info(person.getId() + " | " + person.getFirstName() + " " );
		return person;
	}
	public Person createPerson(Person p) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName(p.getFirstName());
		person.setLastName(p.getLastName());
		person.setAddress(p.getAddress());
		person.setGender(p.getGender());
		//Log 
		logger.info("Creating one person");
		logger.info(person.getId().toString() + " | " + person.getFirstName());
		
		return person;
	}
	public Person updatePerson(Person p) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName(p.getFirstName());
		person.setLastName(p.getLastName());
		person.setAddress(p.getAddress());
		person.setGender(p.getGender());
		//Log 
		logger.info("Updating one person");
		logger.info(person.getId().toString() + " | " + person.getFirstName());
		
		return person;
	}
	public void deletePerson(String id) {
		
		//Log 
		logger.info("Updating one person");
		
		
	}
	
	private Person mockPerson(int i) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("Person name: " + i);
		person.setLastName("Person LastName: " + i);
		person.setAddress("Some Address: " + i );
		person.setGender("Male");
		return person;
	}
}
