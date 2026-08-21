package br.com.loth.financeiro.repository;

import br.com.loth.financeiro.model.Lancamento;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LancamentoRepository {
    private final List<Lancamento> lancamentos = new ArrayList<>();
    private final AtomicLong proximoId = new AtomicLong(1);

    public Lancamento salvar(Lancamento lancamento) {
        Lancamento salvo = new Lancamento(proximoId.getAndIncrement(), lancamento.getDescricao(),
                lancamento.getValor(), lancamento.getTipo(), lancamento.getCategoria(), lancamento.getData());
        lancamentos.add(salvo);
        return salvo;
    }

    public List<Lancamento> listar() {
        return List.copyOf(lancamentos);
    }
}
