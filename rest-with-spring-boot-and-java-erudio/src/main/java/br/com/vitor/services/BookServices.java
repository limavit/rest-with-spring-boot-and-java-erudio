package br.com.vitor.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vitor.controllers.BookController;
import br.com.vitor.data.vo.v1.BookVO;
import br.com.vitor.exceptions.RequiredObjectIsNullException;
import br.com.vitor.exceptions.ResourceNotFoundException;
import br.com.vitor.mapper.DozerMapper;
import br.com.vitor.model.Book;
import br.com.vitor.repositories.BookRepository;

@Service
public class BookServices {

	private Logger logger = Logger.getLogger(BookServices.class.getName());

	@Autowired
	BookRepository repository;
	

	public List<BookVO> findAll() {
		logger.info("Finding all book");
		var Books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
		Books.stream().forEach(p -> p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel()));
		return Books;

	}

	public BookVO findById(Long id) {

		
		logger.info("Finding one BookVO");
		var entity =  repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("no Records foud this ID!"));
		var vo = DozerMapper.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo; 
	}

	public BookVO createBookVO(BookVO p) {
		
		if (p == null) throw new RequiredObjectIsNullException();		
		logger.info("Creating one BookVO");
		var entity = DozerMapper.parseObject(p, Book.class);
		var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		logger.info("vo: " + vo.getTitle() + " id: " );
		logger.info("entity: ");
		return vo;
	}
	
	

	public BookVO updateBookVO(BookVO p) {
		if (p == null) throw new RequiredObjectIsNullException();

		var entity = repository.findById(p.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("no Records foud this ID!"));

		entity.setAuthor(p.getAuthor());;
		entity.setLaunchDate(p.getLaunchDate());;
		entity.setPrice(p.getPrice());;
		entity.setTitle(p.getTitle());;
		
		logger.info("Updating one BookVO");

		var vo = DozerMapper.parseObject(entity, BookVO.class);
		vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}

	public void deleteBookVO(Long id) {
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("no Records foud this ID!"));

		
		logger.info("Updating one BookVO");
		repository.delete(entity);

	}

}
