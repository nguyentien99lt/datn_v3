package com.controllers;

import com.clients.requests.FindByPageRequest;
import com.clients.responses.FindByPageResponse;
import com.entities.BrandEntity;
import com.services.iml.ImlBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@ResponseBody
@RequestMapping("/admin/brand")
@Controller
public class BrandController {
    @Autowired
    private ImlBrandService brandService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<BrandEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return brandService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<BrandEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                         @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return brandService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/readByID/{id}")
    public ResponseEntity<Optional<BrandEntity>> readById(@PathVariable Integer id) throws Exception {
        try {
            brandService.readById(id);
            return ResponseEntity.ok().body(brandService.readById(id));
        }catch (Exception e){
            throw new Exception("Bản ghi không tồn tại");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<BrandEntity> create(@Valid @RequestBody BrandEntity brand)throws Exception {
        try {
            brandService.create(brand);
            return ResponseEntity.ok().body(brand);
        }catch (Exception e){
            throw new Exception("Create Failed");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<BrandEntity> update(@Valid @RequestBody BrandEntity brand)throws Exception {
        try {
            brandService.update(brand);
            return ResponseEntity.ok().body(brand);
        }catch (Exception e){
            throw  new Exception("Update Failed");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BrandEntity> delete(@PathVariable Integer id) throws Exception {
        try {
            brandService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            throw new Exception("Delete Failed");
        }
    }


}
