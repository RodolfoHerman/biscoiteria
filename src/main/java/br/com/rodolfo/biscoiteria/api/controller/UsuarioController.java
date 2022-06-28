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

import br.com.rodolfo.biscoiteria.api.mapper.UsuarioInputDemapper;
import br.com.rodolfo.biscoiteria.api.mapper.UsuarioModelMapper;
import br.com.rodolfo.biscoiteria.api.mapper.UsuarioResumoModelMapper;
import br.com.rodolfo.biscoiteria.api.model.UsuarioModel;
import br.com.rodolfo.biscoiteria.api.model.UsuarioResumoModel;
import br.com.rodolfo.biscoiteria.api.model.input.UsuarioInput;
import br.com.rodolfo.biscoiteria.domain.model.Usuario;
import br.com.rodolfo.biscoiteria.domain.repository.UsuarioRepository;
import br.com.rodolfo.biscoiteria.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private UsuarioModelMapper usuarioModelMapper;

    @Autowired
    private UsuarioResumoModelMapper usuarioResumoModelMapper;

    @Autowired
    private UsuarioInputDemapper usuarioInputDemapper;

    @GetMapping
    public List<UsuarioResumoModel> listar() {
        return usuarioResumoModelMapper.toCollection(usuarioRepository.findAll());
    }

    @GetMapping("/{usuario-id}")
    public UsuarioModel buscar(@PathVariable("usuario-id") Long id) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(id);

        return usuarioModelMapper.toModel(usuario);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UsuarioModel salvar(@RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario usuario = usuarioInputDemapper.toDomainObject(usuarioInput);

        return usuarioModelMapper.toModel(cadastroUsuarioService.salvar(usuario));
    }

    @PutMapping("/{usuario-id}")
    public UsuarioModel atualizar(
        @PathVariable("usuario-id") Long id,
        @RequestBody @Valid UsuarioInput usuarioInput
    ) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(id);

        usuarioInputDemapper.copyToDomainObject(usuarioInput, usuario);

        return usuarioModelMapper.toModel(cadastroUsuarioService.salvar(usuario));
    }

    @DeleteMapping("/{usuario-id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("usuario-id") Long id) {
        cadastroUsuarioService.excluir(id);
    }
}
