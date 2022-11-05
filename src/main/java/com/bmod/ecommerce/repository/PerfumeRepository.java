package com.bmod.ecommerce.repository;

import com.bmod.ecommerce.entity.PerfumeEntity;
import com.bmod.ecommerce.repository.projection.PerfumeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PerfumeRepository extends JpaRepository<PerfumeEntity, Long> {

    List<PerfumeProjection> findAllByOrderByIdAsc();
    
    @Query("SELECT perfume FROM PerfumeEntity perfume ORDER BY perfume.id ASC")
    Page<PerfumeProjection> findAllByOrderByIdAsc(Pageable pageable);

    List<PerfumeEntity> findByPerfumerOrderByPriceDesc(String perfumer);

    List<PerfumeEntity> findByPerfumeGenderOrderByPriceDesc(String perfumeGender);

    List<PerfumeEntity> findByIdIn(List<Long> perfumesIds);

    @Query("SELECT perfume FROM PerfumeEntity perfume WHERE perfume.id IN :perfumesIds")
    List<PerfumeProjection> getPerfumesByIds(List<Long> perfumesIds);

    @Query("SELECT perfume FROM PerfumeEntity perfume " +
            "WHERE (coalesce(:perfumers, null) IS NULL OR perfume.perfumer IN :perfumers) " +
            "AND (coalesce(:genders, null) IS NULL OR perfume.perfumeGender IN :genders) " +
            "AND (coalesce(:priceStart, null) IS NULL OR perfume.price BETWEEN :priceStart AND :priceEnd) " +
            "ORDER BY CASE WHEN :sortByPrice = true THEN perfume.price ELSE -perfume.price END ASC")
    Page<PerfumeProjection> findPerfumesByFilterParams(
            List<String> perfumers, 
            List<String> genders, 
            Integer priceStart, 
            Integer priceEnd, 
            boolean sortByPrice,
            Pageable pageable);

    @Query("SELECT perfume FROM PerfumeEntity perfume " +
            "WHERE UPPER(perfume.perfumer) LIKE UPPER(CONCAT('%',:text,'%')) " +
            "ORDER BY perfume.price DESC")
    Page<PerfumeProjection> findByPerfumer(String text, Pageable pageable);

    @Query("SELECT perfume FROM PerfumeEntity perfume " +
            "WHERE UPPER(perfume.perfumeTitle) LIKE UPPER(CONCAT('%',:text,'%')) " +
            "ORDER BY perfume.price DESC")
    Page<PerfumeProjection> findByPerfumeTitle(String text, Pageable pageable);

    @Query("SELECT perfume FROM PerfumeEntity perfume " +
            "WHERE UPPER(perfume.country) LIKE UPPER(CONCAT('%',:text,'%')) " +
            "ORDER BY perfume.price DESC")
    Page<PerfumeProjection> findByManufacturerCountry(String text, Pageable pageable);
}
