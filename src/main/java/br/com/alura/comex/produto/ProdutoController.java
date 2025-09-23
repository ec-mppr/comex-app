package br.com.alura.comex.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.categoria.Categoria;
import br.com.alura.comex.categoria.CategoriaRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

  @Autowired
  private CategoriaRepository categoriaRepository;

  @Autowired
  private ProdutoRepository produtoRepository;

  @GetMapping("/lista")
  public ResponseEntity<List<Produto>> lista() {
    List<Produto> lista = produtoRepository.findAll();
    return new ResponseEntity<List<Produto>>(lista, HttpStatus.OK);
  }

  @PostMapping("/cadastro")
  public ResponseEntity<Object> cadastro(@RequestBody @Valid ProdutoDTO request) {
    Categoria categoria;

    Optional<Categoria> categoriaBuscada = categoriaRepository.findById(request.categoria());

    if (categoriaBuscada.isPresent()) {
      categoria = categoriaBuscada.get();
      Produto produto = Produto.fromRecord(request, categoria);
      produtoRepository.save(produto);
      return new ResponseEntity<>(produto, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Falha no cadastro: categoria do produto não encontrada", HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/busca")
  public ResponseEntity<Object> busca(@RequestBody Long id) {
    Optional<Produto> produtoBuscado = produtoRepository.findById(id);
    if (produtoBuscado.isPresent()) {
      Produto produto = produtoBuscado.get();
      return new ResponseEntity<>(produto, HttpStatus.OK);
    } else {
      return new ResponseEntity<Object>("Produto não encontrado", HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping("/deleta")
  public ResponseEntity<Object> deleta(@RequestBody Long id) {
    Optional<Produto> produtoBuscado = produtoRepository.findById(id);
    if (produtoBuscado.isPresent()) {
      String nomeProduto = produtoBuscado.get().getNome();
      produtoRepository.deleteById(id);
      return new ResponseEntity<>("Produto " + nomeProduto + " deletado", HttpStatus.OK);
    } else {
      return new ResponseEntity<Object>("Produto não encontrado", HttpStatus.BAD_REQUEST);
    }
  }

}
