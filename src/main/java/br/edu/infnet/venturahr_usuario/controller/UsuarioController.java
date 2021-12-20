package br.edu.infnet.venturahr_usuario.controller;

import br.edu.infnet.venturahr_usuario.model.domain.Usuario;
import br.edu.infnet.venturahr_usuario.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping ("/email/{email}")
    public ResponseEntity obterPorEmail(@PathVariable String email){
        ResponseEntity retorno = ResponseEntity.notFound().build();
        try {
            Usuario usuario = usuarioRepository.findByEmail(email);
            if (usuario != null){
                return retorno = ResponseEntity.ok().body(usuario);

            }

        }catch (Exception e){
        }
        return retorno;
    }

    @PostMapping
    public ResponseEntity inserirUsuario (@RequestBody Usuario usuario){
        ResponseEntity retorno = ResponseEntity.badRequest().build();
        if (usuario != null && usuario.getId() == null && usuario.getEmail() != null && usuario.getSenha() != null){
            Usuario usuarioInserido = usuarioRepository.save(usuario);
            return retorno = ResponseEntity.ok().body(usuarioInserido);

        }
        return retorno;
    }

    @PatchMapping
    public ResponseEntity atualizarUsuario (@RequestBody Usuario usuario){

        ResponseEntity retorno = ResponseEntity.badRequest().build();
        if (usuario != null && usuario.getId() != null){
            Usuario usuarioGravado = usuarioRepository.findById(usuario.getId()).orElse(null);
            if(usuarioGravado != null){
                try {
                    usuarioGravado = usuarioRepository.save(usuario);
                    return retorno = ResponseEntity.ok().body(usuarioGravado);

                }catch (Exception e){
                }
            }

        }
        return retorno;
    }

    @DeleteMapping
    public ResponseEntity excluirUsuario (@PathVariable Integer id){
        ResponseEntity retorno = ResponseEntity.notFound().build();
        if(id != null) {
            Usuario usuarioGravado = usuarioRepository.findById(id).orElse(null);

            if (usuarioGravado != null){
                usuarioRepository.deleteById(id);
                return retorno = ResponseEntity.ok().build();
            }
        }
        return  retorno;
    }



}
