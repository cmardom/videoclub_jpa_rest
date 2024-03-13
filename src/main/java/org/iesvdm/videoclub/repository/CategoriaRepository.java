package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {


    //List<Categoria> findAllCategoriaByOrderByNombre();

    Page<Categoria> findAllByNombreOrderByNombreAsc(String buscar, Pageable pageable);
    Page<Categoria> findAllByNombreOrderByNombreDesc(String buscar, Pageable pageable);




}
//JSON IGNORE EN LA PARTE de la coleccion que tiene el one
// y ToString.exclude