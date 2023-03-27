package com.usuario.service.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.usuario.service.entidades.Usuario;
import com.usuario.service.feingClients.CocheFeignClient;
import com.usuario.service.feingClients.MotoFeignClient;
import com.usuario.service.model.Coche;
import com.usuario.service.model.Moto;
import com.usuario.service.repositorio.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private RestTemplate template;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CocheFeignClient cocheFeing;
	
	@Autowired
	private MotoFeignClient motoFeing;
	
	
	
	public List<Usuario> getAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario getUsuarioById(int id){
		return usuarioRepository.findById(id).orElse(null);
	}
	
	public Usuario save(Usuario user) {
		Usuario newUser = usuarioRepository.save(user);
		return newUser;
	}
	
	public List<Coche> getCoches(int userId){
		List<Coche> cars = template.getForObject("http://localhost:8002/coche/usuario/" + userId, List.class);
		return cars;
	}
	
	public List<Moto> getMotos(int userId){
		List<Moto> motos = template.getForObject("http://localhost:8003/moto/usuario/" + userId, List.class);
		return motos;
	}
	
	public Coche saveCoche(int userId,Coche car){
		car.setUsuarioId(userId);
		Coche newcar = cocheFeing.save(car);
		return newcar;
	}
	
	public Moto saveMoto(int userId,Moto moto){
		moto.setUsuarioId(userId);
		Moto newmoto = motoFeing.save(moto);
		return newmoto;
	}
	
	public Map<String, Object> getUsuarioVehiculos(int usuarioId){
		Map<String, Object> result = new HashMap<>();
		Usuario user = usuarioRepository.findById(usuarioId).orElse(null);
		if (user == null) {
			result.put("Mensaje", "El usuario no existe");
			return result;
		}
		result.put("Usuario", user);
		List<Coche> cars = cocheFeing.getCoches(usuarioId);
		if (cars.isEmpty())
			result.put("Coches", "El usuario no tiene coches.");
		else
			result.put("Coches", cars);
		List<Moto> motos = motoFeing.getMotos(usuarioId);
		if (motos.isEmpty())
			result.put("Motos", "El usuario no tiene motos.");
		else
			result.put("Motos", motos);
		return result;
	}
	
}
