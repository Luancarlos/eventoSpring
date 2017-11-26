package br.com.tete.teste.repository;

import br.com.tete.teste.models.Evento;
import org.springframework.data.repository.CrudRepository;

public interface EventoRepository extends CrudRepository<Evento,String>{
    // criar metodo para buscar somente um registro
    Evento findByCodigo(long codigo);
}
