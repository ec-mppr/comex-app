package br.com.alura.comex.categoria;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/lista")
    public ResponseEntity<List<CategoriaDTO>> lista() {
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        List<CategoriaDTO> listaCategoriaDTOs = listaCategorias.stream().map(CategoriaDTO::new).toList();
        return ResponseEntity.ok(listaCategoriaDTOs);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<CategoriaDTO> cadastro(@RequestBody @Valid CategoriaDTO request,
            UriComponentsBuilder uriBuilder) {
        Categoria categoria = Categoria.fromRecord(request);
        categoriaRepository.save(categoria);

        var uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoria.getId()).toUri();

        return ResponseEntity.created(uri).body(new CategoriaDTO(categoria));
    }

    @GetMapping("/busca/{id}")
    public ResponseEntity<CategoriaDTO> busca(@PathVariable Long id) {
        Categoria categoria = categoriaRepository.getReferenceById(id);
        CategoriaDTO categoriaDTO = new CategoriaDTO(categoria);
        return ResponseEntity.ok(categoriaDTO);
    }

    @DeleteMapping("/deleta/{id}")
    @Transactional
    public ResponseEntity<Void> deleta(@PathVariable Long id) {
        var categoria = categoriaRepository.getReferenceById(id);
        categoria.delete();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualiza")
    @Transactional
    public ResponseEntity<CategoriaDTO> atualiza(@RequestBody @Valid CategoriaUpdateRequest request) {
        var categoria = categoriaRepository.getReferenceById(request.id());
        categoria.update(request);
        return ResponseEntity.ok(new CategoriaDTO(categoria));
    }

}
