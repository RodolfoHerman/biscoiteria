package br.com.rodolfo.biscoiteria.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rodolfo.biscoiteria.domain.exception.GrupoEmUsoException;
import br.com.rodolfo.biscoiteria.domain.exception.GrupoNaoEncontradoException;
import br.com.rodolfo.biscoiteria.domain.model.Grupo;
import br.com.rodolfo.biscoiteria.domain.model.Permissao;
import br.com.rodolfo.biscoiteria.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroPermissaoService cadastroPermissaoService;

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    @Transactional
    public void excluir(Long id) {
        try {
            grupoRepository.deleteById(id);
            grupoRepository.flush();

        } catch (EmptyResultDataAccessException ex) {
            throw new GrupoNaoEncontradoException(id);
        } catch (DataIntegrityViolationException ex) {
            throw new GrupoEmUsoException(id);
        }
    }

    @Transactional
    public void associarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);

        grupo.adicionarPermissao(permissao);
    }

    @Transactional
    public void desaassociarPermissao(Long grupoId, Long permissaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);

        grupo.removerPermissao(permissao);
    }

    public Grupo buscarOuFalhar(Long id) {
        return grupoRepository.findById(id)
            .orElseThrow(() -> new GrupoNaoEncontradoException(id));
    }
}
