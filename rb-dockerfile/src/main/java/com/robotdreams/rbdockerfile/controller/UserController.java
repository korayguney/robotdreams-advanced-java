package com.robotdreams.rbdockerfile.controller;

import com.robotdreams.rbdockerfile.entity.Users;
import com.robotdreams.rbdockerfile.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;

    @GetMapping("/index")
    public String showUserList(Model theModel){
        theModel.addAttribute("users", repository.findAll());
        return "index";
    }

    @GetMapping("/signup")
    public String showSignupPage(Users user){
        return "adduser";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid Users user, BindingResult result, Model theModel){
        if(result.hasErrors()){
            return "adduser";
        }

        repository.save(user);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdatePage(@PathVariable int id, Model theModel){
        Users user = repository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid user id: " + id));
        theModel.addAttribute("user",user);
        return "updateuser";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable int id, @Valid Users users, BindingResult result, Model theModel){
        if(result.hasErrors()){
            return "updateuser";
        }

        repository.save(users);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id){
        Users user = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user : " + id));
        repository.delete(user);

        return "redirect:/index";
    }

}
