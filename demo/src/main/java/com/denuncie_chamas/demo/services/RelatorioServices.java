package com.denuncie_chamas.demo.services;

import com.denuncie_chamas.demo.model.Denuncia;
import com.denuncie_chamas.demo.model.DenunciaRelatorioDTO;
import com.denuncie_chamas.demo.repositories.DenunciaRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RelatorioServices {

    @Autowired
    private DenunciaRepository denunciaRepository;

    public byte[] gerarRelatorio(Long usuarioId) {

        try {

            List<Denuncia> denuncias =
                    denunciaRepository.findByUsuarioId(usuarioId);

            System.out.println("Denúncias encontradas: " + denuncias.size());

            List<DenunciaRelatorioDTO> dtos = denuncias.stream()
                    .map(DenunciaRelatorioDTO::new)
                    .collect(Collectors.toList());

            InputStream arquivo =
                    getClass().getResourceAsStream(
                            "/relatorios/denuncias.jrxml"
                    );

            System.out.println("ARQUIVO: " + arquivo);

            JasperReport jasperReport =
                    JasperCompileManager.compileReport(arquivo);

            JRBeanCollectionDataSource dataSource =
                    new JRBeanCollectionDataSource(dtos);

            Map<String, Object> parametros = new HashMap<>();

            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                            jasperReport,
                            parametros,
                            dataSource
                    );

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {

            e.printStackTrace();

            throw new RuntimeException(e);
        }
    }
}