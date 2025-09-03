package io.github.vagnerbraga.sbootexpsecurity.domain.security;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class IdentificacaoUsuario {
    private String id;
    private String nome;
    private String login;
    private List<String> permissoes;

    public IdentificacaoUsuario(String nome, String login, List<String> permissoes, String id) {
        this.nome = nome;
        this.login = login;
        this.permissoes = permissoes;
        this.id = id;
    }

    public List<String> getPermissoes() {
        if(permissoes == null) {
            permissoes = new ArrayList<>();
        }
        return permissoes;
    }
}
