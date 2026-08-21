package br.com.loth.financeiro.config;

import br.com.loth.financeiro.strategy.CalculoFinanceiro;
import br.com.loth.financeiro.strategy.SaldoMensalStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinanceiroConfig {
    @Bean
    public CalculoFinanceiro calculoFinanceiro() {
        return new SaldoMensalStrategy();
    }
}
