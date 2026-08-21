package br.com.loth.financeiro.validation;

import br.com.loth.financeiro.api.LancamentoRequest;

public class CategoriaValidator extends ValidadorLancamento {
    @Override
    protected void verificar(LancamentoRequest request) {
        if (request.categoria() == null || request.categoria().isBlank()) {
            throw new IllegalArgumentException("A categoria é obrigatória.");
        }
    }
}
