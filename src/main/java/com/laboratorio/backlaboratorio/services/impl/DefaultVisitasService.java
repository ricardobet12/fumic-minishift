/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio.services.impl;

import com.laboratorio.backlaboratorio.dto.EmpresaDTO;
import com.laboratorio.backlaboratorio.dto.RutasDTO;
import com.laboratorio.backlaboratorio.dto.UsuariosDTO;
import com.laboratorio.backlaboratorio.dto.VisitasDTO;
import com.laboratorio.backlaboratorio.entity.Empresa;
import com.laboratorio.backlaboratorio.entity.Usuarios;
import com.laboratorio.backlaboratorio.entity.Visitas;
import com.laboratorio.backlaboratorio.repository.EmpresaRepository;
import com.laboratorio.backlaboratorio.repository.UsuariosRepository;
import com.laboratorio.backlaboratorio.repository.VisitasRepository;
import com.laboratorio.backlaboratorio.services.VisitasService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ricardo.bet
 */
@Service
public class DefaultVisitasService implements VisitasService {

    @Autowired
    private VisitasRepository visitasRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<VisitasDTO> listarVisitas() {
        List<Visitas> visitas = visitasRepository.findAllByOrderByFechaAsc();
        List<VisitasDTO> result = new ArrayList<>();
        if (visitas != null || !visitas.isEmpty()) {
            for (Visitas u : visitas) {
                result.add(mapper.map(u, VisitasDTO.class));
            }
            return result;
        }
        return null;
    }

    @Override
    public VisitasDTO agendarVisita(VisitasDTO v) {
        Visitas vi = new Visitas();
        Empresa e = new Empresa();
        e.setId(v.getIdEmpresa().getId());
        e.setDireccion(v.getIdEmpresa().getDireccion());
        e.setTelefono(v.getIdEmpresa().getTelefono());
        e.setNombre(v.getIdEmpresa().getNombre());
        vi.setIdEmpresa(e);
        vi.setFecha(v.getFecha());
        Visitas vis = this.visitasRepository.save(vi);
        return mapper.map(vis, VisitasDTO.class);
    }

       @Override
    public List<RutasDTO> consultarVisitas(Integer idUsuario) {
        List<RutasDTO> result = new ArrayList<>();
        RutasDTO ruta = new RutasDTO();
        if (idUsuario != null) {
            Optional<Usuarios> uOptional = this.usuariosRepository.findById(idUsuario);
            if (uOptional.isPresent()) {
                List<Empresa> list = this.empresaRepository.findByIdUsuario(mapper.map(uOptional.get(), Usuarios.class));
                if (list != null && !list.isEmpty()) {
                    for (Empresa e : list) {
                        ruta = new RutasDTO();
                        Visitas ultimaVisitaProgramada = new Visitas();
                        ruta.setIdEmpresa(mapper.map(e, EmpresaDTO.class));
                        ruta.setIdUsuario(mapper.map(uOptional.get(), UsuariosDTO.class));
                         ultimaVisitaProgramada = this.visitasRepository.findTop1ByIdEmpresaOrderByFechaAsc(e);
                        if (ultimaVisitaProgramada != null) {
                            ruta.setIdRuta(ultimaVisitaProgramada.getIdVisita());
                            ruta.setFecha(ultimaVisitaProgramada.getFecha());
                            if (ultimaVisitaProgramada.getIdFormulario() != null) {
                                ruta.setEstado("description");
                            } else {
                                ruta.setEstado("access_time");
                            }
                            result.add(ruta);
                        }
                    }
                }
            }
            return result;
        }
        return null;
    }

}
