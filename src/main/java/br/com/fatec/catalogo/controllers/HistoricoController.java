package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;

@Controller
public class HistoricoController {

    @Autowired
    private ProdutoService service;

    @GetMapping("/historico")
    public String historico(Model model, Authentication authentication) {

        boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("produtos", service.listarTodos());
        return "historico";
    }
}