package com.denuncie_chamas.demo.controllers;

import com.denuncie_chamas.demo.model.Usuario;
import com.denuncie_chamas.demo.services.RelatorioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


    @RestController
    @RequestMapping("/relatorio")

    public class RelatorioController {

        @Autowired
        private RelatorioServices relatorioService;

        @GetMapping("/denuncias")
        public ResponseEntity<byte[]> gerarRelatorio(
                Authentication authentication
        ) throws Exception {

            // Usuário logado
            Usuario usuario =
                    (Usuario) authentication.getPrincipal();

            // Gera PDF
            byte[] pdf =
                    relatorioService.gerarRelatorio(usuario.getId());

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=relatorio.pdf"
                    )
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdf);
        }
    }
