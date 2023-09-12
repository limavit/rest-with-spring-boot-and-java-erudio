package br.com.vitor.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vitor.data.vo.v1.PersonVO;
import br.com.vitor.data.vo.v2.PersonVOV2;
import br.com.vitor.services.PersonServices;

@RestController
@RequestMapping("/person")
public class PersonController {
	@Autowired
	private PersonServices service = new PersonServices();

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE 
	})
	//Com @GetMapping não precisa mais dizer o que produz e consome, porem para o swagger é necessário
	//com @RequestMapping(method = RequestMethod.Get precisa colocar o que consome e o que produz
	public List<PersonVO> findAll() {

		return service.findAll();

	}

	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE 
	})
	public PersonVO findById(@PathVariable(value = "id") Long id) {

		return service.findById(id);

	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE 
	}, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE 
	})
	public PersonVO create(@RequestBody PersonVO person) {

		return service.createPersonVO(person);

	}
	@PostMapping(value ="/v2",consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE 
	}, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE 
	})
	public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {

		return service.createPersonVOV2(person);

	}

	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE 
	}, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE 
	})
	public PersonVO update(@RequestBody PersonVO person) {

		return service.updatePersonVO(person);

	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteById(@PathVariable(value = "id") Long id) {

		service.deletePersonVO(id);
		return ResponseEntity.noContent().build();

	}

}
