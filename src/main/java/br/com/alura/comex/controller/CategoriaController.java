package br.com.alura.comex.controller;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.dao.CategoriaDao;
import br.com.alura.comex.model.Categoria;
import br.com.alura.comex.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/lista")
    public ResponseEntity<List<Categoria>> lista() {
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        return new ResponseEntity<List<Categoria>>(listaCategorias, HttpStatus.OK);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastro(@RequestBody @Valid CategoriaRequest request) {
        Categoria categoria = Categoria.fromRecord(request);
        System.out.println(categoria);
        categoriaRepository.save(categoria);
        return new ResponseEntity<String>(
                "Nova categoria cadastrada: " + categoria.getNome(), HttpStatus.OK);
    }
}
