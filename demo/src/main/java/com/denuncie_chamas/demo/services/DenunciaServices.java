package com.denuncie_chamas.demo.services;

import com.denuncie_chamas.demo.model.Denuncia;
import com.denuncie_chamas.demo.model.DenunciaDTO;
import com.denuncie_chamas.demo.model.DenunciaResponseDTO;
import com.denuncie_chamas.demo.model.Enums.GravidadeEnum;
import com.denuncie_chamas.demo.model.Enums.StatusEnum;
import com.denuncie_chamas.demo.model.Enums.TipoIncendioEnum;
import com.denuncie_chamas.demo.model.Usuario;
import com.denuncie_chamas.demo.repositories.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DenunciaServices {

    @Autowired
    DenunciaRepository repository;

    public Denuncia create(DenunciaDTO denunciaDTO){

        Denuncia denuncia = new Denuncia();

        if(denunciaDTO.tipoIncendio().equals("Residencial")){
            denuncia.setTipoIncendio(TipoIncendioEnum.RESIDENCIAL);
        }
        else if(denunciaDTO.tipoIncendio().equals("Comercial")){
            denuncia.setTipoIncendio(TipoIncendioEnum.COMERCIAL);
        }
        else if(denunciaDTO.tipoIncendio().equals("Florestal")){
            denuncia.setTipoIncendio(TipoIncendioEnum.FLORESTAL);
        }
        else if(denunciaDTO.tipoIncendio().equals("Urbano")){
            denuncia.setTipoIncendio(TipoIncendioEnum.URBANO);
        }
        else if(denunciaDTO.tipoIncendio().equals("Rural")){
            denuncia.setTipoIncendio(TipoIncendioEnum.RURAL);
        }

        denuncia.setLatitude(denunciaDTO.latitude());
        denuncia.setLongitude(denunciaDTO.longitude());
        denuncia.setImagem(denunciaDTO.imagem());
        denuncia.setDescricao(denunciaDTO.descricao());

        if(denunciaDTO.gravidade() == true){
            denuncia.setGravidade(GravidadeEnum.ALTA);
        }
        else if(denunciaDTO.gravidade() == false){
            denuncia.setGravidade(GravidadeEnum.MEDIA);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) authentication.getPrincipal();

        denuncia.setUsuario(usuario);
        denuncia.setStatus(StatusEnum.PENDENTE);

        LocalDateTime dataHora = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dataFormatada = dataHora.format(formatter);

        denuncia.setDataRegistro(dataFormatada);

        return repository.save(denuncia);
    }

    public List<DenunciaResponseDTO> findMyDenuncias(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) authentication.getPrincipal();

        List<Denuncia> denuncias = repository.findByUsuarioId(usuario.getId());

        return denuncias.stream()
                .map(denuncia -> new DenunciaResponseDTO(
                        denuncia.getTipoIncendio(),
                        denuncia.getGravidade(),
                        denuncia.getLatitude(),
                        denuncia.getLongitude(),
                        denuncia.getDescricao(),
                        denuncia.getImagem(),
                        denuncia.getDataRegistro(),
                        denuncia.getStatus()
                ))
                .toList();
    }
}
