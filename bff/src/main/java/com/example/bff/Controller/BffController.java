package com.example.bff.Controller;

import com.example.bff.service.Oauth2JwtService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BffController {

    private final Oauth2JwtService oauth2JwtService;

    public BffController(Oauth2JwtService oauth2JwtService){
        this.oauth2JwtService = oauth2JwtService;
    }

     @GetMapping("/")
    public String home(){
        return "index";
     }
}
