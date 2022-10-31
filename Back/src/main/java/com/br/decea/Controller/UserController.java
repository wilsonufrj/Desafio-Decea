/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.decea.Controller;

import com.br.decea.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import com.br.decea.Service.IUserService;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author wilson
 */
@RestController
@RequestMapping(path = "/users")
@CrossOrigin
public class UserController {
        
    @Autowired
    private IUserService userService;
    
    @PostMapping
    public ResponseEntity create(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.create(userDTO));
    }
    
    @GetMapping(value="/list")
    public ResponseEntity listUser(){
        return ResponseEntity.ok(userService.listUser());
    }
    
    @PostMapping(value="/login")
    public ResponseEntity login(@RequestBody UserDTO userDTO) throws Exception{
        try{
            return ResponseEntity.ok(userService.login(userDTO));
        }catch(Exception e){
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
        
    
}
