package org.iesvdm.videoclub.service;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.exception.PeliculaNotFoundException;
import org.iesvdm.videoclub.repository.CategoriaRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> all() {

        return this.categoriaRepository.findAll();
    }

    public Categoria save(Categoria categoria){ return this.categoriaRepository.save(categoria);}

    public Categoria one (Long id) throws ChangeSetPersister.NotFoundException {
        return this.categoriaRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public void delete(Long id) throws ChangeSetPersister.NotFoundException {
        this.categoriaRepository.findById(id).map(categoria -> {this.categoriaRepository.delete(categoria);
        return categoria;}).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public Categoria replace(Long id, Categoria categoria) {

        return this.categoriaRepository.findById(id).map( p -> (id.equals(categoria.getId())  ?
                        this.categoriaRepository.save(categoria) : null))
                .orElseThrow(() -> new PeliculaNotFoundException(id));

    }

    public Map<String, Object> allPages(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Categoria> pageAll = this.categoriaRepository.findAll(pageable);
        Map<String, Object> response = new HashMap<>();

        response.put("categorias", pageAll.getContent());
        return response;
    }
    public Page<Categoria> allPagesByTextAsc(String buscar, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("buscar").ascending());
        Page<Categoria> pageAll = this.categoriaRepository.findAllByNombreOrderByNombreAsc(buscar,pageable);

        return pageAll;
    }

    public Page<Categoria> allPagesByTextDesc(String buscar, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("buscar").ascending());
        Page<Categoria> pageAll = this.categoriaRepository.findAllByNombreOrderByNombreDesc(buscar,pageable);

        return pageAll;
    }



//    public Page<Categoria> allByQueryFilterStream (Optional<String> searchingOptional, Optional <String> orderingOptional){
//        if (searchingOptional.isPresent() && orderingOptional.isPresent()){
//            if (orderingOptional.get().equals("asc")){
//                return this.categoriaRepository.findAllByNombreOrderByNombreAsc(searchingOptional.toString());
//            } else if (orderingOptional.get().equals("desc")){
//                return this.categoriaRepository.findAllByNombreOrderByNombreDesc(searchingOptional.toString());
//            } else {
//                return (Page<Categoria>) this.categoriaRepository.findAll();
//            }
//        }
//        return (Page<Categoria>) this.categoriaRepository.findAll();
//
//    }



}
