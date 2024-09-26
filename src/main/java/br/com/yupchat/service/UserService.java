package br.com.yupchat.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.yupchat.dto.AuthRequestDto;
import br.com.yupchat.dto.UserBasicDto;
import br.com.yupchat.dto.UserDto;
import br.com.yupchat.exception.CustomNotFoundException;
import br.com.yupchat.exception.CustomNotPermitedException;
import br.com.yupchat.model.User;
import br.com.yupchat.repository.UserRepository;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final String SECRET_KEY = "nbjRcuarYuDnZ4Q8FtTy9nUnpKBRPfRG";
    private final long EXPIRATION_TIME = 300000;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserBasicDto register(UserDto userDto) {
        User user = new User();
        user.setNome(userDto.getNome());
        user.setSobrenome(userDto.getSobrenome());
        user.setEmail(userDto.getEmail());
        user.setSenha(passwordEncoder.encode(userDto.getSenha()));

        User savedUser = userRepository.save(user);
        return mapToDto(savedUser);
    }

    public String login(AuthRequestDto authRequest) {
        Optional<User> userOptional = userRepository.findByEmail(authRequest.getEmail());
        if (userOptional.isEmpty() || !passwordEncoder.matches(authRequest.getSenha(), userOptional.get().getSenha())) {
            throw new CustomNotPermitedException(CustomNotPermitedException.INVALID_CREDENTIALS);
        }

        User user = userOptional.get();
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder().setSubject(user.getEmail()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    public UserBasicDto getUserDetails(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new CustomNotFoundException(CustomNotFoundException.USER_NOT_FOUND);
        }

        User user = userOptional.get();

        return mapToDto(user);
    }

    private UserBasicDto mapToDto(User user) {
        UserBasicDto userDto = new UserBasicDto();
        userDto.setNome(user.getNome());
        userDto.setSobrenome(user.getSobrenome());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

}
