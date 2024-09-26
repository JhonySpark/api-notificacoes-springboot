package br.com.yupchat.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yupchat.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}