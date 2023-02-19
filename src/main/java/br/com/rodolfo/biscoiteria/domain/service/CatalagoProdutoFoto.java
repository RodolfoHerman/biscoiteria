package br.com.rodolfo.biscoiteria.domain.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rodolfo.biscoiteria.domain.model.ProdutoFoto;
import br.com.rodolfo.biscoiteria.domain.repository.ProdutoRepository;
import br.com.rodolfo.biscoiteria.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalagoProdutoFoto {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FotoStorageService fotoStorageService;

    @Transactional
    public ProdutoFoto salvar(ProdutoFoto foto, InputStream stream) {
        var fotoExistente = produtoRepository.findFotoByProdutoId(
            foto.getProduto().getId());
        String nomeArquivo = fotoStorageService.gerarNomeArquivo(foto.getNomeArquivo());
        String nomeArquivoExistente = null;

        if(fotoExistente.isPresent()) {
            nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
            produtoRepository.delete(fotoExistente.get());
        }

        foto.setNomeArquivo(nomeArquivo);
        foto = produtoRepository.save(foto);
        produtoRepository.flush();

        NovaFoto novaFoto = NovaFoto.builder()
            .nomeArquivo(foto.getNomeArquivo())
            .inputStream(stream)
        .build();

        fotoStorageService.substituir(nomeArquivoExistente, novaFoto);

        return foto;
    }
}
