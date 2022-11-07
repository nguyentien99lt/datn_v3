package com.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import com.dto.OrderDTO;
import com.dto.OrderInterface;
import com.entities.OrderEntity;
import com.services.iml.ImlOrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private ImlOrderService orderService;

    @PostMapping("/find-by-page")
    public FindByPageResponse<OrderEntity> findByPage(@RequestBody FindByPageRequest finByPageRequest) {
        return orderService.findByPage(finByPageRequest);
    }
    @GetMapping("/find-by-page")
    public FindByPageResponse<OrderEntity> findByPage(@RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
                                                      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        return orderService.findByPageParam(pageNumber, pageSize);

    }

    @GetMapping("/{id}")
    public Optional<OrderEntity> readById(@PathVariable Integer id) {
        return orderService.readById(id);
    }

    @PostMapping("/create")
    public OrderEntity create(@RequestBody OrderEntity order) {
        return orderService.create(order);
    }

    @PutMapping("/update")
    public OrderEntity update(@RequestBody OrderEntity order) {
        return orderService.update(order);
    }

    @DeleteMapping("/{id}")
    public OrderEntity delete(@PathVariable Integer id) {
        return orderService.delete(id);
    }

    @PostMapping("/search")
    public FindByPageResponse<OrderInterface> viewUser(
            @RequestBody OrderDTO dto
            ,Pageable  pageable) {
        return	orderService.listAll(dto, pageable);
    }
}
