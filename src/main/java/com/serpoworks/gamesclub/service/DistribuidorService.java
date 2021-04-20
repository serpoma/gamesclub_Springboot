
package com.serpoworks.gamesclub.service;

import com.serpoworks.gamesclub.domain.Distribuidor;
import com.serpoworks.gamesclub.repository.DistribuidorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DistribuidorService {
    
    private final DistribuidorRepository distribuidorRepository;

    public DistribuidorService(DistribuidorRepository distribuidorRepository) {
        this.distribuidorRepository = distribuidorRepository;
    }
    
    public List<Distribuidor> listarTodos(){
        
       return this.distribuidorRepository.listarTodos();
    }
    
    
}
