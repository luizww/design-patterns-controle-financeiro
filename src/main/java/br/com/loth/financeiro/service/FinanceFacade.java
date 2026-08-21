package br.com.loth.financeiro.service;

import br.com.loth.financeiro.api.LancamentoRequest;
import br.com.loth.financeiro.api.ResumoFinanceiro;
import br.com.loth.financeiro.model.Lancamento;
import br.com.loth.financeiro.model.TipoLancamento;
import br.com.loth.financeiro.repository.LancamentoRepository;
import br.com.loth.financeiro.strategy.CalculoFinanceiro;
import br.com.loth.financeiro.validation.CategoriaValidator;
import br.com.loth.financeiro.validation.DescricaoValidator;
import br.com.loth.financeiro.validation.ValidadorLancamento;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class FinanceFacade {
    private final LancamentoRepository repository;
    private final CalculoFinanceiro calculoFinanceiro;
    private final ValidadorLancamento validadores;

    public FinanceFacade(LancamentoRepository repository, CalculoFinanceiro calculoFinanceiro) {
        this.repository = repository;
        this.calculoFinanceiro = calculoFinanceiro;
        this.validadores = new DescricaoValidator();
        this.validadores.depoisDe(new CategoriaValidator());
    }

    public Lancamento adicionar(LancamentoRequest request) {
        validadores.validar(request);
        return repository.salvar(new Lancamento(null, request.descricao().trim(), request.valor(),
                request.tipo(), request.categoria().trim(), request.data()));
    }

    public List<Lancamento> listar() {
        return repository.listar();
    }

    public ResumoFinanceiro resumir() {
        List<Lancamento> lancamentos = repository.listar();
        BigDecimal receitas = somarPorTipo(lancamentos, TipoLancamento.RECEITA);
        BigDecimal despesas = somarPorTipo(lancamentos, TipoLancamento.DESPESA);
        Map<String, BigDecimal> porCategoria = new LinkedHashMap<>();

        lancamentos.stream()
                .filter(l -> l.getTipo() == TipoLancamento.DESPESA)
                .forEach(l -> porCategoria.merge(l.getCategoria(), l.getValor(), BigDecimal::add));

        return new ResumoFinanceiro(receitas, despesas, calculoFinanceiro.calcular(lancamentos), porCategoria);
    }

    private BigDecimal somarPorTipo(List<Lancamento> lancamentos, TipoLancamento tipo) {
        return lancamentos.stream().filter(l -> l.getTipo() == tipo)
                .map(Lancamento::getValor).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
