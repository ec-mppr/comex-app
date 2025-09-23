package br.com.alura.comex.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

  @Autowired
  ClienteRepository clienteRepository;

  @GetMapping("/lista")
  public ResponseEntity<List<Cliente>> lista() {
    List<Cliente> listaClientes = clienteRepository.findAll();
    return ResponseEntity.ok().body(listaClientes);
  }

  @PostMapping("/cadastro")
  public ResponseEntity<Cliente> cadastro(@RequestBody @Valid ClienteDTO request) {
    Cliente cliente = Cliente.fromRecord(request);
    clienteRepository.save(cliente);
    return ResponseEntity.ok().body(cliente);
  }
}
