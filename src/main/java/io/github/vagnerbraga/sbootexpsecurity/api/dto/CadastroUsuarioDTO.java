package io.github.vagnerbraga.sbootexpsecurity.api.dto;

import io.github.vagnerbraga.sbootexpsecurity.domain.entity.Usuario;
import lombok.Data;

import java.util.List;

@Data
public class CadastroUsuarioDTO {
    private Usuario usuario;
    private List<String> permissoes;
}
