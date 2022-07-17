package br.com.rodolfo.biscoiteria.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import br.com.rodolfo.biscoiteria.domain.filter.PedidoFilter;
import br.com.rodolfo.biscoiteria.domain.model.Pedido;
import br.com.rodolfo.biscoiteria.domain.model.Usuario;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PedidoSpecs {

    public static Specification<Pedido> usandoFiltro(PedidoFilter pedidoFilter) {
        return (root, query, builder) -> {
            if(Pedido.class.equals(query.getResultType())) {
                root.fetch("cliente");
            }

            var predicates = new ArrayList<Predicate>();
            Path<Usuario> usuarioPath = root.get("cliente");

            if(StringUtils.isNotBlank(pedidoFilter.getNome())) {
                predicates.add(builder.like(usuarioPath.get("nome"), "%".concat(pedidoFilter.getNome()).concat("%")));
            }

            if(StringUtils.isNotBlank(pedidoFilter.getTelefone())) {
                predicates.add(builder.like(usuarioPath.get("telefone"), "%".concat(pedidoFilter.getTelefone()).concat("%")));
            }

            return builder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
