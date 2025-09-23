package br.com.alura.comex.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.comex.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
