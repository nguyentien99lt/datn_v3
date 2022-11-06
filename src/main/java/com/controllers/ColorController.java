package com.controllers;

import com.clients.requests.FindByPageRequest;
import com.clients.responses.FindByPageResponse;
import com.entities.ColorEntity;
import com.services.iml.ImlColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/admin/color")
@CrossOrigin
public class ColorController {
    @Autowired
    private ImlColorService colorService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<ColorEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return colorService.findByPage(finByPageRequest);
    }

    @GetMapping("/find-by-page")
    public FindByPageResponse<ColorEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return colorService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/readByID/{id}")
    public ResponseEntity<Optional<ColorEntity>> readById(@PathVariable Integer id) throws Exception {
        try {
            colorService.readById(id);
            return ResponseEntity.ok().body(colorService.readById(id));
        } catch (Exception e) {
            throw new Exception("Bản ghi không tồn tại");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ColorEntity> create(@Valid @RequestBody ColorEntity color) throws Exception {
        try {
            colorService.create(color);
            return ResponseEntity.ok().body(color);
        } catch (Exception e) {
            throw new Exception("Create Failed");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ColorEntity> update(@Valid @RequestBody ColorEntity color) throws Exception {
        try {
            colorService.update(color);
            return ResponseEntity.ok().body(color);
        } catch (Exception e) {
            throw new Exception("Update Failed");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ColorEntity> delete(@PathVariable Integer id) throws Exception {
        try {
            colorService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            throw new Exception("Delete Failed");
        }
    }
}
