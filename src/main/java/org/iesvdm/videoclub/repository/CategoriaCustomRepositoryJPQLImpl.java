package org.iesvdm.videoclub.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.iesvdm.videoclub.domain.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public abstract class CategoriaCustomRepositoryJPQLImpl implements CategoriaCustomRepository{
    @Autowired
    private EntityManager em;


    @Override
    public List<Categoria> queryCustomCategoria(Optional<String> searchingOptional, Optional<String> orderingOptional) {
        StringBuilder query = new StringBuilder("SELECT C FROM Categoria C");

        if (searchingOptional.isPresent()){
            query.append(" ").append("WHERE C.nombre like :nombre");
        }

        if (orderingOptional.isPresent()){
            if (searchingOptional.isPresent() && "asc".equalsIgnoreCase(searchingOptional.get())){
                query.append(" ").append("ORDER BY C.nombre ASC");
            } else if (searchingOptional.isPresent() && "desc".equalsIgnoreCase(searchingOptional.get())){
                query.append(" ").append("ORDER BY C.nombre DESC");
            }
        }

        Query createdQuery = em.createQuery(query.toString());
        searchingOptional.ifPresent(s -> createdQuery.setParameter("nombre", "%" + s + "%"));
        return createdQuery.getResultList();
    }
}
