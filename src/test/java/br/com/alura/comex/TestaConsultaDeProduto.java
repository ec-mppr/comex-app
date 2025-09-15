package br.com.alura.comex;

import br.com.alura.comex.dao.ProdutoDao;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;

import java.util.List;

public class TestaConsultaDeProduto {

    public static void main(String[] args) {
        ProdutoDao produtoDao = new ProdutoDao();
        List<Produto> produtos = produtoDao.listaTodos();

        for (Produto produto : produtos) {
            System.out.println("ID: " + produto.getId());
            System.out.println("NOME: " + produto.getNome());

            System.out.println("CATEGORIAS:");
            for (Categoria categoria : produto.getCategorias()) {
                System.out.println("  " + categoria.getId() + " => " + categoria.getNome());
            }

            System.out.println("========================================");
            System.out.println();
        }

    }
}
