package br.com.alura.comex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import br.com.alura.comex.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
