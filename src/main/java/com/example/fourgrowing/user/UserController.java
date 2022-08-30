package com.example.fourgrowing.user;

import com.example.fourgrowing.user.data.dto.UserCreateDto;
import com.example.fourgrowing.user.data.dto.UsersDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserCreateDto userCreateDto) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateDto userCreateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        if (!userCreateDto.getPassword1().equals(userCreateDto.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        try {
            userService.create(userCreateDto);
        } catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        } catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        return "signup_form";
    }

    @GetMapping("/admin")
	public @ResponseBody List<UsersDto> getUsers() {
		return userService.getUsers();
	}
	
	@PostMapping("/admin")
	public RedirectView newUsers(@ModelAttribute UsersDto userDto) {
		userService.newUsers(userDto);
		return new RedirectView("/admin/users");
	}
}