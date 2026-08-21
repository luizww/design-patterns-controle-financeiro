package br.com.loth.financeiro.strategy;

import br.com.loth.financeiro.model.Lancamento;

import java.math.BigDecimal;
import java.util.List;

public interface CalculoFinanceiro {
    BigDecimal calcular(List<Lancamento> lancamentos);
}
