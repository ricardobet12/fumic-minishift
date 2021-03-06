/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio.repository;

import com.laboratorio.backlaboratorio.entity.Usuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author utp
 */
@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Integer> {

    Usuarios findByUsuarioAndClave(String usuario, String clave);

    List<Usuarios> findByRol(String rol);

    Usuarios findByCedula(Integer cedula);

}
