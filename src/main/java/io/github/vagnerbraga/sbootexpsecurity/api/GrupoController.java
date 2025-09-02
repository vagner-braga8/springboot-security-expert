package io.github.vagnerbraga.sbootexpsecurity.api;

import io.github.vagnerbraga.sbootexpsecurity.domain.entity.Grupo;
import io.github.vagnerbraga.sbootexpsecurity.domain.repository.GrupoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos")
@RequiredArgsConstructor
public class GrupoController {

    private final GrupoRepository grupoRepository;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Grupo> salvar(@RequestBody final Grupo grupo) {
        return ResponseEntity.ok(grupoRepository.save(grupo));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Grupo>> listar() {
        return ResponseEntity.ok(grupoRepository.findAll());
    }
}
