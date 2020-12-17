/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio.repository;

import com.laboratorio.backlaboratorio.dto.RutasDTO;
import com.laboratorio.backlaboratorio.dto.UsuariosDTO;
import com.laboratorio.backlaboratorio.entity.Rutas;
import com.laboratorio.backlaboratorio.entity.Usuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ricardo.bet
 */
@Repository
public interface RutasRepository extends JpaRepository<Rutas, Integer> {

    List<Rutas> findByIdUsuario(Usuarios usuario);
    
   

}
