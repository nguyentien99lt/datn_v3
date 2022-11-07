package com.controllers;

import com.clients.requests.FindByPageRequest;
import com.clients.responses.FindByPageResponse;
import com.entities.SizeEntity;
import com.services.iml.ImlSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/admin/size")
public class SizeController {
    @Autowired
    private ImlSizeService sizeService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<SizeEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return sizeService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<SizeEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return sizeService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/readById/{id}")
    public ResponseEntity<Optional<SizeEntity>> readById(@PathVariable Integer id) throws Exception {

        try {
            sizeService.readById(id);
            return ResponseEntity.ok().body(sizeService.readById(id));
        }catch (Exception e){
            throw new Exception("Bản ghi không tồn tại");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<SizeEntity> create(@Valid @RequestBody SizeEntity size) throws Exception {
        try {
            sizeService.create(size);
            return  ResponseEntity.ok().body(size);
        }catch (Exception e){
            throw new Exception("Create Failed");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<SizeEntity> update(@Valid @RequestBody SizeEntity size) throws  Exception {
        try {
            sizeService.update(size);
            return ResponseEntity.ok().body(size);
        }catch (Exception e){
            throw new Exception("Update Failed");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SizeEntity> delete(@PathVariable Integer id) throws  Exception {
        try {
            sizeService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            throw  new Exception("Delete Failed");
        }
    }
}
