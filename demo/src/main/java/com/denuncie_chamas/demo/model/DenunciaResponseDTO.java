package com.denuncie_chamas.demo.model;

import com.denuncie_chamas.demo.model.Enums.GravidadeEnum;
import com.denuncie_chamas.demo.model.Enums.StatusEnum;
import com.denuncie_chamas.demo.model.Enums.TipoIncendioEnum;

import java.math.BigDecimal;

public record DenunciaResponseDTO(TipoIncendioEnum tipoIncendio,
                                  GravidadeEnum gravidade,
                                  BigDecimal latitude,
                                  BigDecimal longitude,
                                  String descricao,
                                  String imagem,
                                  String dataRegistro,
                                  StatusEnum status){}
