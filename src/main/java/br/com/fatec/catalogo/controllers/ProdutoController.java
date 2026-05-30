package br.com.fatec.catalogo.controllers;

import br.com.fatec.catalogo.models.ProdutoModel;
import br.com.fatec.catalogo.services.CategoriaService;
import br.com.fatec.catalogo.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    public String listar(@RequestParam(value = "nome", required = false) String nome,
                         @RequestParam(value = "categoriaId", required = false) Long categoriaId,
                         Model model,
                         Authentication authentication) {

        boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);

        if (nome != null && !nome.isBlank()) {
            model.addAttribute("produtos", isAdmin
                    ? service.listarPorNome(nome)
                    : service.listarPorNomeOrdenadoPorId(nome));
        } else if (categoriaId != null) {
            model.addAttribute("produtos", isAdmin
                    ? service.listarPorCategoria(categoriaId)
                    : service.listarPorCategoriaOrdenadoPorId(categoriaId));
        } else {
            model.addAttribute("produtos", service.listarTodosOrdenadoPorId());
        }

        model.addAttribute("categorias", categoriaService.listarTodas());

        return "lista-produtos";
    }

    @GetMapping("/novo")
    public String exibirFormulario(Model model) {
        model.addAttribute("produto", new ProdutoModel());
        model.addAttribute("categorias", categoriaService.listarTodas()); // Garante que o select funcione
        return "cadastro-produto";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("produto") ProdutoModel produto,
                         BindingResult result,
                         Model model,
                         RedirectAttributes attrs) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listarTodas());
            return "cadastro-produto";
        }
        try{
            boolean isNovo = (produto.getIdProduto() == 0);;
            service.salvar(produto);

            String horario = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm"));
            String acao = isNovo ? "cadastrado" : "atualizado";
            attrs.addFlashAttribute("sucesso",
                    "Produto \"" + produto.getNome() + "\" " + acao + " com sucesso em " + horario + ".");

            return "redirect:/produtos";
        }catch (IllegalArgumentException e){
            model.addAttribute("erroQuantidade", e.getMessage());
            model.addAttribute("categorias", categoriaService.listarTodas());
            return "cadastro-produto";
        }
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable long id, Model model) {
        model.addAttribute("produto", service.buscarPorId(id));
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "editar-produto"; // Reutilizamos o mesmo form para editar
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable long id, RedirectAttributes attrs) {
        try {
            service.excluir(id);
            attrs.addFlashAttribute("sucesso", "Produto excluído com sucesso.");
        } catch (Exception e) {
            attrs.addFlashAttribute("erro", "Não foi possível excluir o produto.");
        }
        return "redirect:/produtos";
    }

    @PostMapping("/editar/{id}")
    public String salvarEdicao(@PathVariable long id,
                               @Valid @ModelAttribute("produto") ProdutoModel produto,
                               BindingResult result,
                               Model model,
                               RedirectAttributes attrs) {
        if (result.hasErrors()) {
            model.addAttribute("categorias", categoriaService.listarTodas());
            return "editar-produto";
        }
        try {
            produto.setIdProduto(id);
            service.salvar(produto);

            String horario = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm"));
            attrs.addFlashAttribute("sucesso",
                    "Produto \"" + produto.getNome() + "\" atualizado com sucesso em " + horario + ".");

            return "redirect:/produtos";
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("categorias", categoriaService.listarTodas());
            return "editar-produto";
        }
    }

    @GetMapping("/historico")
    public String historico(Model model) {
        return "historico";
    }

    @Autowired
    private CategoriaService categoriaService;


}