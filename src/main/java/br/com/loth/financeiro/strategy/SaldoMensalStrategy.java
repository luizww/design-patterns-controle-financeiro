package br.com.loth.financeiro.strategy;

import br.com.loth.financeiro.model.Lancamento;
import br.com.loth.financeiro.model.TipoLancamento;

import java.math.BigDecimal;
import java.util.List;

public class SaldoMensalStrategy implements CalculoFinanceiro {
    @Override
    public BigDecimal calcular(List<Lancamento> lancamentos) {
        return lancamentos.stream()
                .map(lancamento -> lancamento.getTipo() == TipoLancamento.RECEITA
                        ? lancamento.getValor() : lancamento.getValor().negate())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
