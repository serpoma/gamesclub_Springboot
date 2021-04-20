package com.serpoworks.gamesclub.controller;

import com.serpoworks.gamesclub.domain.Videojuego;
import com.serpoworks.gamesclub.service.VideojuegosService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
En Spring, las clases anotadas con @Controller atienden peticiones web.
Para que se considere Controlador tenemos que poner explicitamente en una clase Java normal la etiqueta @Controller al inicio
y por cada HTML que suministremos poner la etiqueta @RequestMapping en cada metodo que queramos (los parentesis seran la ubicacion de la URL)
*/

@Controller
public class ListadoController {
    
    //El Controlador necesita al Service para poder obtener los Videojuegos    
    private final VideojuegosService videojuegoService; //final: no puede modificar su valor, hay que establecer ese valor al declararlos o en el constructor.

    //Se inyecta en el Constructor el Service: Inyección de Dependencias.
    public ListadoController(VideojuegosService videojuegoService) {
        this.videojuegoService = videojuegoService;
    }
    
    @RequestMapping("/") //Se atiende la raiz del sitio Web.
    public String listarVideojuegos(Model model){ //El Modelo es el objeto que tratará la Vista para poder visualizar los Videojuegos.
        
        //Búsqueda de los videojuegos...
        List<Videojuego> buscarDestacados = videojuegoService.buscarDestacados();
        
        //Se le asigna al Model los Videojuegos a una variable "videojuegos" que leera la vista con Thymeleaf
        //Al Model se le puede asignar mas objetos que no sean Videojuegos...
        model.addAttribute("videojuegos", buscarDestacados);
        
        //Se llama a listado.html
        return "listado";
        
    }
    
    //Busqueda de Videojuegos por Distribuidor: Se hace notar que se devuelve la misma pagina "listado.html"
    //Para usar un parametro opcional pondriamos: @RequestParam(required = false) int distribuidorId...
    @RequestMapping("/videojuegosPorDistribuidor")
    public String listarVideojuegosPorDistribuidor(int distribuidorId, Model model){
        
        List<Videojuego> juegos = videojuegoService.buscarPorDistribuidor(distribuidorId);
        model.addAttribute("videojuegos", juegos);
        
        //Queremos mostrar el Nombre de Distribuidor en grande.
        model.addAttribute("nombreDistribuidor",juegos.get(0).getDistribuidor().getNombre());
        
        return "listado";
        
    }
    
    //Busqueda de Videojuegos por Nombre: se podrá usar /buscar?consulta=border --> Saca el juego Borderlands 3
    //@RequestParam nos permite configurar información sobre un parametro especifico.
    //Aqui designaremos la nomenclatura que deberá tener la URL para construirla con el parametro: en este caso sera:
    // /buscar?q=border
    @RequestMapping("/buscar")
    public String buscar(@RequestParam("q") String consulta, Model model){
        
        List<Videojuego> juegos = videojuegoService.buscar(consulta);
        model.addAttribute("videojuegos",juegos);
        
        return "listado";
        
    }   
}
