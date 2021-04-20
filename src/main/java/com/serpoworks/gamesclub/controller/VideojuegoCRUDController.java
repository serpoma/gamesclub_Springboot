package com.serpoworks.gamesclub.controller;

import com.serpoworks.gamesclub.domain.Distribuidor;
import com.serpoworks.gamesclub.domain.Videojuego;
import com.serpoworks.gamesclub.service.DistribuidorService;
import com.serpoworks.gamesclub.service.VideojuegosService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class VideojuegoCRUDController {

    private final DistribuidorService distribuidorService;
    private final VideojuegosService videojuegoService;

    public VideojuegoCRUDController(DistribuidorService distribuidorService, VideojuegosService videojuegoService) {
        this.distribuidorService = distribuidorService;
        this.videojuegoService = videojuegoService;
    }

    @RequestMapping("/videojuegos/crear")
    public String mostrarFormAlta(Model model){

       List<Distribuidor> listaDistribuidores = this.distribuidorService.listarTodos();
       model.addAttribute("distribuidores",listaDistribuidores);
       
       //Añadimos al Model un objeto Videojuego vacio para luego asociarlo al formulario y así guardar de forma mas facil.
       //Se asocia dentro del form con th:object (verlo)
       //Y después cada atributo del objeto con th:field="*{atributo}"
       //Se pueden asociar atributos complejos como "Distribuidor" y Thymeleaf se encarga de asociarlo completo! QUE PASADA!
       model.addAttribute("videojuego", new Videojuego());
        
       return "formVideojuego";
    }
    
    //RequestMapping por defecto es un GET y PostMapping es un POST sirve para mandar información dentro del body del form.
    @PostMapping("/videojuegos/guardar") 
    public String guardar(Videojuego videojuego){
        
        videojuego = this.videojuegoService.guardar(videojuego);
        System.out.println(videojuego);
        //Una vez que guardamos redireccionamos a la raiz de la web.
        return "redirect:/";
    }
    
    @RequestMapping("/videojuegos/modificar")
    public String modificar(int id, Model model){
        
       List<Distribuidor> listaDistribuidores = this.distribuidorService.listarTodos();
       model.addAttribute("distribuidores",listaDistribuidores);
        
       Videojuego videojuego =  this.videojuegoService.buscarPorId(id);
       System.out.println(videojuego);

       model.addAttribute("videojuego",videojuego);
       
       return "formVideojuego";
        
    }
    
}
