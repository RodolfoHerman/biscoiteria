package br.com.rodolfo.biscoiteria.domain.model.dto;

import java.math.BigDecimal;

import br.com.rodolfo.biscoiteria.domain.model.ProdutoCategoria;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoEncomenda;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal precoVenda;

    private boolean ativo;

    private ProdutoCategoria categoria;

    private ProdutoEncomenda encomenda;
}
