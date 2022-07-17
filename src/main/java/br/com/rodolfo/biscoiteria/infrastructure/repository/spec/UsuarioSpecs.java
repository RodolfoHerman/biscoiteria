package br.com.rodolfo.biscoiteria.infrastructure.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import br.com.rodolfo.biscoiteria.domain.model.Usuario;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UsuarioSpecs {

    public static Specification<Usuario> comNomeSemelhante(String nome) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if(StringUtils.isNotBlank(nome)) {
                predicates.add(builder.like(root.get("nome"), "%".concat(nome).concat("%")));
            }

            return builder.and(predicates.toArray(Predicate[]::new));
        };
    }
}
