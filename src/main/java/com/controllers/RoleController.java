package com.controllers;

import com.clients.requests.FindByPageRequest;
import com.clients.responses.FindByPageResponse;
import com.entities.RoleEntity;
import com.services.iml.ImlRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private ImlRoleService roleService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<RoleEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return roleService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<RoleEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return roleService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/readById/{id}")
    public ResponseEntity<Optional<RoleEntity>> readById(@PathVariable Integer id) throws Exception {

        try {
            roleService.readById(id);
            return ResponseEntity.ok().body(roleService.readById(id));
        }catch (Exception e){
            throw  new Exception("Bản ghi không tồn tại");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<RoleEntity> create( @Valid @RequestBody RoleEntity role) throws Exception {
        try {
            roleService.create(role);
            return ResponseEntity.ok().body(role);
        }catch (Exception e){
            throw new Exception("Create Failed");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<RoleEntity> update( @Valid @RequestBody RoleEntity role) throws Exception {
        try {
            roleService.update(role);
            return ResponseEntity.ok().body(role);
        }catch (Exception e){
            throw new Exception("Update Failed");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<RoleEntity> delete(@PathVariable Integer id) throws Exception {
        try {
            roleService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            throw new Exception("Delete Failed");
        }
    }
}
