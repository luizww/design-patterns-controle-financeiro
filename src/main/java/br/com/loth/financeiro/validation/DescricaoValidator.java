package br.com.loth.financeiro.validation;

import br.com.loth.financeiro.api.LancamentoRequest;

public class DescricaoValidator extends ValidadorLancamento {
    @Override
    protected void verificar(LancamentoRequest request) {
        if (request.descricao() == null || request.descricao().trim().length() < 3) {
            throw new IllegalArgumentException("A descrição precisa ter pelo menos 3 caracteres.");
        }
    }
}
