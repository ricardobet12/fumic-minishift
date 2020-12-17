/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio.services;

import com.laboratorio.backlaboratorio.dto.EmpresaDTO;
import java.util.List;
import org.springframework.mail.MailException;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author utp
 */
public interface EmpresaService {

    EmpresaDTO registrarEmpresa(EmpresaDTO e);

    List<EmpresaDTO> listarEmpresas();

    List<EmpresaDTO> empresasSinAsignacion();

    Boolean eliminarEmpresa(Integer idEmpresa);

    EmpresaDTO consultarPorId(Integer idEmpresa);

    EmpresaDTO subirArchivo(MultipartFile archivo, Integer id);

    List<EmpresaDTO> listarPorUsuarioEncargado(Integer id);
    
    List<EmpresaDTO> listarEmpresasPorEstadoActivo();
    
     void sendNotificacion(String nombre, String correo) throws MailException;

}
