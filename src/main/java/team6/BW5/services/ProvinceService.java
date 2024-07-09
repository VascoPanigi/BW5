package team6.BW5.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team6.BW5.entities.Province;
import team6.BW5.exceptions.NotFoundException;
import team6.BW5.repositories.ProvinceRepository;

import java.util.UUID;

@Service
public class ProvinceService {

    @Autowired
    private ProvinceRepository provinceRepository;

    public Page<Province> getAllProvinces(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 300) pageSize = 300;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return provinceRepository.findAll(pageable);
    }

    public Province findById(UUID provinceId) {
        return this.provinceRepository.findById(provinceId).orElseThrow(() -> new NotFoundException(provinceId));
    }
}
