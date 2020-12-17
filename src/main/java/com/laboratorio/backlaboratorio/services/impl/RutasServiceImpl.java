/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio.services.impl;

import com.laboratorio.backlaboratorio.dto.RutasDTO;
import com.laboratorio.backlaboratorio.dto.UsuariosDTO;
import com.laboratorio.backlaboratorio.repository.RutasRepository;
import com.laboratorio.backlaboratorio.services.RutasService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ricardo.bet
 */
@Service
public class RutasServiceImpl implements RutasService {

    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private RutasRepository rutasRepository;

    @Override
    public List<RutasDTO> obtenerRutasPorEmpleado(UsuariosDTO usuariosDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
