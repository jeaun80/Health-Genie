package com.example.healthgenie.service;

import com.example.healthgenie.Email.EmailValidator;
import com.example.healthgenie.dto.userRegisterDto;
import com.example.healthgenie.entity.User;
import com.example.healthgenie.global.constants.constant;
import com.example.healthgenie.global.utils.basicUtils;
import com.example.healthgenie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final EmailValidator emailValidator;
    private final UserRepository userRepository;

    @Transactional
    public ResponseEntity<String> signUp(userRegisterDto request) {
        log.info("Inside signUp {}", request.getEmail());
        try {
            // 이메일 유효성 검사
            boolean isValidEmail = emailValidator.test(request.getEmail());

            if (!isValidEmail) {
                log.info("email not valid");
                return basicUtils.getResponseEntity(constant.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }

            // 유저 중복 체크
            boolean userExists = userRepository.findByEmail(request.getEmail()).isPresent();

            if (userExists) {
                log.info("이메일 중복 : " + request.getEmail());
                return basicUtils.getResponseEntity(constant.DUPLICATE_DATA, HttpStatus.BAD_REQUEST);
            }

            //return
        } catch (Exception e) {
            e.printStackTrace();
        }

        return basicUtils.getResponseEntity(constant.OK_GOOD, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public Optional<User> findOne(String email){
        return userRepository.findByEmail(email);
    }

}
