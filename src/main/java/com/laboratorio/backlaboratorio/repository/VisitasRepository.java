/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio.repository;

import com.laboratorio.backlaboratorio.entity.Empresa;
import com.laboratorio.backlaboratorio.entity.Visitas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ricardo.bet
 */

@Repository
public interface VisitasRepository extends JpaRepository<Visitas, Integer>{
    
    List<Visitas> findAllByOrderByFechaAsc();
    
    Visitas findTop1ByIdEmpresaOrderByFechaAsc(Empresa empresa);
    
}
