package io.github.attornatusdesafio.repository;

import io.github.attornatusdesafio.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
