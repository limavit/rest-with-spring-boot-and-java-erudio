package br.com.vitor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vitor.model.Book;
@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
