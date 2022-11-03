package com.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clients.requests.FindByPageRequest;
import com.clients.responses.FindByPageResponse;
import com.dto.CategoryDTO;
import com.entities.CategoryEntity;
import com.services.iml.ImlCategoryService;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private ImlCategoryService categoryService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<CategoryEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return categoryService.findByPage(finByPageRequest);
    }

    @GetMapping("/find-by-page")
    public FindByPageResponse<CategoryEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return categoryService.findByPageParam(pageNumber, pageSize);
    }

    @GetMapping("/read-by-id/{id}")
    public ResponseEntity<Optional<CategoryEntity>> readById(@PathVariable Integer id) throws Exception {
        try {
            categoryService.readById(id);
            return ResponseEntity.ok().body(categoryService.readById(id));
        } catch (Exception e) {
            throw new Exception("Bản ghi không tồn tại");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<CategoryEntity> create( @Valid @RequestBody CategoryEntity category) throws Exception {
        try {
             categoryService.create(category);
            return ResponseEntity.ok().body(category);
        } catch (Exception e) {
            throw new Exception("Create failed");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<CategoryEntity> update( @Valid @RequestBody CategoryEntity category) throws Exception {

        try {
            categoryService.update(category);
            return ResponseEntity.ok().body(category);
        } catch (Exception e) {
            throw new Exception("Update failed");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CategoryEntity> delete(@PathVariable Integer id) throws Exception {
        try {
            categoryService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            throw new Exception("Delete failed");
        }
    }

    @PostMapping("/search")
    public FindByPageResponse<CategoryEntity> viewCate(
            @RequestBody CategoryDTO dto,
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber
            , @RequestParam(name = "pageSize", defaultValue = "2") Integer pageSize) {
        return categoryService.listAll(dto, pageNumber, pageSize);
    }

}
