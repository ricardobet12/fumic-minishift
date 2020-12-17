/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio.services;

import com.laboratorio.backlaboratorio.dto.UsuariosDTO;
import java.util.List;
import org.springframework.mail.MailException;

/**
 *
 * @author utp
 */
public interface UsuariosService {

    UsuariosDTO registrarUsuario(UsuariosDTO u);

    List<UsuariosDTO> listarUsuarios();

    boolean eliminarUsuario(Integer id);

    UsuariosDTO login(String usuario, String clave);

    List<UsuariosDTO> listarPorRol(String rol);
    
    UsuariosDTO obtenerUsuarioPorid(Integer id);
    
    void sendNotificacion(UsuariosDTO u) throws MailException;
    
    void sendNotificacionAdjunto() throws MailException;
    

}
