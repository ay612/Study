package kr.submit.submit.domain.user.domain.repository;

import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public class UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findUserByNickname(String nickname);
    Optional<User> findByPhoneNumber(String phoneNumber);

}
