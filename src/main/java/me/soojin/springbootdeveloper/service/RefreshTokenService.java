package me.soojin.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.soojin.springbootdeveloper.domain.RefreshToken;
import me.soojin.springbootdeveloper.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
 private final RefreshTokenRepository refreshTokenRepository;
 public RefreshToken findByRefreshToken(String refreshToken) {
     return refreshTokenRepository.findByRefreshToken(refreshToken)
             .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
     }
}
