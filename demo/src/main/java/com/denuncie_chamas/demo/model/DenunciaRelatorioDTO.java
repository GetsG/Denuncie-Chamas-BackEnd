package com.denuncie_chamas.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DenunciaRelatorioDTO {

    private Long id_denuncia;
    private String tipoIncendio;
    private String gravidade;
    private String status;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String descricao;
    private String dataRegistro;

    public DenunciaRelatorioDTO(Denuncia d) {
        this.id_denuncia = d.getId_denuncia();
        this.tipoIncendio = d.getTipoIncendio() != null ? d.getTipoIncendio().name() : "";
        this.gravidade    = d.getGravidade()    != null ? d.getGravidade().name()    : "";
        this.status       = d.getStatus()       != null ? d.getStatus().name()       : "";
        this.latitude     = d.getLatitude();
        this.longitude    = d.getLongitude();
        this.descricao    = d.getDescricao();
        this.dataRegistro = d.getDataRegistro();
    }
}
