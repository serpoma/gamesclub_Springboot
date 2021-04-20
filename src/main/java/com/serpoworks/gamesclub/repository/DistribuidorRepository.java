package com.serpoworks.gamesclub.repository;

import com.serpoworks.gamesclub.domain.Distribuidor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface DistribuidorRepository  extends JpaRepository<Distribuidor,Integer> {
    
    //Podiamos haberlo obviado, quitarlo y usar en el Service el findAll() que viene predeterminado con el Repository!
    @Query("from Distribuidor d order by d.nombre")
    List<Distribuidor> listarTodos();
    
}
