package com.denuncie_chamas.demo.repositories;

import com.denuncie_chamas.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByEmail(String login);
}
