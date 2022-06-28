package br.com.rodolfo.biscoiteria.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodolfo.biscoiteria.domain.exception.PermissaoNaoEncontradaException;
import br.com.rodolfo.biscoiteria.domain.model.Permissao;
import br.com.rodolfo.biscoiteria.domain.repository.PermissaoRepository;

@Service
public class CadastroPermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalhar(Long id) {
        return permissaoRepository.findById(id)
            .orElseThrow(() -> new PermissaoNaoEncontradaException(id));
    }
}
