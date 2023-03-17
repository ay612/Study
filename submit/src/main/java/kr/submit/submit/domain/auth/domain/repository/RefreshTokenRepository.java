package kr.submit.submit.domain.auth.domain.repository;

import kr.submit.submit.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String>{
    Optional<RefreshToken> findByToken(String token);
}
