package com.denuncie_chamas.demo.services;

import com.denuncie_chamas.demo.model.Denuncia;
import com.denuncie_chamas.demo.repositories.DenunciaRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelatorioServices {

    @Autowired
    private DenunciaRepository denunciaRepository;

    public byte[] gerarRelatorio(Long usuarioId) throws Exception {

        // Busca denúncias do usuário
        List<Denuncia> denuncias =
                denunciaRepository.findByUsuarioId(usuarioId);

        // Arquivo Jasper
        InputStream arquivo =
                getClass().getResourceAsStream(
                        "/relatorios/denuncias.jrxml"
                );

        // Compila relatório
        JasperReport jasperReport =
                JasperCompileManager.compileReport(arquivo);

        // DataSource
        JRBeanCollectionDataSource dataSource =
                new JRBeanCollectionDataSource(denuncias);

        // Parâmetros opcionais
        Map<String, Object> parametros = new HashMap<>();

        parametros.put("titulo", "Relatório de Denúncias");

        // Preenche relatório
        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport,
                        parametros,
                        dataSource
                );

        // Gera PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
