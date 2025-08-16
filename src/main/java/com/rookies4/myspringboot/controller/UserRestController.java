package com.rookies4.myspringboot.controller;

import com.rookies4.myspringboot.entity.UserEntity;
import com.rookies4.myspringboot.exception.BusinessException;
import com.rookies4.myspringboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor //롬북으로 생성자를 자동으로 시켜줌, (Constructor Injection 대신에 사용)
@RequestMapping("/api/users")
public class UserRestController {
    private final UserRepository userRepository; //final을 적으면 변수를 무조권 초기화시킴

//    //Constructor Injection
//    public UserRestController(UserRepository userRepository) {
//        System.out.println("생성자 인젝션 " + userRepository.getClass().getName());
//        this.userRepository = userRepository;
//    }
    //등록
    @PostMapping
    public UserEntity create(@RequestBody UserEntity user){
        return userRepository.save(user);

    }
    //전체목록조회
    @GetMapping
    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }
    //ID로 조회
    @GetMapping("/{id}")//api/users/1
    public UserEntity getUser(@PathVariable Long id){ //@PathVariable  /1, /2같이 어떤 id를 가져오는지
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        //orElseThrow(Supplier) Supplier의 추상메서드 T get()
        UserEntity existUser = optionalUser.orElseThrow(()-> new BusinessException("User Not Found", HttpStatus.NOT_FOUND)); //실패하면 예외던지기(404)
        return existUser;
    }
}
