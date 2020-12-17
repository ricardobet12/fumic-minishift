/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio.services;

import com.laboratorio.backlaboratorio.dto.RutasDTO;
import com.laboratorio.backlaboratorio.dto.UsuariosDTO;
import com.laboratorio.backlaboratorio.dto.VisitasDTO;
import java.util.List;

/**
 *
 * @author ricardo.bet
 */
public interface VisitasService {

    List<VisitasDTO> listarVisitas();

    VisitasDTO agendarVisita(VisitasDTO v);

    List<RutasDTO> consultarVisitas(Integer idUsuario);
}
