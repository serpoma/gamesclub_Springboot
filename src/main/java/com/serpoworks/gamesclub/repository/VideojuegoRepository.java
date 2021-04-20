
package com.serpoworks.gamesclub.repository;

import com.serpoworks.gamesclub.domain.Videojuego;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*Acceso a datos con Spring Data JPA
Esto es un Repository: Se encarga de la interfaz directamente con la Base de Datos.
Spring Data JPA es una extensión de Spring Framework que facilita muchísimo el acceso a las bases de datos, 
usando objetos mapeados con JPA. Nos brinda la facilidad de programar interfaces, que serán luego implementadas 
por Spring en tiempo de ejecución. De esta manera, con muy poco código, tendremos muchísima funcionalidad.

La interfaz que creemos deberá extender JpaRepository (indicando la clase y el tipo de datos del atributo que es ID). 
Al extender JpaRepository, tendremos automaticamente varios métodos útiles para buscar objetos, guardarlos, actualizarlos y borrarlos.
(findAll(),save(), deleteAll(), count()...)

El Service es el que usará el Repository.
*/

public interface VideojuegoRepository extends JpaRepository<Videojuego,Integer> {
    //No hace falta implementar nada! Ya Spring te lo hace directamente!
    //Claro si utilizamos los metodos "estandar": findAll(),save(), deleteAll(), count(), etc
    //Pero si queremos ajustar algo mas debemos declarar (esto es una interfaz) los metodos que queramos, y la busqueda que queremos hacer
    //Ejemplo queremos devolver los Videojuegos ordenados de una forma
    
    //Spring Data JPA utiliza la anotación @Query para resolver métodos de consulta personalizados
    //@Query recibe como parametro un String con el Query a ejecutar: Se puede usar tanto JPQL (Java Persistence Query Language)
    //que es el lenguaje de consultas de JPA, que en el se consulta sobre "objetos" no tablas. Tb se puede usar SQL nativo
    //@Query(value = "select * from videojuego order by nombre", nativeQuery=true) //SQL nativo
    @Query("from Videojuego v order by v.nombre") //JPQL.
    List<Videojuego> buscarTodos();
    
    //Paramétros dinámicos
    //JPQL: Aqui no hace falta hacer un JOIN (como hariamos con SQL nativo) con la otra tabla ya viene integrado! ?1 hace referencia a "distribuidorId"
    @Query("from Videojuego v where v.distribuidor.id= ?1 order by v.nombre") 
    List<Videojuego> buscarPorDistribuidor(Integer distribuidorId);

    /*
    Query Methods
        Spring Data nos provee una forma alternativa a @Query para crear métodos de consulta, y es a través de los Query Mehtods. 
        Un Query Method es la creación de una consulta a partir del nombre de un método, el cual tiene que seguir cierta sintaxis. 
        Los Query Methods son muy prácticos para crear consultas simples y rápidas, y nos evitan el uso de @Query. 
        Los query methods empiezan con la palabra findBy y siguen con las condiciones.

            findByNombre(String n)
            findByNombreAndApellido(String n, String a)
            findByApellidoOrderByNombreAsc(String a)
    */ 
    //La consulta siguiente también podriamos utilizarla así como sigue a continuación
        //@Query("from Videojuego v where v.nombre like %?1% order by v.nombre")
        ///List<Videojuego> buscar(String consulta);
    List<Videojuego> findByNombreContaining(String consulta);
        
    
}
