package com.example.bff.Controller;

import com.example.bff.service.Oauth2JwtService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class BffController {

    private final Oauth2JwtService oauth2JwtService;

    public BffController(Oauth2JwtService oauth2JwtService){
        this.oauth2JwtService = oauth2JwtService;
    }

    @GetMapping("/")
    public String index(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            populateDashboardModel(authentication, model);
        }
        return "index";
    }

    @GetMapping("/api/auth/check-session")
    public String checkSession(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            populateDashboardModel(authentication, model);
            return "index :: user-dashboard";
        }
        return "index :: empty-fragment";
    }

    @GetMapping("/views/landing")
    public String getLandingFragment(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            populateDashboardModel(authentication, model);
            return "index :: user-dashboard";
        }
        return "redirect:/";
    }

    // Helper method to keep code DRY (Don't Repeat Yourself)
    private void populateDashboardModel(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        model.addAttribute("roles", roles);
    }

    @GetMapping("/views/register")
    public String getRegisterFragment() {
        return "index :: register-form";
    }

}
