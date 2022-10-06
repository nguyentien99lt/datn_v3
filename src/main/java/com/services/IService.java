package com.services;

import com.clients.requests.FindByPageRequest;
import com.clients.responses.FindByPageResponse;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IService<T> {

    public T create(T entity);
    public T update(T entity);
    public T delete(Integer id);
    public Optional<T> readById(Integer id);
    public FindByPageResponse<T> findByPage(FindByPageRequest findByPageRequest) throws ServiceException;;

    public FindByPageResponse<T> findByPageParam(Integer pageNumber, Integer pageSize) throws ServiceException;;
    public default FindByPageResponse<T> response(Page<T> pageCart) {
        List<T> productDetailEntityList = pageCart.getContent();
        FindByPageResponse<T> response = new FindByPageResponse();
        response.setPageResponse(productDetailEntityList);
        response.setPageSize(pageCart.getSize());
        response.setCurrentPage(pageCart.getNumber());
        response.setTotalPage(pageCart.getTotalPages());
        response.setTotalElement(pageCart.getTotalElements());
        return response;
    }
}
