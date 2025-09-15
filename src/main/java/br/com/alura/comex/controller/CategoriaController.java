package br.com.alura.comex.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.model.Categoria;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @GetMapping("/lista")
    public ResponseEntity<String> lista() {
        return new ResponseEntity<String>("Lista de categorias", HttpStatus.OK);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastro(@RequestBody @Valid Categoria categoria) {
        return new ResponseEntity<String>("Cadastro de nova categoria: " + categoria.getNome(), HttpStatus.OK);
    }
}
