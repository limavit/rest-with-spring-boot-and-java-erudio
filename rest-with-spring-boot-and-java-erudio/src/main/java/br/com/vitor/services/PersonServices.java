package br.com.vitor.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vitor.exceptions.ResourceNotFoundException;
import br.com.vitor.model.Person;
import br.com.vitor.repository.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository repository;

	public List<Person> findAll() {
		logger.info("Finding all people");
		return repository.findAll();

	}

	public Person findById(Long id) {

		// Log
		logger.info("Finding one person");
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no Records foud this ID!"));
	}

	public Person createPerson(Person p) {
		// Log
		logger.info("Creating one person");

		return repository.save(p);
	}

	public Person updatePerson(Person p) {

		var entity = repository.findById(p.getId())
				.orElseThrow(() -> new ResourceNotFoundException("no Records foud this ID!"));

		entity.setFirstName(p.getFirstName());
		entity.setLastName(p.getLastName());
		entity.setAddress(p.getAddress());
		entity.setGender(p.getGender());
		// Log
		logger.info("Updating one person");

		return repository.save(entity);
	}

	public void deletePerson(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("no Records foud this ID!"));

		// Log
		logger.info("Updating one person");
		repository.delete(entity);;

	}

}
