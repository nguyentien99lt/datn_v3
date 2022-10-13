package com.controllers;

import com.clients.requests.FindByPageRequest;
import com.clients.responses.FindByPageResponse;
import com.entities.ProductEntity;
import com.services.iml.ImlProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {
    @Autowired
    private ImlProductService productService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<ProductEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return productService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<ProductEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return productService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/readById/{id}")
    public ResponseEntity<Optional<ProductEntity>> readById(@PathVariable Integer id) throws Exception {
        try {
            productService.readById(id);
            return ResponseEntity.ok().body(productService.readById(id));
        }catch (Exception e){
            throw new Exception("Bản ghi không tồn tại");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ProductEntity> create(@RequestBody ProductEntity product) throws Exception {
        try {
            productService.create(product);
            return ResponseEntity.ok().body(product);
        }catch (Exception e){
            throw new Exception("Create Failed");

        }
    }

    @PutMapping("/update")
    public ResponseEntity<ProductEntity> update(@RequestBody ProductEntity product) throws Exception {
        try {
            productService.update(product);
            return ResponseEntity.ok().body(product);
        }catch (Exception e){
            throw  new Exception("Update Failed");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductEntity> delete(@PathVariable Integer id) throws Exception {
        try {
            productService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            throw  new Exception("Delete Failed");
        }
    }
}
