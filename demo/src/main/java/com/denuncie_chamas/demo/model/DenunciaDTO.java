package com.denuncie_chamas.demo.model;

import java.math.BigDecimal;

public record DenunciaDTO(String tipoIncendio,
                          BigDecimal latitude,
                          BigDecimal longitude,
                          String imagem,
                          String descricao,
                          Boolean gravidade) {
}
