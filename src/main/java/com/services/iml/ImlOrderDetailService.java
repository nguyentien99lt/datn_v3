package com.services.iml;

import com.clients.requests.FindByPageRequest;
import com.clients.responses.FindByPageResponse;
import com.entities.OrderDetailEntity;
import com.repositories.IOrderDetailRepository;
import com.services.IService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImlOrderDetailService implements IService<OrderDetailEntity> {

    @Autowired
    private IOrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetailEntity create(OrderDetailEntity orderDetailEntity) {
        return orderDetailRepository.save(orderDetailEntity);
    }

    @Override
    public OrderDetailEntity update(OrderDetailEntity orderDetailEntity) {
        OrderDetailEntity orderDetail = orderDetailRepository.findById(orderDetailEntity.getId()).get();
        if (orderDetail != null) {
            orderDetail.setOrder(orderDetailEntity.getOrder());
            orderDetail.setProductDetail(orderDetailEntity.getProductDetail());
            orderDetail.setPrice(orderDetailEntity.getPrice());
            orderDetail.setQuantity(orderDetailEntity.getQuantity());
            orderDetail.setStatus(orderDetailEntity.getStatus());
            orderDetail.setNote(orderDetailEntity.getNote());
            return orderDetailRepository.save(orderDetail);
        } else {
            return null;
        }
    }

    @Override
    public OrderDetailEntity delete(Integer id) {
        OrderDetailEntity orderDetail = orderDetailRepository.findById(id).get();
        if (orderDetail != null) {
            orderDetailRepository.deleteById(id);
            return orderDetail;
        } else {
            return null;
        }
    }

    @Override
    public Optional<OrderDetailEntity> readById(Integer id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public FindByPageResponse<OrderDetailEntity> findByPage(FindByPageRequest findByPageRequest) throws ServiceException {
        try {
            Integer pageNumber = findByPageRequest.getPageNumber();
            Integer pageSize = findByPageRequest.getPageSize();
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<OrderDetailEntity> pageCart = orderDetailRepository.findAll(page);
            return response(pageCart);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public FindByPageResponse<OrderDetailEntity> findByPageParam(Integer pageNumber, Integer pageSize) throws ServiceException {
        try {
            Pageable page = PageRequest.of(pageNumber, pageSize);
            Page<OrderDetailEntity> pageCart = orderDetailRepository.findAll(page);
            return response(pageCart);
        } catch (Exception e) {
            return null;
        }
    }
}
