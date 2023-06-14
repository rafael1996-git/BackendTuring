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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.example.turing.entity.Students;
import com.example.turing.models.Login;
import com.example.turing.models.Response;
import com.example.turing.serviceStudents.IStudentsService;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET,
		RequestMethod.DELETE })
@RestController
@EnableWebMvc
@RequestMapping("/api")
public class Controllers {

	/**
	 * Metodo que brinda el Crud
	 * 
	 * http://localhost:8081/api/show
	 * 
	 * @param /api
	 * @param /show
	 * @return retorna la lista
	 * @throws Exception si no logra obtener la conexion
	 * 
	 * 
	 *                   http://localhost:8081/api/delete/{id}
	 * @param /api
	 * @param /delete
	 * @return retorna la boolean
	 * @throws Exception si no logra obtener la conexion
	 * 
	 *                   http://localhost:8081/api/edith/{email}
	 * @param /api
	 * @param /edith
	 * @return retorna la lista
	 * @throws Exception si no logra obtener la conexion
	 * 
	 *                   http://localhost:8081/api/save
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
	Response response = new Response();
	List<Students> students;
	Students lista = null;

	@Autowired
	private IStudentsService service;

	@GetMapping("/Show")
	public ResponseEntity<?> All() {

		try {

			students = service.findAll();

		} catch (Exception e) {
			response.setItems(null);
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			dataResponse.put("response", response);
			return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		if (students == null) {
			response.setItems(null);
			response.setCode(HttpStatus.NOT_FOUND);
			response.setMessage("No hay Registros en la base de datos!");
			response.setSuccess(false);
			dataResponse.put("response", response);
			return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.NOT_FOUND);

		}
		response.setItems(students);
		response.setCode(HttpStatus.OK);
		response.setMessage("");
		response.setSuccess(true);
		dataResponse.put("response", response);
		return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.OK);
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Students u) {
		try {
			String email = u.getEmail();
			logger.info("id: " + email);
			if (service.findByEmail(email)) {
				response.setItems(null);
				response.setCode(HttpStatus.CONFLICT);
				response.setMessage("ya  hay Registros con ese email en la base de datos!");
				response.setSuccess(false);
				dataResponse.put("response", response);
				return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.CONFLICT);
			}
			service.save(u);
		} catch (Exception e) {
			response.setItems(null);
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			dataResponse.put("response", response);
			return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.setItems(null);
		response.setCode(HttpStatus.CREATED);
		response.setMessage("Registro creado con exito!");
		response.setSuccess(true);
		dataResponse.put("response", response);
		return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.CREATED);
	}

	@PutMapping("/edith/{email}")
	public ResponseEntity<?> edith(@PathVariable("email") String email, @RequestBody Students u) {
		Students newStudents = new Students();
		try {
			logger.info("dato: " + email);
			if (service.findByEmail(email)) {

				newStudents = service.existEmail(email);
				newStudents.setNombre(u.getNombre());
				newStudents.setApellido(u.getApellido());
				newStudents.setEmail(u.getEmail());
				newStudents.setFecha_nacimiento(u.getFecha_nacimiento());
				response.setItems(null);
				response.setCode(HttpStatus.OK);
				response.setMessage("registro actualizado con exito en  base de datos!");
				response.setSuccess(true);
				dataResponse.put("response", response);
				service.save(newStudents);
				return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.OK);
			} else {
				response.setItems(null);
				response.setCode(HttpStatus.NOT_FOUND);
				response.setMessage("el email no existe en la base de datos!");
				response.setSuccess(false);
				dataResponse.put("response", response);
				return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setItems(null);
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			dataResponse.put("response", response);
			return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> borrarTorre(@PathVariable("id") Long id) {
		try {
			logger.info("dato: " + id);
			if (service.findByIdStudents(id)) {

				response.setItems(null);
				response.setCode(HttpStatus.OK);
				response.setMessage("registro eliminado con exito de la  base de datos!");
				response.setSuccess(true);
				dataResponse.put("response", response);
				service.delete(id);
				return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.OK);
			} else {
				response.setItems(null);
				response.setCode(HttpStatus.NOT_FOUND);
				response.setMessage("el email no existe en la base de datos!");
				response.setSuccess(false);
				dataResponse.put("response", response);
				return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setItems(null);
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			dataResponse.put("response", response);
			return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@PostMapping("/login")
	public ResponseEntity<?> Login(@RequestBody Login u) {
		try {
			logger.info("dato: " + u);
			String email=u.getEmail();
			if (service.findByEmail(email)) {
				response.setItems(null);
				response.setCode(HttpStatus.OK);
				response.setMessage("Login Successful !");
				response.setSuccess(true);
				dataResponse.put("response", response);
				return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.OK);
			} else {
				response.setItems(null);
				response.setCode(HttpStatus.NOT_FOUND);
				response.setMessage("Login ERROR valida tus datos nuevamente!");
				response.setSuccess(false);
				dataResponse.put("response", response);
				return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setItems(null);
			response.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
			response.setMessage(e.getMessage());
			response.setSuccess(false);
			dataResponse.put("response", response);
			return new ResponseEntity<Map<String, Object>>(dataResponse, HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
}
