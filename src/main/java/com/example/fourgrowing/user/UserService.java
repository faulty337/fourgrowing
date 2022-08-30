package com.example.fourgrowing.user;


import com.example.fourgrowing.user.data.dto.UserCreateDto;
import com.example.fourgrowing.user.data.dto.UsersDto;
import com.example.fourgrowing.user.data.entity.UserData;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
	private ModelMapper modelMapper;

    public UserData create(UserCreateDto userCreateDto) {
        UserData user = new UserData();
        user.setUsername(userCreateDto.getUsername());
        user.setEmail(userCreateDto.getEmail());
        user.setCreateTime(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setGender(userCreateDto.getGender());
        user.setPhoneNumber(userCreateDto.getPhoneNumber());
        user.setAge(userCreateDto.getAge());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword1()));
        this.userRepository.save(user);
        return user;
    }

    public List<UsersDto> getUsers(){
		return userRepository.findAll().stream().map(users -> modelMapper.map(users, UsersDto.class)).collect(Collectors.toList());
	}
	
	@Transactional
	public UsersDto newUsers(UsersDto usersDto) {
		// 사용자 저장
		// 암호화
		usersDto.setPassword(new BCryptPasswordEncoder().encode(usersDto.getPassword()));
		UsersDto resultDto = modelMapper.map(userRepository.save(usersDto.toEntity()), UsersDto.class);
		// 권한 저장
		// authoritiesRepository.save(Authorities.builder()
		// 									.username(usersDto.getUsername())
		// 									.authority("ROLE_ADMIN")
		// 									.build());
		return resultDto;
	}
}
