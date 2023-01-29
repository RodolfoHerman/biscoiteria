package br.com.rodolfo.biscoiteria.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProdutoFoto {

    @Id
    @EqualsAndHashCode.Include
    @Column(name = "produto_id")
    private Long id;

    private String nomeArquivo;

    private String contentType;

    private Long tamanho;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private Produto produto;
}
