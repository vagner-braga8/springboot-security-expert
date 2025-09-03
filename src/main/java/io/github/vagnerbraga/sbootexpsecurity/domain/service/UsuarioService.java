package io.github.vagnerbraga.sbootexpsecurity.domain.service;

import io.github.vagnerbraga.sbootexpsecurity.domain.entity.Grupo;
import io.github.vagnerbraga.sbootexpsecurity.domain.entity.Usuario;
import io.github.vagnerbraga.sbootexpsecurity.domain.entity.UsuarioGrupo;
import io.github.vagnerbraga.sbootexpsecurity.domain.repository.GrupoRepository;
import io.github.vagnerbraga.sbootexpsecurity.domain.repository.UsuarioGrupoRepository;
import io.github.vagnerbraga.sbootexpsecurity.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final GrupoRepository grupoRepository;
    private final UsuarioGrupoRepository usuarioGrupoRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Usuario salvar(Usuario usuario, List<String> grupos) {
        salvarUsuario(usuario);
        associarGruposAoUsuario(usuario, grupos);
        return usuario;
    }

    private void salvarUsuario(Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        usuarioRepository.save(usuario);
    }

    private void associarGruposAoUsuario(Usuario usuario, List<String> grupos) {
        List<UsuarioGrupo> listaUsuarioGrupo = grupos.stream().map(nomeGrupo -> {
                    Optional<Grupo> possivelGrupo = grupoRepository.findByNome(nomeGrupo);
                    if (possivelGrupo.isPresent()) {
                        Grupo grupo = possivelGrupo.get();
                        return new UsuarioGrupo(usuario, grupo);
                    }
                    return null;
                })
                .filter(grupo -> grupo != null)
                .collect(Collectors.toList());
        usuarioGrupoRepository.saveAll(listaUsuarioGrupo);
    }

    public Usuario obterUsuarioComPermissoes(String login) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByLogin(login);
        if (usuarioOptional.isEmpty()) {
            return null;
        }
        Usuario usuario = usuarioOptional.get();
        List<String> permissoes = usuarioGrupoRepository.findPermissoesByUsuario(usuario);
        usuario.setPermissoes(permissoes);
        return usuario;
    }
}
