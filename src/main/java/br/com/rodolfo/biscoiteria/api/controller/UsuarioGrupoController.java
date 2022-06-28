package br.com.rodolfo.biscoiteria.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodolfo.biscoiteria.api.mapper.GrupoModelMapper;
import br.com.rodolfo.biscoiteria.api.model.GrupoModel;
import br.com.rodolfo.biscoiteria.domain.model.Usuario;
import br.com.rodolfo.biscoiteria.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios/{usuario-id}/grupos")
public class UsuarioGrupoController {

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private GrupoModelMapper grupoModelMapper;

    @GetMapping
    public List<GrupoModel> listar(@PathVariable("usuario-id") Long id) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(id);

        return grupoModelMapper.toCollection(usuario.getGrupos());
    }

    @PutMapping("/{grupo-id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void associarGrupo(
        @PathVariable("usuario-id") Long usuarioId,
        @PathVariable("grupo-id") Long grupoId
    ) {
        cadastroUsuarioService.associarGrupo(usuarioId, grupoId);
    }

    @DeleteMapping("/{grupo-id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void desassociarGrupo(
        @PathVariable("usuario-id") Long usuarioId,
        @PathVariable("grupo-id") Long grupoId
    ) {
        cadastroUsuarioService.desassociarGrupo(usuarioId, grupoId);
    }
}
