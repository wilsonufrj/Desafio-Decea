/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.decea.Controller;

import com.br.decea.DTO.ClientDTORequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.br.decea.Service.IClientService;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author wilson
 */
@RestController
@RequestMapping(path = "/users")
public class ClientController {
        
    @Autowired
    private IClientService userService;
    
    @PostMapping
    public ResponseEntity create(@RequestBody ClientDTORequest clientDTO){
        return ResponseEntity.ok(userService.create(clientDTO));
    }
    
    @GetMapping(value="/list")
    public ResponseEntity listUser(){
        return ResponseEntity.ok(userService.listUser());
    }
    
    //Receber o usuario pelo header
    @GetMapping
    public ResponseEntity login(@RequestBody ClientDTORequest clientDTO){
        try{
            return ResponseEntity.ok(userService.login(clientDTO));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
    
    
}
