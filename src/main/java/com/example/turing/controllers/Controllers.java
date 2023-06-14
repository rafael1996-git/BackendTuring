package com.example.turing.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.turing.entity.Students;
import com.example.turing.models.Response;
import com.example.turing.serviceStudents.IStudentsService;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST,RequestMethod.PUT,RequestMethod.GET,RequestMethod.DELETE })
@RestController
@EnableWebMvc
@RequestMapping("/api")
public class Controllers {

	/**
	 * Metodo que brinda el Crud
	 * 
	 * http://localhost:8081/api/show
	 * @param /api
	 * @param /show 
	 * @return retorna la lista 
	 * @throws Exception si no logra obtener la conexion
	 * 
	 * 
	 * http://localhost:8081/api/delete
	 * @param /api
	 * @param /delete 
	 * @return retorna la boolean 
	 * @throws Exception si no logra obtener la conexion
	 * 
	 * http://localhost:8081/api/edith
	 * @param /api
	 * @param /edith 
	 * @return retorna la lista 
	 * @throws Exception si no logra obtener la conexion
	 * 
	 * http://localhost:8081/api/save
	 * @param /api
	 * @param /lista 
	 * @return retorna la lista 
	 * @throws Exception si no logra obtener la conexion
	 * 
	 * 
	 * @author Maria Elena Quinto Zagal
	 * 
	 */
	
	
	private final Logger logger = LoggerFactory.getLogger(Controllers.class);
	Map<String, Object> dataResponse = new HashMap<>();
	Response response=new Response();
	List<Students>  empleado;
	@Autowired
	private IStudentsService service;
	  
	   @GetMapping("/Show")
	   public ResponseEntity<?> All() {

			try {

			empleado = service.findAll();
				
			} catch (Exception e) {
				response.setItems(null);
				response.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
				response.setMessage(e.getMessage());
				response.setSuccess(false);
				dataResponse.put("response", response);
				return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.INTERNAL_SERVER_ERROR);

			}
			if (empleado == null) {
				response.setItems(null);
				response.setCode(HttpStatus.NOT_FOUND);
				response.setMessage("No hay Registros en la base de datos!");
				response.setSuccess(false);
				dataResponse.put("response", response);
				return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.NOT_FOUND);

			}
			response.setItems(empleado);
			response.setCode(HttpStatus.OK);
			response.setMessage("");
			response.setSuccess(true);
			dataResponse.put("response", response);
			return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.OK);
		}
	
	 
}
