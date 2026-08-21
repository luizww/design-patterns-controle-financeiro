package br.com.loth.financeiro.validation;

import br.com.loth.financeiro.api.LancamentoRequest;

public abstract class ValidadorLancamento {
    private ValidadorLancamento proximo;

    public ValidadorLancamento depoisDe(ValidadorLancamento proximo) {
        this.proximo = proximo;
        return proximo;
    }

    public void validar(LancamentoRequest request) {
        verificar(request);
        if (proximo != null) {
            proximo.validar(request);
        }
    }

    protected abstract void verificar(LancamentoRequest request);
}
