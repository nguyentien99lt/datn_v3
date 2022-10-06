package com.repositories;

import com.entities.ProductDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductDetailRepository extends JpaRepository<ProductDetailEntity, Integer> {


    @Query("SELECT pd FROM ProductDetailEntity pd WHERE pd.name LIKE %:name% and pd.status = :status")
    Page<ProductDetailEntity> findByName(@Param("name") String name, @Param("status")Integer status, Pageable pageable);

    @Query("SELECT pd FROM ProductDetailEntity pd WHERE pd.price = :price and pd.status = :status")
    Page<ProductDetailEntity> findByPrice(@Param("price") Float price, @Param("status")Integer status, Pageable pageable);

    @Query(" SELECT pd FROM ProductDetailEntity pd INNER JOIN  SizeEntity s ON pd.size.id = s.id " +
            "WHERE s.size = :size and pd.status = :status ")
    Page<ProductDetailEntity> findBySize(@Param("size") Integer size, @Param("status")Integer status, Pageable pageable);

    @Query(" SELECT pd FROM ProductDetailEntity pd INNER JOIN  ColorEntity c ON pd.color.id = c.id " +
            "WHERE c.color = :color and pd.status = :status")
    Page<ProductDetailEntity> findByColor(@Param("color") String color, @Param("status")Integer status, Pageable pageable);

    @Query(value = " SELECT pd FROM ProductDetailEntity pd LEFT JOIN ProductEntity p ON pd.product.id = p.id" +
            " INNER JOIN BrandEntity b ON b.id = p.brand.id WHERE b.name LIKE %:name% and pd.status = :status")
    Page<ProductDetailEntity> findByBrandName(@Param("name") String name, @Param("status")Integer status, Pageable pageable);
}
