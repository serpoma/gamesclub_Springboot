
package com.serpoworks.gamesclub.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

//Asociación de clases Java con la Base de Datos : Lo haremos con JPA
//@Entity indica que la clase está asociada con una tabla de la base de datos
//(se asume que el nombre de la tabla será igual al nombre de la clase)
//Se puede agregar la anotación @Table para indicar un nombre de tabla diferente

@Entity
//@Table(name="videojuego"): Opcional. Aqui no hace falta ponerlo, xq se llaman igual (Clase Java y Tabla), pero si no fuera así habria que ponerlo.
public class Videojuego {    
    
    @Id //Indicamos el campo/atributo que será ID
    //@Column(name="id"): Igual concepto que el @Table anterior, si se llaman igual no hace falta ponerlo.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Indicamos que será autogenerado y que cuando creamos un Videojuego nuevo, asigna un id sin que sea nulo.
    private Integer id;
    private String nombre;
    private String descripcion;
    private String imagen_url;
    
    //@JoinColumn(name = "distribuidor_id", nullable = false): Opcional, solo si queremos expresamente poner el nombre de la Columna
    @ManyToOne //Mapeamos con JPA la relación entre tablas: Muchos a Uno (Un Videojuego tiene un distribuidor, pero un distribuidor puede tener muchos Videojuegos)
    private Distribuidor distribuidor; //Asociación a la tabla Distribuidor: En vez de incluir el id solo, metemos el Objeto entero.

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagenURL) {
        this.imagen_url = imagenURL;
    }

    public Distribuidor getDistribuidor() {
        return distribuidor;
    }

    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
    }

    @Override
    public String toString() {
        return "Videojuego{" + "id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", imagen_url=" + imagen_url + ", distribuidor=" + distribuidor + '}';
    }
    
    
    
    
    
}
