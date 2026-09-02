package com.isced.expedientes.controller;

import com.isced.expedientes.model.Expediente;
import com.isced.expedientes.model.Papel;
import com.isced.expedientes.model.Utilizador;
import com.isced.expedientes.repository.ExpedienteRepository;
import com.isced.expedientes.repository.PapelRepository;
import com.isced.expedientes.repository.UtilizadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ExpedienteController {

    @Autowired
    private ExpedienteRepository expedienteRepository;

    @Autowired
    private UtilizadorRepository utilizadorRepository;

    @Autowired
    private PapelRepository papelRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody Map<String, String> credenciais) {
        String email = credenciais.get("email");
        String senha = credenciais.get("senha");
        Optional<Utilizador> user = utilizadorRepository.findByEmail(email);
        if (user.isPresent() && user.get().getSenha().equals(senha)) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.status(401).body(Map.of("error", "E-mail ou senha incorretos"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Map<String, String> dados) {
        String nome = dados.get("nome");
        String email = dados.get("email");
        String senha = dados.get("senha");

        if (utilizadorRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "E-mail já registado"));
        }

        Utilizador novoUser = new Utilizador();
        novoUser.setNome(nome);
        novoUser.setEmail(email);
        novoUser.setSenha(senha);

        // Atribui o papel de "Funcionario" automaticamente
        Optional<Papel> papelFunc = papelRepository.findByNome("Funcionario");
        papelFunc.ifPresent(novoUser::setPapel);

        utilizadorRepository.save(novoUser);
        return ResponseEntity.ok(Map.of("message", "Conta criada com sucesso!"));
    }

    @GetMapping("/expedientes")
    public List<Expediente> listar() {
        return expedienteRepository.findAll();
    }

    @PostMapping("/expedientes")
    public Expediente salvar(@Valid @RequestBody Expediente expediente) {
        expediente.setEstado("REGISTADO");
        return expedienteRepository.save(expediente);
    }
}
