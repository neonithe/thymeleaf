package se.lexicon.mattias.thymeleaf.service;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.mattias.thymeleaf.model.Student;

import java.util.List;


public interface StudentDAO extends JpaRepository<Student, Integer> {

    List<Student> findAll();

    /** Order list **/
    List<Student> findAllByOrderByNameAsc();
    List<Student> findAllByOrderByNameDesc();
    List<Student> findAllByOrderByIdAsc();

    /** Find By **/
    List<Student> findAllByName(String name);
    List<Student> findAllByEmail(String email);

}
