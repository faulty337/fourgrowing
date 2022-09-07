package com.example.fourgrowing.controller;

import com.example.fourgrowing.service.UserService;
import com.example.fourgrowing.data.dto.UserCreateAdminDto;
import com.example.fourgrowing.data.dto.UserCreateDto;
import com.example.fourgrowing.data.dto.UsersDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserCreateDto userCreateDto) {
        return "account/signup";
    }
    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("/profile")
    public String profile() {
        return "account/profile";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateDto userCreateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "account/signup";
        }

        if (!userCreateDto.getPassword1().equals(userCreateDto.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "account/signup";
        }

        try {
            userService.create(userCreateDto);
        } catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "account/signup";
        } catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "account/signup";
        }

        return "account/login";
    }

    @GetMapping("/admin")
	public @ResponseBody List<UsersDto> getUsers() {
		return userService.getUsers();
	}
	
	@PostMapping("/admin")
	public RedirectView newUsers(@ModelAttribute UserCreateAdminDto userCreateAdminDto) {
		userService.newUsers(userCreateAdminDto);
		return new RedirectView("/admin/users");
	}
}