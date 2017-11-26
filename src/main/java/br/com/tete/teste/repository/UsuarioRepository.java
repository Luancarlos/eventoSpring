package br.com.tete.teste.repository;


import br.com.tete.teste.models.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, String>{
    Usuario findByLogin(String login);
}
