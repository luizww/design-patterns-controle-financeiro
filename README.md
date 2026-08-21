# Controle Financeiro

API REST feita em Java com Spring Boot para registrar receitas e despesas e acompanhar o saldo.

## O que o projeto faz

- cadastra receitas e despesas;
- lista os lançamentos cadastrados;
- calcula receitas, despesas e saldo;
- mostra quanto foi gasto em cada categoria;
- valida os dados antes de salvar.

Os dados ficam em memória para manter o foco no exercício de padrões de projeto. Ao reiniciar a aplicação, os lançamentos são apagados.

## Padrões utilizados

### Facade

`FinanceFacade` concentra o fluxo principal da aplicação. O controller não precisa conhecer repositório, validações ou cálculo do saldo.

### Strategy

`CalculoFinanceiro` define a operação de cálculo e `SaldoMensalStrategy` implementa a regra atual. Assim, uma nova forma de cálculo pode ser adicionada sem alterar a fachada.

### Chain of Responsibility

Os validadores de descrição e categoria são encadeados. Cada um verifica sua própria regra e passa a requisição adiante.

### Singleton do Spring

As classes `@Service`, `@Repository` e a estratégia registrada como `@Bean` são gerenciadas pelo Spring como instâncias únicas durante a execução da aplicação.

## Como executar

É necessário ter Java 17 e Maven instalados.

```bash
mvn spring-boot:run
```

## Exemplos

Cadastrar uma receita:

```http
POST http://localhost:8080/lancamentos
Content-Type: application/json

{
  "descricao": "Salário",
  "valor": 2500.00,
  "tipo": "RECEITA",
  "categoria": "Trabalho",
  "data": "2026-08-20"
}
```

Consultar o resumo:

```http
GET http://localhost:8080/lancamentos/resumo
```
