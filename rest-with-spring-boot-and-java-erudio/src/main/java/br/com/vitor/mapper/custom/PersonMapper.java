package br.com.vitor.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.vitor.data.vo.v2.PersonVOV2;
import br.com.vitor.model.Person;

@Service
public class PersonMapper {
	public PersonVOV2 convertEntityToVo(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		vo.setId(person.getId());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setBirthDay(new Date());
		vo.setAddress(person.getAddress());
		vo.setGender(person.getGender());
		
		return vo;
	}
	
	public Person convertVoToEntity(PersonVOV2 personVOV2) {
		Person entity = new Person();
		entity.setId(personVOV2.getId());
		entity.setFirstName(personVOV2.getFirstName());
		entity.setLastName(personVOV2.getLastName());
		entity.setAddress(personVOV2.getAddress());
		entity.setGender(personVOV2.getGender());
		return entity;
	}

}
