package br.com.loth.financeiro.controller;

import br.com.loth.financeiro.api.LancamentoRequest;
import br.com.loth.financeiro.api.ResumoFinanceiro;
import br.com.loth.financeiro.model.Lancamento;
import br.com.loth.financeiro.service.FinanceFacade;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {
    private final FinanceFacade financeFacade;

    public LancamentoController(FinanceFacade financeFacade) {
        this.financeFacade = financeFacade;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Lancamento adicionar(@Valid @RequestBody LancamentoRequest request) {
        return financeFacade.adicionar(request);
    }

    @GetMapping
    public List<Lancamento> listar() {
        return financeFacade.listar();
    }

    @GetMapping("/resumo")
    public ResumoFinanceiro resumo() {
        return financeFacade.resumir();
    }
}
