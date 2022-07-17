package br.com.rodolfo.biscoiteria.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import br.com.rodolfo.biscoiteria.domain.model.Produto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProdutoSpecs {

    public static Specification<Produto> comNomeSemelhante(String nome) {
        return (root, query, builder) -> {
            if(Produto.class.equals(query.getResultType())) {
                root.fetch("categoria");
            }

            var predicates = new ArrayList<Predicate>();

            if(StringUtils.isNotBlank(nome)) {
                predicates.add(builder.like(root.get("nome"), "%".concat(nome).concat("%")));
            }

            return builder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
