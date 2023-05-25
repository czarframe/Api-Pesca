package com.projeto.pesca.service;

import com.projeto.pesca.domain.Peixe;
import com.projeto.pesca.repository.PeixeRepository;
import com.projeto.pesca.service.exception.DatabaseException;
import com.projeto.pesca.service.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PeixeService {

    @Autowired
    private PeixeRepository repository;

    public Page<Peixe> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Peixe findById(Long id) {
        Optional<Peixe> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Peixe insert(Peixe obj) {
        return repository.save(obj);
    }

    public Peixe update(Long id, Peixe obj) {
        try {
            Peixe entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Peixe entity, Peixe obj) {
        entity.setNome(obj.getNome());
        entity.setPeso(obj.getPeso());
        entity.setComprimento(obj.getComprimento());
    }

    public void delete(Long id) {
        try {
            repository.findById(id);
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}