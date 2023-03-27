package com.coche.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coche.service.entidades.Coche;
import com.coche.service.servicio.CocheServicio;


@RestController
@RequestMapping("/coche")
public class CocheController {
	
	@Autowired
	private CocheServicio carService;
	
	@GetMapping
	public ResponseEntity<List<Coche>> listarCoches(){
		List<Coche> cars = carService.getAll();
		if(cars.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(cars);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Coche> obtenerCoche(@PathVariable("id")int id){
		Coche car = carService.getCocheById(id);
		if(car == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(car);
	}
	
	@PostMapping
	public ResponseEntity<Coche> guardarCoche(@RequestBody Coche car){
		Coche newcar = carService.save(car);
		return ResponseEntity.ok(newcar);
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<List<Coche>> listarCochesByUserId(@PathVariable("id")int id){
		List<Coche> cars = carService.getbyUsuarioId(id);
		if(cars.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(cars);
	}

}
