package br.com.vitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vitor.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
