package com.bmod.ecommerce.service;

import com.bmod.ecommerce.entity.PerfumeEntity;
import com.bmod.ecommerce.entity.ReviewEntity;
import com.bmod.ecommerce.enums.SearchPerfumeEnum;
import com.bmod.ecommerce.repository.projection.PerfumeProjection;
import graphql.schema.DataFetcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PerfumeService {

    PerfumeEntity getPerfumeById(Long perfumeId);

    Page<PerfumeProjection> getAllPerfumes(Pageable pageable);

    List<PerfumeProjection> getPerfumesByIds(List<Long> perfumesId);

    Page<PerfumeProjection> findPerfumesByFilterParams(List<String> perfumers, List<String> genders, List<Integer> prices,
                                                       boolean sortByPrice, Pageable pageable);

    List<PerfumeEntity> findByPerfumer(String perfumer);

    List<PerfumeEntity> findByPerfumeGender(String perfumeGender);

    Page<PerfumeProjection> findByInputText(SearchPerfumeEnum searchType, String text, Pageable pageable);

    PerfumeEntity savePerfume(PerfumeEntity perfume, MultipartFile file);

    String deletePerfume(Long perfumeId);

    List<ReviewEntity> getReviewsByPerfumeId(Long perfumeId);

    DataFetcher<PerfumeEntity> getPerfumeByQuery();

    DataFetcher<List<PerfumeProjection>> getAllPerfumesByQuery();

    DataFetcher<List<PerfumeEntity>> getAllPerfumesByIdsQuery();
}
