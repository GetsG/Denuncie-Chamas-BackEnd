package com.denuncie_chamas.demo.model;

import com.denuncie_chamas.demo.model.Enums.GravidadeEnum;
import com.denuncie_chamas.demo.model.Enums.StatusEnum;
import com.denuncie_chamas.demo.model.Enums.TipoIncendioEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Table(name = "denuncia")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id_denuncia")
public class Denuncia implements Serializable {

    private static final long serialVersionUTD = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_denuncia;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_incendio")
    private TipoIncendioEnum tipoIncendio;

    @Enumerated(EnumType.STRING)
    @Column(name = "gravidade")
    private GravidadeEnum gravidade;

    @Column(precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(precision = 9, scale = 6)
    private BigDecimal longitude;

    private String descricao;

    @Column(columnDefinition = "TEXT")
    private String imagem;

    @Column(name = "data_hora_registro")
    private String dataRegistro;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;


}
