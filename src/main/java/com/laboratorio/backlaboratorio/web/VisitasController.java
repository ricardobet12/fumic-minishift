/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laboratorio.backlaboratorio.web;

import com.laboratorio.backlaboratorio.dto.RutasDTO;
import com.laboratorio.backlaboratorio.dto.VisitasDTO;
import com.laboratorio.backlaboratorio.services.VisitasService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ricardo.bet
 */
@RestController
@RequestMapping("${api.base.url}/visitas")
public class VisitasController {

    @Autowired
    private VisitasService visitasService;

    @PostMapping
    public ResponseEntity<?> saveVisita(@RequestBody VisitasDTO visita) {
        VisitasDTO v = visitasService.agendarVisita(visita);
        if (v != null) {
            return ResponseEntity.ok(v);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> getVisitas() {
        List<VisitasDTO> u = visitasService.listarVisitas();
        if (!u.isEmpty()) {
            return ResponseEntity.ok(u);
        }
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/obtenerRutas/{id}")
    public ResponseEntity<?> getRutas(@PathVariable("id") Integer id) {
        List<RutasDTO> u = visitasService.consultarVisitas(id);
        if (!u.isEmpty()) {
            return ResponseEntity.ok(u);
        }
        return ResponseEntity.noContent().build();
    }
}
