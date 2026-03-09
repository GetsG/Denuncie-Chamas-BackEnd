package com.denuncie_chamas.demo.controllers;

import com.denuncie_chamas.demo.infra.security.TokenService;
import com.denuncie_chamas.demo.model.LoginDTO;
import com.denuncie_chamas.demo.model.LoginResponseDTO;
import com.denuncie_chamas.demo.model.RegisterDTO;
import com.denuncie_chamas.demo.model.Usuario;
import com.denuncie_chamas.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    LocalDate dataAtual = LocalDate.now();

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Validated LoginDTO data){
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((Usuario) auth.getPrincipal());

            return ResponseEntity.ok(new LoginResponseDTO(token));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "E-mail ou senha incorretos"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity registrar(@RequestBody @Validated RegisterDTO data){
        if(this.repository.findByEmail(data.login()) != null)
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Email já cadastrado"));

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Usuario newUser = new Usuario(data.nome(), data.telefone(), data.login(), encryptedPassword, dataAtual);

        this.repository.save(newUser);
        return ResponseEntity.ok().build();
    }

}
