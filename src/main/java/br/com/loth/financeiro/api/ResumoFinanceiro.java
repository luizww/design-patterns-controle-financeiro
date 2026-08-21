package br.com.loth.financeiro.api;

import java.math.BigDecimal;
import java.util.Map;

public record ResumoFinanceiro(
        BigDecimal receitas,
        BigDecimal despesas,
        BigDecimal saldo,
        Map<String, BigDecimal> despesasPorCategoria
) {}
