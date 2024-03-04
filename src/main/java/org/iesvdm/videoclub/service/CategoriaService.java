package org.iesvdm.videoclub.service;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> all() {return this.categoriaRepository.findAll();}

    public Categoria save(Categoria categoria){ return this.categoriaRepository.save(categoria);}

    public Categoria one (Long id) throws ChangeSetPersister.NotFoundException {
        return this.categoriaRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public void delete(Long id) throws ChangeSetPersister.NotFoundException {
        this.categoriaRepository.findById(id).map(categoria -> {this.categoriaRepository.delete(categoria);
        return categoria;}).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
}
