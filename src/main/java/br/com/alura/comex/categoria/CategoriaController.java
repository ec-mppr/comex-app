package br.com.alura.comex.categoria;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.comex.shared.ErroResponse;

import org.springframework.web.bind.annotation.RequestParam;

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
    public ResponseEntity<String> cadastro(@RequestBody @Valid CategoriaDTO request) {
        Categoria categoria = Categoria.fromRecord(request);
        categoriaRepository.save(categoria);
        return new ResponseEntity<String>(
                "Nova categoria cadastrada: " + categoria.getNome(), HttpStatus.OK);
    }

    @GetMapping("/busca")
    public ResponseEntity<Object> busca(@RequestBody Long id) {
        Optional<Categoria> categoriaBuscada = categoriaRepository.findById(id);
        if (categoriaBuscada.isPresent()) {
            Categoria categoria = categoriaBuscada.get();
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(new ErroResponse("Categoria não encontrada"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleta")
    public ResponseEntity<Object> deleta(@RequestBody Long id) {
        Optional<Categoria> categoriaBuscada = categoriaRepository.findById(id);
        if (categoriaBuscada.isPresent()) {
            String nomeCategoria = categoriaBuscada.get().getNome();
            categoriaRepository.deleteById(id);
            return new ResponseEntity<>("Categoria " + nomeCategoria + " deletada", HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(new ErroResponse("Categoria não encontrada"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/atualiza")
    public ResponseEntity<Object> atualiza(@RequestBody @Valid CategoriaUpdateRequest request) {
        Optional<Categoria> categoriaBuscada = categoriaRepository.findById(request.id());
        if (categoriaBuscada.isPresent()) {
            Categoria categoria = categoriaBuscada.get();
            categoriaRepository.save(categoria);
            return new ResponseEntity<>("Categoria " + categoria.getNome() + " atualizada", HttpStatus.OK);
        } else {
            return new ResponseEntity<Object>(new ErroResponse("Categoria não encontrada"), HttpStatus.BAD_REQUEST);
        }
    }

}
