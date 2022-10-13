package com.controllers;

import com.clients.requests.FindByPageRequest;
import com.clients.requests.ProductDetailRequest;
import com.clients.responses.FindByPageResponse;
import com.entities.ProductDetailEntity;
import com.services.iml.ImlProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/product-detail")
@CrossOrigin
public class ProductDetailController {
    @Autowired
    private ImlProductDetailService productDetailService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<ProductDetailEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return productDetailService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<ProductDetailEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return productDetailService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/readById/{id}")
    public ResponseEntity<Optional<ProductDetailEntity>> readById(@PathVariable Integer id) throws Exception {
        try {
            productDetailService.readById(id);
            return ResponseEntity.ok().body(productDetailService.readById(id));
        }catch (Exception e){
            throw  new Exception("Bản ghi không tồn tại");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDetailEntity> create(@RequestBody ProductDetailEntity productDetail) throws Exception {
        try {
            productDetailService.create(productDetail);
            return ResponseEntity.ok().body(productDetail);
        }catch (Exception e){
            throw new Exception("Create Failed");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ProductDetailEntity> update(@RequestBody ProductDetailEntity productDetail) throws Exception {
        try {
            productDetailService.update(productDetail);
            return ResponseEntity.ok().body(productDetail);
        }catch (Exception e){
            throw  new Exception("Update Failed");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProductDetailEntity> delete(@PathVariable Integer id) throws Exception {
        try {
            productDetailService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            throw new Exception("Delete Failed");
        }
    }

    @PostMapping("/search-by-name")
    public FindByPageResponse<ProductDetailEntity> searchByName(@RequestBody ProductDetailRequest productDetailRequest,
                                                                @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
        return productDetailService.findByName(productDetailRequest,pageNumber, pageSize);
    }
    @PostMapping("/search-by-size")
    public FindByPageResponse<ProductDetailEntity> searchBySize(@RequestBody ProductDetailRequest productDetailRequest,
                                                                  @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
        return productDetailService.findBySize(productDetailRequest,pageNumber, pageSize);
    }
    @PostMapping("/search-by-price")
    public FindByPageResponse<ProductDetailEntity> searchByPrice(@RequestBody ProductDetailRequest productDetailRequest,
                                                                  @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
        return productDetailService.findByPrice(productDetailRequest,pageNumber, pageSize);
    }
    @PostMapping("/search-by-color")
    public FindByPageResponse<ProductDetailEntity> searchByColor(@RequestBody ProductDetailRequest productDetailRequest,
                                                                  @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
        return productDetailService.findByColor(productDetailRequest,pageNumber, pageSize);
    }
    @PostMapping("/search-by-brand-name")
    public FindByPageResponse<ProductDetailEntity> searchByBrandName(@RequestBody ProductDetailRequest productDetailRequest,
                                                                  @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                                  @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
        return productDetailService.findByBrand(productDetailRequest,pageNumber, pageSize);
    }
}
