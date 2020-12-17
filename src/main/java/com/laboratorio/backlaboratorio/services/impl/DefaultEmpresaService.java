/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio.services.impl;

import com.laboratorio.backlaboratorio.dto.EmpresaDTO;
import com.laboratorio.backlaboratorio.entity.Empresa;
import com.laboratorio.backlaboratorio.entity.Usuarios;
import com.laboratorio.backlaboratorio.repository.EmpresaRepository;
import com.laboratorio.backlaboratorio.repository.UsuariosRepository;
import com.laboratorio.backlaboratorio.services.EmpresaService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author utp
 */
@Service
public class DefaultEmpresaService implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public EmpresaDTO registrarEmpresa(EmpresaDTO e) {
        Empresa empresa = new Empresa();
        empresa.setDireccion(e.getDireccion());
        empresa.setNit(e.getNit());
        empresa.setNombre(e.getNombre());
        empresa.setTelefono(e.getTelefono());
        empresa.setLat(e.getLat());
        empresa.setLongi(e.getLongi());
        empresa.setEstado(e.isEstado());
        if (e.getId() != null) {
            empresa.setId(e.getId());
            
        }
         if (e.getIdUsuario() != null) {
            Usuarios u = new Usuarios();
            u.setIdUsuario(e.getIdUsuario().getIdUsuario());
            empresa.setIdUsuario(u);
        }
        empresa = empresaRepository.save(empresa);
       e.setId(empresa.getId());

        return e;
    }

    @Override
    public List<EmpresaDTO> listarEmpresas() {
        List<Empresa> empresa = empresaRepository.findAll();
        List<EmpresaDTO> resultado = new ArrayList<>();
        if (!empresa.isEmpty() || empresa != null) {
            for (Empresa e : empresa) {
                resultado.add(mapper.map(e, EmpresaDTO.class));
            }
            return resultado;
        }
        return null;
    }

    @Override
    public List<EmpresaDTO> listarEmpresasPorEstadoActivo() {
        List<Empresa> empresa = empresaRepository.findByEstado(true);
        List<EmpresaDTO> resultado = new ArrayList<>();
        if (!empresa.isEmpty() || empresa != null) {
            for (Empresa e : empresa) {
                resultado.add(mapper.map(e, EmpresaDTO.class));
            }
            return resultado;
        }
        return null;
    }

    @Override
    public List<EmpresaDTO> empresasSinAsignacion() {
        List<Empresa> empresa = empresaRepository.findByIdUsuarioIsNull();
        List<EmpresaDTO> resultado = new ArrayList<>();
        if (!empresa.isEmpty() || empresa != null) {
            for (Empresa e : empresa) {
                resultado.add(mapper.map(e, EmpresaDTO.class));
            }
            return resultado;
        }
        return null;
    }

    @Override
    public Boolean eliminarEmpresa(Integer idEmpresa) {
        if (idEmpresa != null) {
            Optional<Empresa> empresa = empresaRepository.findById(idEmpresa);
            if (empresa.isPresent()) {
                empresaRepository.deleteById(idEmpresa);
                return true;
            }
            return false;
        }
        return null;
    }

    @Override
    public EmpresaDTO consultarPorId(Integer idEmpresa) {
        Optional<Empresa> e = empresaRepository.findById(idEmpresa);
        if (e.isPresent()) {
            return mapper.map(e.get(), EmpresaDTO.class);
        }
        return null;
    }

    @Override
    public EmpresaDTO subirArchivo(MultipartFile archivo, Integer id) {
        if (id != null) {
            Optional<Empresa> emp = this.empresaRepository.findById(id);
            EmpresaDTO result = new EmpresaDTO();
            if (emp.isPresent()) {
                if (!archivo.isEmpty()) {
                    String nombreArchivo = id + archivo.getOriginalFilename();
                    System.out.println(nombreArchivo);
                    Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
                    emp.get().setRuta(nombreArchivo);
                    result.setRuta(nombreArchivo);
                    result.setId(id);
                    this.empresaRepository.save(emp.get());
                    try {
                        Files.copy(archivo.getInputStream(), rutaArchivo);
                        return result;
                    } catch (IOException ex) {
                        return null;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<EmpresaDTO> listarPorUsuarioEncargado(Integer id) {
        List<EmpresaDTO> result = new ArrayList<>();
        if (id != null) {
            Optional<Usuarios> uOptional = this.usuariosRepository.findById(id);
            if (uOptional.isPresent()) {
                List<Empresa> list = this.empresaRepository.findByIdUsuario(uOptional.get());
                if (list != null && !list.isEmpty()) {
                    for (Empresa e : list) {
                        result.add(mapper.map(e, EmpresaDTO.class));
                    }
                    return result;
                }
            }
        }
        return null;
    }
    
    @Override
    public void sendNotificacion(String nombre, String correo) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
          mail.setTo(correo);
            mail.setFrom("ricardo.bet1209@gmail.com");
            mail.setSubject("Empresa FUMIC Gracias por confiar en nosotros");
            mail.setText("Gracias "+nombre+" por darnos la confianza de ser la empresa que va a realizar el servicio de fumigación.");

            javaMailSender.send(mail);
        
    }
    
      private void sendNotificacion2(String nombre, String correo) throws MailException {
        SimpleMailMessage mail = new SimpleMailMessage();
          mail.setTo(correo);
            mail.setFrom("ricardo.bet1209@gmail.com");
            mail.setSubject("Empresa FUMIC Gracias por confiar en nosotros");
            mail.setText("Gracias "+nombre+" por darnos la confianza de ser la empresa que va a realizar el servicio de fumigación.");

            javaMailSender.send(mail);
        
    }


    
}
