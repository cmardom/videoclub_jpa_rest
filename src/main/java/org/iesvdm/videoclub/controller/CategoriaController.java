package org.iesvdm.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesvdm.videoclub.domain.Categoria;
import org.iesvdm.videoclub.domain.Pelicula;
import org.iesvdm.videoclub.service.CategoriaService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping(value = {"","/"}, params = {"!buscar", "!ordenar"})
    public List<Categoria> all() {
        log.info("Accediendo a todas las categorias");
        return this.categoriaService.all();
    }

    @GetMapping(value = {"", "/"}, params = {"page", "size"})
    public ResponseEntity<Map<String, Object>> allPages(@RequestParam(value = "page", defaultValue = "0") int page,
                                                        @RequestParam(value = "size", defaultValue = "0") int size){
        log.info("Accessing Paged Question List");

        Map<String, Object> responseAll = this.categoriaService.allPages(page, size);
        return ResponseEntity.ok(responseAll);
    }

    @GetMapping(value = {"", "/"}, params = {"buscar","page", "size"})
    public Page<Categoria> allPagesFilterByTextAsc(@RequestParam(value = "buscar", defaultValue = "") String buscar, @RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "0") int size){
        log.info("Accessing Paged categoria List Filtered By ID");

        return this.categoriaService.allPagesByTextAsc(buscar, page, size);

    }


//    @GetMapping(value = {"", "/"}, params = {"buscar","page", "size"})
//    public Page<Categoria> allPagesFilterByTextDesc(@RequestParam(value = "buscar", defaultValue = "") String buscar, @RequestParam(value = "page", defaultValue = "0") int page,
//                                                   @RequestParam(value = "size", defaultValue = "0") int size){
//        log.info("Accessing Paged categoria List Filtered By ID");
//
//        return this.categoriaService.allPagesByTextDesc(buscar, page, size);
//
//    }



    @PostMapping({"","/"})
    public Categoria newCategoria(@RequestBody Categoria categoria) {
        return this.categoriaService.save(categoria);
    }

    @GetMapping("/{id}")
    public Categoria one(@PathVariable("id") Long id) throws ChangeSetPersister.NotFoundException {
        return this.categoriaService.one(id);
    }

    @PutMapping("/{id}")
    public Categoria replaceCategoria(@PathVariable("id") Long id, @RequestBody Categoria categoria) {
        return this.categoriaService.replace(id, categoria);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable("id") Long id) throws ChangeSetPersister.NotFoundException {
        this.categoriaService.delete(id);
    }
}
