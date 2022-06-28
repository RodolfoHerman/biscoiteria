package br.com.rodolfo.biscoiteria.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rodolfo.biscoiteria.domain.exception.NegocioException;
import br.com.rodolfo.biscoiteria.domain.exception.UsuarioEmUsoException;
import br.com.rodolfo.biscoiteria.domain.exception.UsuarioNaoEncontradoException;
import br.com.rodolfo.biscoiteria.domain.model.Grupo;
import br.com.rodolfo.biscoiteria.domain.model.Usuario;
import br.com.rodolfo.biscoiteria.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        usuarioRepository.detach(usuario);

        Optional<Usuario> usuarioSalvo = usuarioRepository.findByTelefone(usuario.getTelefone());

        if(usuarioSalvo.isPresent() && !usuarioSalvo.get().equals(usuario)) {
            throw new NegocioException(
                String.format("Já existe um usuário cadastrado com telefone: %s", usuario.getTelefone()));
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void excluir(Long id) {
        try {
            usuarioRepository.deleteById(id);
            usuarioRepository.flush();

        } catch (EmptyResultDataAccessException ex) {
            throw new UsuarioNaoEncontradoException(id);
        } catch (DataIntegrityViolationException ex) {
            throw new UsuarioEmUsoException(id);
        }
    }

    @Transactional
    public void associarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);

        usuario.adicionarGrupo(grupo);
    }

    @Transactional
    public void desassociarGrupo(Long usuarioId, Long grupoId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);

        usuario.removerGrupo(grupo);
    }

    public Usuario buscarOuFalhar(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new UsuarioNaoEncontradoException(id));
    }
}
