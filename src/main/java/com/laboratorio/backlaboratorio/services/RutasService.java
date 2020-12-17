/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio.services;

import com.laboratorio.backlaboratorio.dto.RutasDTO;
import com.laboratorio.backlaboratorio.dto.UsuariosDTO;
import java.util.List;

/**
 *
 * @author ricardo.bet
 */
public interface RutasService {
    
    List<RutasDTO> obtenerRutasPorEmpleado(UsuariosDTO usuariosDTO);
    
    
    
}
