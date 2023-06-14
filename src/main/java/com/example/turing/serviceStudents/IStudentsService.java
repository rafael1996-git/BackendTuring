package com.example.turing.serviceStudents;

import java.util.List;

import com.example.turing.entity.Students;

public interface IStudentsService {

	public boolean findByEmail(String u) throws Exception;

	public List<Students> findAll() throws Exception;

	public Students findById(Long id) throws Exception;

	public void delete(Long id) throws Exception;

	public Students save(Students u) throws Exception;

	public Students Edith(Students u) throws Exception;

}
