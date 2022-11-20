package com.bmod.ecommerce.service.Impl;

import com.bmod.ecommerce.entity.PerfumeEntity;
import com.bmod.ecommerce.entity.ReviewEntity;
import com.bmod.ecommerce.enums.SearchPerfumeEnum;
import com.bmod.ecommerce.exception.ApiRequestException;
import com.bmod.ecommerce.repository.PerfumeRepository;
import com.bmod.ecommerce.repository.projection.PerfumeProjection;
import com.bmod.ecommerce.service.PerfumeService;
import graphql.schema.DataFetcher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerfumeServiceImpl implements PerfumeService {

    private final PerfumeRepository perfumeRepository;


    @Override
    public PerfumeEntity getPerfumeById(Long perfumeId) {
        return perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new ApiRequestException("Perfume not found.", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<ReviewEntity> getReviewsByPerfumeId(Long perfumeId) {
        PerfumeEntity perfume = getPerfumeById(perfumeId);
        return perfume.getReviews();
    }

    @Override
    public Page<PerfumeProjection> getAllPerfumes(Pageable pageable) {
        return perfumeRepository.findAllByOrderByIdAsc(pageable);
    }

    @Override
    public List<PerfumeProjection> getPerfumesByIds(List<Long> perfumesId) {
        return perfumeRepository.getPerfumesByIds(perfumesId);
    }

    @Override
    public Page<PerfumeProjection> findPerfumesByFilterParams(List<String> perfumers, List<String> genders, List<Integer> prices, 
                                                    boolean sortByPrice, Pageable pageable) {
        return perfumeRepository.findPerfumesByFilterParams(perfumers, genders, prices.get(0), prices.get(1), sortByPrice, pageable);
    }

    @Override
    public List<PerfumeEntity> findByPerfumer(String perfumer) {
        return perfumeRepository.findByPerfumerOrderByPriceDesc(perfumer);
    }

    @Override
    public List<PerfumeEntity> findByPerfumeGender(String perfumeGender) {
        return perfumeRepository.findByPerfumeGenderOrderByPriceDesc(perfumeGender);
    }

    @Override
    public Page<PerfumeProjection> findByInputText(SearchPerfumeEnum searchType, String text, Pageable pageable) {
        if (searchType.equals(SearchPerfumeEnum.BRAND)) {
            return perfumeRepository.findByPerfumer(text, pageable);
        } else if (searchType.equals(SearchPerfumeEnum.PERFUME_TITLE)) {
            return perfumeRepository.findByPerfumeTitle(text, pageable);
        } else {
            return perfumeRepository.findByManufacturerCountry(text, pageable);
        }
    }

    @Override
    @Transactional
    public PerfumeEntity savePerfume(PerfumeEntity perfume, MultipartFile multipartFile) {
        if (multipartFile == null) {
        } else {
            File file = new File(multipartFile.getOriginalFilename());
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(multipartFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String fileName = UUID.randomUUID().toString() + "." + multipartFile.getOriginalFilename();
            file.delete();
        }
        return perfumeRepository.save(perfume);
    }

    @Override
    @Transactional
    public String deletePerfume(Long perfumeId) {
        PerfumeEntity perfume = perfumeRepository.findById(perfumeId)
                .orElseThrow(() -> new ApiRequestException("Perfume not found.", HttpStatus.NOT_FOUND));
        perfumeRepository.delete(perfume);
        return "Perfume deleted successfully";
    }

    @Override
    public DataFetcher<PerfumeEntity> getPerfumeByQuery() {
        return dataFetchingEnvironment -> {
            Long perfumeId = Long.parseLong(dataFetchingEnvironment.getArgument("id"));
            return perfumeRepository.findById(perfumeId).get();
        };
    }

    @Override
    public DataFetcher<List<PerfumeProjection>> getAllPerfumesByQuery() {
        return dataFetchingEnvironment -> perfumeRepository.findAllByOrderByIdAsc();
    }

    @Override
    public DataFetcher<List<PerfumeEntity>> getAllPerfumesByIdsQuery() {
        return dataFetchingEnvironment -> {
            List<String> objects = dataFetchingEnvironment.getArgument("ids");
            List<Long> perfumesId = objects.stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            return perfumeRepository.findByIdIn(perfumesId);
        };
    }
}
