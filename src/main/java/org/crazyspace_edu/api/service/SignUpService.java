package org.crazyspace_edu.api.service;

import lombok.RequiredArgsConstructor;
import org.crazyspace_edu.api.exception.AlreadyExistsEmailException;
import org.crazyspace_edu.api.repository.UserRepository;
import org.crazyspace_edu.api.request.SignUpRequest;
import org.crazyspace_edu.api.domain.user.AgreeYN;
import org.crazyspace_edu.api.domain.user.User;
import org.crazyspace_edu.api.domain.user.UserStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignUpRequest signUpRequest) {

        Optional<User> userOptional = userRepository.findByEmail(signUpRequest.getEmail());

        if (userOptional.isPresent()) {
            throw new AlreadyExistsEmailException();
        }

        // 비밀번호 암호화
        String encryptedPassword = passwordEncoder.encode(signUpRequest.getPassword());


        var user = User.builder()
                .user_email(signUpRequest.getEmail())
                .user_password(encryptedPassword)
                .user_phone(signUpRequest.getPhone())
                .username(signUpRequest.getUsername())
                .user_birth(signUpRequest.getBirth())
                .userLocBaseSvcAgmtYN(AgreeYN.Y)
                .userMktInfoRecvAgmtYN(AgreeYN.Y)
                .userPushYN(AgreeYN.Y)
                .userPsInfoProcAgmtYN(AgreeYN.Y)
                .userStatus(UserStatus.ACTIVE)
                .build();

        userRepository.save(user);
    }
}
