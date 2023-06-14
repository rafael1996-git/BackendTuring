package com.example.turing.serviceStudentsimplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.turing.entity.Students;
import com.example.turing.serviceDao.IStudents;
import com.example.turing.serviceStudents.IStudentsService;


@Service
@Transactional
public class StudentsImplement implements IStudentsService{
	
	@Autowired
	private IStudents repository;

	@Override
	public boolean findByEmail(String u)  throws Exception{
		return repository.existsByEmail(u);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Students> findAll()  throws Exception{
		return (List<Students>) repository.findAll();
	}


	@Override
	@Transactional(readOnly = true)
	public Students findById(Long id)  throws Exception{
		return repository.findById(id).orElse(null);
	}

	@Override
	public void delete(Long id)  throws Exception{
		repository.deleteById(id);;
	}

	@Override
	public Students save(Students u)  throws Exception{
		return repository.save(u);
	}

	@Override
	public Students Edith(Students u) throws Exception{
		return repository.save(u);
	}
	
	

}
