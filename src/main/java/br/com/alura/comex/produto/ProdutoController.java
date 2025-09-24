package br.com.alura.comex.produto;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.comex.categoria.Categoria;
import br.com.alura.comex.categoria.CategoriaRepository;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  public ResponseEntity<ProdutoDTO> cadastro(@RequestBody @Valid ProdutoDTO request, UriComponentsBuilder uribuilder) {
    Categoria categoria = categoriaRepository.getReferenceById(request.categoria());
    Produto produto = Produto.fromRecord(request, categoria);
    URI uri = uribuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
    produtoRepository.save(produto);
    return ResponseEntity.created(uri).body(new ProdutoDTO(produto));
  }

  @GetMapping("/busca/{id}")
  public ResponseEntity<ProdutoDTO> busca(@PathVariable Long id) {
    Produto produto = produtoRepository.getReferenceById(id);
    return ResponseEntity.ok(new ProdutoDTO(produto));
  }

  @DeleteMapping("/deleta/{id}")
  public ResponseEntity<Void> deleta(@PathVariable Long id) {
    produtoRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
