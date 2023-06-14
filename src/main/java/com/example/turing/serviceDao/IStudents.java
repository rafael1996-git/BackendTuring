package com.example.turing.serviceDao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.turing.entity.Students;

@Repository
public interface IStudents extends CrudRepository<Students, Long> {
	boolean existsByEmail(String nombreTorre)  throws Exception;
}
