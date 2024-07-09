package team6.BW5.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import team6.BW5.entities.Municipality;
import team6.BW5.entities.Province;
import team6.BW5.repositories.MunicipalityRepository;
import team6.BW5.repositories.ProvinceRepository;
import team6.BW5.services.CSVService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class DBInitializer {

    @Autowired
    private CSVService csvService;
    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private MunicipalityRepository municipalityRepository;

    @Bean
    public void initializeProvinces() throws IOException {
        Path provincesPath = Paths.get("src/main/java/team6/BW5/data/province-italiane.csv");

        List<String[]> provincesList = csvService.convertCSV(provincesPath);

        List<Province> newProvinces = provincesList.stream()
                .map(row -> {
                    Province province = new Province();
                    province.setNotation(row[0]);
                    province.setName(row[1]);
                    province.setRegion(row[2]);
                    return province;
                })
                .toList();

        newProvinces.forEach(provinceRepository::save);

    }

    @Bean
    public void initializeMunicipalities() throws IOException {
        Path provincesPath = Paths.get("src/main/java/team6/BW5/data/comuni-italiani.csv");

        List<String[]> municipalitiesList = csvService.convertCSV(provincesPath);

        List<Municipality> newMunicipalities = municipalitiesList.stream()
                .map(row -> {
                    Municipality municipality = new Municipality();
                    municipality.setName(row[2]);
                    municipality.setProvince(provinceRepository.findFirstByName(row[3]));
                    return municipality;
                })
                .toList();

        newMunicipalities.forEach(municipalityRepository::save);


    }
}
