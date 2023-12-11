package br.com.vitor.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.vitor.data.vo.v1.PersonVO;
import br.com.vitor.model.Person;
import br.com.vitor.repositories.PersonRepository;
import br.com.vitor.services.PersonServices;
import br.com.vitor.unittests.mapper.mocks.MockPerson;
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {
	
	MockPerson input;
	@InjectMocks
	private PersonServices service;
	
	@Mock
	PersonRepository repository;

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this);
		
	}
	@Test
	void testFindById() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		var result = service.findById(1L);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
		assertEquals("Addres Test", result.getAddress());
		assertEquals("First Name Test", result.getFirstName());
		assertEquals("Last Name Test", result.getLastName());
		assertEquals("Female", result.getGender());
		
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	

	@Test
	void testCreatePersonVO() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		Person pesisted = entity;
		pesisted.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L); 
		
		when(repository.save(entity)).thenReturn(pesisted);
		
		var result = service.createPersonVO(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
		assertEquals("Addres Test", result.getAddress());
		assertEquals("First Name Test", result.getFirstName());
		assertEquals("Last Name Test", result.getLastName());
		assertEquals("Female", result.getGender());
		
	}

	@Test
	void testCreatePersonVOV2() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdatePersonVO() {
		Person entity = input.mockEntity(1);
		
		Person pesisted = entity;
		pesisted.setId(1L);
		
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L); 
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		when(repository.save(entity)).thenReturn(pesisted);
		
		var result = service.updatePersonVO(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</person/1>;rel=\"self\"]"));
		assertEquals("Addres Test", result.getAddress());
		assertEquals("First Name Test", result.getFirstName());
		assertEquals("Last Name Test", result.getLastName());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testDeletePersonVO() {
		Person entity = input.mockEntity(1);
		entity.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		service.deletePersonVO(1L);
	}

}
