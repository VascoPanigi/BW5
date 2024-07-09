package team6.BW5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team6.BW5.entities.Municipality;
import team6.BW5.exceptions.NotFoundException;
import team6.BW5.repositories.MunicipalityRepository;

import java.util.UUID;

@Service
public class MunicipalityService {
    @Autowired
    private MunicipalityRepository municipalityRepository;

    public Page<Municipality> getAllMunicipalities(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 300) pageSize = 300;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return municipalityRepository.findAll(pageable);
    }

    public Municipality findById(UUID municipalityId) {
        return this.municipalityRepository.findById(municipalityId).orElseThrow(() -> new NotFoundException(municipalityId));
    }
}
