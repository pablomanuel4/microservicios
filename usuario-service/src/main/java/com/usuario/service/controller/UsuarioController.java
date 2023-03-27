package com.usuario.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.model.Coche;
import com.usuario.service.model.Moto;
import com.usuario.service.servicio.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios(){
		List<Usuario> usuarios = usuarioService.getAll();
		if(usuarios.isEmpty())
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(usuarios);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id")int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping
	public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario user){
		Usuario newUser = usuarioService.save(user);
		return ResponseEntity.ok(newUser);
	}
	
	@GetMapping("/coches/{usuarioId}")
	public ResponseEntity<List<Coche>> listarCoches(@PathVariable("usuarioId")int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null)
			return ResponseEntity.notFound().build();
		List<Coche> coches = usuarioService.getCoches(id);
		return ResponseEntity.ok(coches);
	}
	
	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>> listarMotos(@PathVariable("usuarioId")int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null)
			return ResponseEntity.notFound().build();
		List<Moto> motos = usuarioService.getMotos(id);
		return ResponseEntity.ok(motos);
	}
	
	@PostMapping("/coche/{usuarioId}")
	public ResponseEntity<Coche> guardarCoche(@PathVariable("usuarioId")int id,@RequestBody Coche car){
		Coche newcar = usuarioService.saveCoche(id, car);
		return ResponseEntity.ok(newcar);
	}
	
	@PostMapping("/moto/{usuarioId}")
	public ResponseEntity<Moto> guardarMoto(@PathVariable("usuarioId")int id,@RequestBody Moto moto){
		Moto newcar = usuarioService.saveMoto(id, moto);
		return ResponseEntity.ok(newcar);
	}
	
	@GetMapping("/vehiculos/{usuarioId}")
	public ResponseEntity<Map<String,Object>> listarVehiculos(@PathVariable("usuarioId")int id){
		Usuario usuario = usuarioService.getUsuarioById(id);
		if(usuario == null)
			return ResponseEntity.notFound().build();
		Map<String,Object> vehiculos = usuarioService.getUsuarioVehiculos(id);
		return ResponseEntity.ok(vehiculos);
	}
}
