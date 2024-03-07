package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {


    List<Categoria> findAllCategoriaOrderByNombre();
    List<Categoria> findAllCategoriaOrderByNombreReversed();



}
//JSON IGNORE EN LA PARTE de la coleccion que tiene el one
// y ToString.exclude