package com.moto.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moto.service.entidades.Moto;
import com.moto.service.servicio.MotoServicio;


@RestController
@RequestMapping("/moto")
public class MotoController {
	
	@Autowired
	private MotoServicio motoService;
	
	@GetMapping
	public ResponseEntity<List<Moto>> listarMotos(){
		List<Moto> motos = motoService.getAll();
		if(motos.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(motos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Moto> obtenerMoto(@PathVariable("id")int id){
		Moto moto = motoService.getCocheById(id);
		if(moto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(moto);
	}
	
	@PostMapping
	public ResponseEntity<Moto> guardarMoto(@RequestBody Moto moto){
		Moto newmoto = motoService.save(moto);
		return ResponseEntity.ok(newmoto);
	}
	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<List<Moto>> listarMotosByUserId(@PathVariable("id")int id){
		List<Moto> motos = motoService.getbyUsuarioId(id);
		if(motos.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(motos);
	}

}
