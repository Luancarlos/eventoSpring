package br.com.tete.teste.repository;

import br.com.tete.teste.models.Convidado;
import br.com.tete.teste.models.Evento;
import org.springframework.data.repository.CrudRepository;

public interface ConvidadoRepository extends CrudRepository<Convidado,String>{

    Iterable<Convidado> findByEvento(Evento evento);
    Convidado findByCpf(String cpf);
}
