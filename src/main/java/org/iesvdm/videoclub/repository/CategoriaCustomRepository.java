package org.iesvdm.videoclub.repository;

import org.iesvdm.videoclub.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaCustomRepository extends JpaRepository<Categoria, Long> {

    public List<Categoria> queryCustomCategoria(Optional<String> searchingOptional, Optional<String> orderingOptional);


}
