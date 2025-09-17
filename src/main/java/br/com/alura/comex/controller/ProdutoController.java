package br.com.alura.comex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.model.Produto;
import br.com.alura.comex.repository.CategoriaRepository;
import br.com.alura.comex.repository.ProdutoRepository;
import io.micrometer.core.ipc.http.HttpSender.Response;
import jakarta.validation.Valid;

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
  public ResponseEntity<Object> cadastro(@RequestBody @Valid ProdutoRequest request, BindingResult result) {
    Categoria categoria;
    System.out.println(result);
    if (result.hasFieldErrors()) {
      List<FieldError> errorList = result.getFieldErrors();
      Map<String, String> errors = new HashMap<>();

      for (FieldError error : errorList) {
        errors.put(error.getField(), error.getDefaultMessage());
      }

      return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
    }

    Optional<Categoria> categoriaBuscada = categoriaRepository.findById(request.categoria());

    if (categoriaBuscada.isPresent()) {
      categoria = categoriaBuscada.get();
      Produto produto = Produto.fromRecord(request, categoria);
      produtoRepository.save(produto);
      return new ResponseEntity<>(produto, HttpStatus.OK);
    } else {
      ErroResponse response = new ErroResponse("Falha no cadastro: categoria do produto não encontrada");
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
  }

}
