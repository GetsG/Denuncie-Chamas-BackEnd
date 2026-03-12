package com.denuncie_chamas.demo.controllers;

import com.denuncie_chamas.demo.model.Denuncia;
import com.denuncie_chamas.demo.model.DenunciaDTO;
import com.denuncie_chamas.demo.model.DenunciaResponseDTO;
import com.denuncie_chamas.demo.services.DenunciaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class DenunciaController {

    @Autowired
    private DenunciaServices services;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody DenunciaDTO denuncia){
        services.create(denuncia);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<DenunciaResponseDTO>> getMyReports(){
        return ResponseEntity.ok(services.findMyDenuncias());
    }
}
