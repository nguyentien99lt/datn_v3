package com.controllers;

import com.clients.requests.FindByPageRequest;
import com.clients.responses.FindByPageResponse;
import com.entities.CartDetailEntity;
import com.services.iml.ImlCartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin/cart-detail")
public class CartDetailController {
    @Autowired
    private ImlCartDetailService cartDetailService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<CartDetailEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return cartDetailService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<CartDetailEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return cartDetailService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/{id}")
    public Optional<CartDetailEntity> readById(@PathVariable Integer id) {
        return cartDetailService.readById(id);
    }

    @PostMapping("/create")
    public CartDetailEntity create(@RequestBody CartDetailEntity cartDetail) {
        return cartDetailService.create(cartDetail);
    }

    @PutMapping("/update")
    public CartDetailEntity update(@RequestBody CartDetailEntity cartDetail) {
        return cartDetailService.update(cartDetail);
    }

    @DeleteMapping("/{id}")
    public CartDetailEntity delete(@PathVariable Integer id) {
        return cartDetailService.delete(id);
    }
}
