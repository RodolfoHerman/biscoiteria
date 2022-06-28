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

import br.com.rodolfo.biscoiteria.api.mapper.PermissaoModelMapper;
import br.com.rodolfo.biscoiteria.api.model.PermissaoModel;
import br.com.rodolfo.biscoiteria.domain.model.Grupo;
import br.com.rodolfo.biscoiteria.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos/{grupo-id}/permissoes")
public class GrupoPermissaoController {

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Autowired
    private PermissaoModelMapper permissaoModelMapper;

    @GetMapping
    public List<PermissaoModel> listar(@PathVariable("grupo-id") Long grupoId) {
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);

        return permissaoModelMapper.toCollection(grupo.getPermissoes());
    }

    @PutMapping("/{permissao-id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void associarPermissao(
        @PathVariable("grupo-id") Long grupoId,
        @PathVariable("permissao-id") Long permissaoId
    ) {
        cadastroGrupoService.associarPermissao(grupoId, permissaoId);
    }

    @DeleteMapping("/{permissao-id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void desassociarPermissao(
        @PathVariable("grupo-id") Long grupoId,
        @PathVariable("permissao-id") Long permissaoId
    ) {
        cadastroGrupoService.desaassociarPermissao(grupoId, permissaoId);
    }
}
