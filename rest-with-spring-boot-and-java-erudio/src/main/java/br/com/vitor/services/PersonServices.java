package br.com.vitor.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);

	}

	public PersonVO findById(Long id) {

		// Log
		logger.info("Finding one PersonVO");
		var entity =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no Records foud this ID!"));
		return DozerMapper.parseObject(entity, PersonVO.class);
	}

	public PersonVO createPersonVO(PersonVO p) {
		// Log
		logger.info("Creating one PersonVO");
		var entity = DozerMapper.parseObject(p, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public PersonVOV2 createPersonVOV2(PersonVOV2 p) {
		// Log
		logger.info("Creating one Person with V2");
		var entity = mapper.convertVoToEntity(p);
		var vo = mapper.convertEntityToVo(repository.save(entity));
		return vo;
	}

	public PersonVO updatePersonVO(PersonVO p) {

		var entity = repository.findById(p.getId())
				.orElseThrow(() -> new ResourceNotFoundException("no Records foud this ID!"));

		entity.setFirstName(p.getFirstName());
		entity.setLastName(p.getLastName());
		entity.setAddress(p.getAddress());
		entity.setGender(p.getGender());
		// Log
		logger.info("Updating one PersonVO");

		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}

	public void deletePersonVO(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("no Records foud this ID!"));

		// Log
		logger.info("Updating one PersonVO");
		repository.delete(entity);

	}

}
