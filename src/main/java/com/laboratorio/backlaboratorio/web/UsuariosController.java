/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio.web;

import com.laboratorio.backlaboratorio.dto.UsuariosDTO;
import com.laboratorio.backlaboratorio.services.UsuariosService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author utp
 */
@RestController
@RequestMapping("${api.base.url}/usuarios")
public class UsuariosController {

    @Autowired
    public UsuariosService service;

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UsuariosDTO user) {
        UsuariosDTO usuario = service.registrarUsuario(user);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> getUsuarios() {
        List<UsuariosDTO> usuario = service.listarUsuarios();
        if (!usuario.isEmpty()) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/login/{usuario}/{clave}")
    public ResponseEntity<?> login(@PathVariable("usuario") String user, @PathVariable("clave") String clave) {
        UsuariosDTO usuario = service.login(user, clave);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/consultar/{rol}")
    public ResponseEntity<?> getUsuariosPorRol(@PathVariable("rol") String rol) {
        List<UsuariosDTO> usuario = service.listarPorRol(rol);
        if (!usuario.isEmpty()) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/consultarPorId/{id}")
    public ResponseEntity<?> getUsuariosPorId(@PathVariable("rol") Integer id) {
        UsuariosDTO usuario = service.obtenerUsuarioPorid(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        boolean usuario = service.eliminarUsuario(id);
        if (usuario == true) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable("id") int id, @RequestBody UsuariosDTO u) {
        UsuariosDTO p = service.obtenerUsuarioPorid(id);

        if (p == null) {
            return ResponseEntity.noContent().build();
        }
        p = service.registrarUsuario(u);

        if (p == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(p);
    }

    @PostMapping("/enviarCorreo")
    public ResponseEntity<?> enviarCorreo(@RequestBody UsuariosDTO u) {
        service.sendNotificacion(u);
        System.out.println("entro");
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/enviarCorreoAdjunto")
    public ResponseEntity<?> enviarCorreoAdjunto() {
        service.sendNotificacionAdjunto();
        return ResponseEntity.ok("ok");
    }

}
