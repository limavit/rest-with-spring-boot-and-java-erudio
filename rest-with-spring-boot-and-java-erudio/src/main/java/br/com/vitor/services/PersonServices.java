package br.com.vitor.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import br.com.vitor.controllers.PersonController;
import br.com.vitor.data.vo.v1.PersonVO;
import br.com.vitor.data.vo.v2.PersonVOV2;
import br.com.vitor.exceptions.ResourceNotFoundException;
import br.com.vitor.mapper.DozerMapper;
import br.com.vitor.mapper.custom.PersonMapper;
import br.com.vitor.model.Person;
import br.com.vitor.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());

	@Autowired
	PersonRepository repository;
	@Autowired
	PersonMapper mapper;

	public List<PersonVO> findAll() {
		logger.info("Finding all people");
		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
		return persons;

	}

	public PersonVO findById(Long id) {

		
		logger.info("Finding one PersonVO");
		var entity =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no Records foud this ID!"));
		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo; 
	}

	public PersonVO createPersonVO(PersonVO p) {
		
		logger.info("Creating one PersonVO");
		var entity = DozerMapper.parseObject(p, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		logger.info("vo: " + vo.getFirstName() + " id: " );
		logger.info("entity: ");
		return vo;
	}
	
	public PersonVOV2 createPersonVOV2(PersonVOV2 p) {
		
		logger.info("Creating one Person with V2");
		var entity = mapper.convertVoToEntity(p);
		var vo = mapper.convertEntityToVo(repository.save(entity));
		return vo;
	}

	public PersonVO updatePersonVO(PersonVO p) {

		var entity = repository.findById(p.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("no Records foud this ID!"));

		entity.setFirstName(p.getFirstName());
		entity.setLastName(p.getLastName());
		entity.setAddress(p.getAddress());
		entity.setGender(p.getGender());
		
		logger.info("Updating one PersonVO");

		var vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public void deletePersonVO(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("no Records foud this ID!"));

		
		logger.info("Updating one PersonVO");
		repository.delete(entity);

	}

}
