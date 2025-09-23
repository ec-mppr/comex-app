package br.com.alura.comex.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
