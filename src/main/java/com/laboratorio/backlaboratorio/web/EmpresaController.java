/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio.web;

import com.laboratorio.backlaboratorio.dto.EmpresaDTO;
import com.laboratorio.backlaboratorio.services.EmpresaService;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author utp
 */
@RestController
@RequestMapping("${api.base.url}/empresa")
public class EmpresaController {

    @Autowired
    public EmpresaService service;

    @PostMapping
    public ResponseEntity<?> saveEmpresa(@RequestBody EmpresaDTO empresa) {
        EmpresaDTO e = service.registrarEmpresa(empresa);
        if (e != null) {
            return ResponseEntity.ok(empresa);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> getEmpresas() {
        List<EmpresaDTO> empresa = service.listarEmpresas();
        if (!empresa.isEmpty()) {
            return ResponseEntity.ok(empresa);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/empresasActivas")
    public ResponseEntity<?> getEmpresasActivas() {
        List<EmpresaDTO> empresa = service.listarEmpresasPorEstadoActivo();
        if (!empresa.isEmpty()) {
            return ResponseEntity.ok(empresa);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/enviarCorreo/{nombre}/{correo}")
    public ResponseEntity<?> enviarCorreo(@PathVariable("nombre") String nombre, @PathVariable("correo") String correo) {
        service.sendNotificacion(nombre, correo);
        System.out.println("entro");
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/empresasSinAsignacion")
    public ResponseEntity<?> getEmpresaSinAsignacion() {
        List<EmpresaDTO> empresa = service.empresasSinAsignacion();
        if (!empresa.isEmpty()) {
            return ResponseEntity.ok(empresa);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/eliminar/{idEmpresa}")
    public ResponseEntity<?> deleteEmpresa(@PathVariable("idEmpresa") Integer idEmpresa) {
        boolean empresa = service.eliminarEmpresa(idEmpresa);
        if (empresa == true) {
            return ResponseEntity.ok(empresa);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<?> UpdateEmpresa(@RequestBody EmpresaDTO empresa) {
        EmpresaDTO emp = this.service.consultarPorId(empresa.getId());
        if (emp == null) {
            return ResponseEntity.notFound().build();
        }
        emp = service.registrarEmpresa(empresa);
        if (emp != null) {
            return ResponseEntity.ok(empresa);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Integer id) {
        EmpresaDTO result = this.service.subirArchivo(archivo, id);
        System.out.println(result);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/listarPorUsuario/{idUsuario}")
    public ResponseEntity<?> getEmpresasAsignadaAUsuario(@PathVariable("idUsuario") Integer idUsuario) {
        List<EmpresaDTO> empresa = service.listarPorUsuarioEncargado(idUsuario);
        if (!empresa.isEmpty()) {
            return ResponseEntity.ok(empresa);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/uploads/{nombreFoto:.+}")
    public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {

        Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
        Resource recurso = null;

        try {
            recurso = new UrlResource(rutaArchivo.toUri());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!recurso.exists() && !recurso.isReadable()) {
            throw new RuntimeException("Error no se pudo cargar la imagen: " + nombreFoto);
        }
        HttpHeaders cabecera = new HttpHeaders();
        cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

        return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
    }

}
