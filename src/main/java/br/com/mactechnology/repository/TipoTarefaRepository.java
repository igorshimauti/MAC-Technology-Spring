package br.com.mactechnology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mactechnology.model.TipoTarefa;

@Repository
public interface TipoTarefaRepository extends JpaRepository<TipoTarefa, Long>{

}