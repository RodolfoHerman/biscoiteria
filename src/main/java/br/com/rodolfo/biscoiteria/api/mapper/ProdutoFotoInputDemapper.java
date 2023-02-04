package br.com.rodolfo.biscoiteria.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.web.multipart.MultipartFile;

import br.com.rodolfo.biscoiteria.domain.model.Produto;
import br.com.rodolfo.biscoiteria.domain.model.ProdutoFoto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, imports = { })
public interface ProdutoFotoInputDemapper {

    @Mapping(target = "contentType", source = "arquivo.contentType")
    @Mapping(target = "nomeArquivo", source = "arquivo.originalFilename")
    @Mapping(target = "tamanho", source = "arquivo.size")
    @Mapping(target = "produto", source = "produto")
    ProdutoFoto toDomainObject(Produto produto, MultipartFile arquivo);
}
