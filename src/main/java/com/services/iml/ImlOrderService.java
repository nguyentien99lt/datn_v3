package com.services.iml;

import com.clients.requests.FindByPageRequest;
import com.clients.responses.FindByPageResponse;
import com.entities.OrderEntity;
import com.repositories.IOrderRepository;
import com.services.IService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImlOrderService implements IService<OrderEntity> {


    @Autowired
    private IOrderRepository orderRepository;

    @Override
    public OrderEntity create(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderEntity update(OrderEntity orderEntity) {
        OrderEntity order = orderRepository.findById(orderEntity.getId()).get();
        if (order != null) {
            order.setOrderAddress(orderEntity.getOrderAddress());
            order.setOrderDate(orderEntity.getOrderDate());
            order.setReceivedDate(orderEntity.getReceivedDate());
            order.setCart(orderEntity.getCart());
            order.setPhone(orderEntity.getPhone());
            order.setUser(orderEntity.getUser());
            order.setStatus(orderEntity.getStatus());
            order.setNote(orderEntity.getNote());
            return orderRepository.save(order);
        } else {
            return null;
        }
    }

    @Override
    public OrderEntity delete(Integer id) {
        OrderEntity color = orderRepository.findById(id).get();
        if (color != null) {
            orderRepository.deleteById(id);
            return color;
        } else {
            return null;
        }
    }

    @Override
    public Optional<OrderEntity> readById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public FindByPageResponse<OrderEntity> findByPage(FindByPageRequest findByPageRequest) throws ServiceException {
        try {
            Integer pageNumber = findByPageRequest.getPageNumber();
            Integer pageSize = findByPageRequest.getPageSize();
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<OrderEntity> pageCart = orderRepository.findAll(page);
            return response(pageCart);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public FindByPageResponse<OrderEntity> findByPageParam(Integer pageNumber, Integer pageSize) throws ServiceException {
        try {
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<OrderEntity> pageCart = orderRepository.findAll(page);
            return response(pageCart);
        } catch (Exception e) {
            return null;
        }
    }
}
