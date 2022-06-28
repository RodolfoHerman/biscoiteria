package br.com.rodolfo.biscoiteria.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.rodolfo.biscoiteria.api.mapper.GrupoInputDemapper;
import br.com.rodolfo.biscoiteria.api.mapper.GrupoModelMapper;
import br.com.rodolfo.biscoiteria.api.model.GrupoModel;
import br.com.rodolfo.biscoiteria.api.model.input.GrupoInput;
import br.com.rodolfo.biscoiteria.domain.model.Grupo;
import br.com.rodolfo.biscoiteria.domain.repository.GrupoRepository;
import br.com.rodolfo.biscoiteria.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Autowired
    private GrupoModelMapper grupoModelMapper;

    @Autowired
    private GrupoInputDemapper grupoInputDemapper;

    @GetMapping
    public List<GrupoModel> listar() {
        return grupoModelMapper.toCollection(grupoRepository.findAll());
    }

    @GetMapping("/{grupo-id}")
    public GrupoModel buscar(@PathVariable("grupo-id") Long id) {
        return grupoModelMapper.toModel(cadastroGrupoService.buscarOuFalhar(id));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public GrupoModel salvar(@RequestBody @Valid GrupoInput grupoInput) {
        Grupo grupo = grupoInputDemapper.toDomainObject(grupoInput);

        return grupoModelMapper.toModel(cadastroGrupoService.salvar(grupo));
    }

    @PutMapping("/{grupo-id}")
    public GrupoModel atualizar(
        @PathVariable("grupo-id") Long id,
        @RequestBody @Valid GrupoInput grupoInput
    ) {
        Grupo grupo = cadastroGrupoService.buscarOuFalhar(id);

        grupoInputDemapper.copyToDomainObject(grupoInput, grupo);

        return grupoModelMapper.toModel(cadastroGrupoService.salvar(grupo));
    }

    @DeleteMapping("/{grupo-id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("grupo-id") Long id) {
        cadastroGrupoService.excluir(id);
    }
}
