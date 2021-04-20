
package com.serpoworks.gamesclub.service;

import com.serpoworks.gamesclub.domain.Videojuego;
import com.serpoworks.gamesclub.repository.VideojuegoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

//En Spring una clase que contiene logica propia del Negocio de nuestra aplicación se le etiqueta con @Service 
@Service
public class VideojuegosService {
    
    private final VideojuegoRepository videojuegoRepository;

    public VideojuegosService(VideojuegoRepository videojuegoRepository) {
        this.videojuegoRepository = videojuegoRepository;
    }
    
    public List<Videojuego> buscarDestacados(){
            
        //findAll es un metodo ya prehecho que busca todos los de la tabla Videojuego.
        //return videojuegoRepository.findAll();
        //Vamos a usar mejor uno nuestro...
        return videojuegoRepository.buscarTodos();        
    }
    
    public List<Videojuego> buscarPorDistribuidor(Integer distribuidorId){
        
        return videojuegoRepository.buscarPorDistribuidor(distribuidorId);
    }
    
    public Videojuego buscarPorId(Integer id){
        return videojuegoRepository.getOne(id); //Ya viene en Spring JPA.
        
    }
    
    public List<Videojuego> buscar(String consulta){
        
        return videojuegoRepository.findByNombreContaining(consulta);
    }
    
    public Videojuego guardar(Videojuego videojuego){
     
        //Spring Data JPA ya nos provee el metodo "save" para guardar o actualizar un objeto anotado con @Entity
        //Si el objeto tiene su campo @Id en null, entonces se realizará un INSERT a la base de datos
        //En caso contrario, se realizará un UPDATE a la base de datos
        return videojuegoRepository.save(videojuego); //Devolvemos el mismo Videojuego pero con el "Id" ya seteado.
    }
    
}
