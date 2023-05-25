package com.projeto.pesca.resource;

import com.projeto.pesca.domain.Peixe;
import com.projeto.pesca.service.PeixeService;
import com.projeto.pesca.service.exception.DatabaseException;
import com.projeto.pesca.service.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/peixe")
public class PeixeController {

    @Autowired
    private PeixeService service;

    @GetMapping
    public ResponseEntity<Page<Peixe>> findAll(Pageable pageable) {
        Page<Peixe> pages = service.findAll(pageable);
        return ResponseEntity.ok().body(pages);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Peixe> findById(@PathVariable Long id) {
        Peixe obj = service.findById(id);
        if (obj == null) {
            throw new ResourceNotFoundException(id);
        }
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Peixe> insert(@RequestBody @Valid Peixe obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Peixe> update(@PathVariable Long id, @RequestBody @Valid Peixe obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            Peixe obj = service.findById(id);
            if (obj == null) {
                throw new ResourceNotFoundException(id);
            }
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (DatabaseException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}