package team6.BW5.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import team6.BW5.entities.Municipality;
import team6.BW5.entities.Province;
import team6.BW5.entities.Role;
import team6.BW5.entities.User;
import team6.BW5.enums.UserRoles;
import team6.BW5.repositories.MunicipalityRepository;
import team6.BW5.repositories.ProvinceRepository;
import team6.BW5.repositories.RoleRepository;
import team6.BW5.repositories.UserRepository;
import team6.BW5.services.CSVService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

//@Component
public class DBInitializer {

    @Autowired
    private CSVService csvService;
    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MunicipalityRepository municipalityRepository;

    @Autowired
    private PasswordEncoder bCrypt;

    private static Map<String, String> getStringStringMap() {
        Map<String, String> provinceMap = new HashMap<>();
        provinceMap.put("Sud Sardegna", "Carbonia Iglesias");
        provinceMap.put("Ascoli Piceno", "Ascoli-Piceno");
        provinceMap.put("Pesaro e Urbino", "Pesaro-Urbino");
        provinceMap.put("Reggio Calabria", "Reggio-Calabria");
        provinceMap.put("Bolzano/Bozen", "Bolzano");
        provinceMap.put("Forlì-Cesena", "Forli-Cesena");
        provinceMap.put("La Spezia", "La-Spezia");
        provinceMap.put("Monza e della Brianza", "Monza-Brianza");
        provinceMap.put("Valle d'Aosta/Vallée d'Aoste", "Aosta");
        provinceMap.put("Verbano-Cusio-Ossola", "Verbania");
        provinceMap.put("Vibo Valentia", "Vibo-Valentia");
        provinceMap.put("Reggio nell'Emilia", "Reggio-Emilia");
        return provinceMap;
    }

//    @Bean
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

//    @Bean
    public void initializeMunicipalities() throws IOException {
        Path provincesPath = Paths.get("src/main/java/team6/BW5/data/comuni-italiani.csv");

        List<String[]> municipalitiesList = csvService.convertCSV(provincesPath);
        Map<String, String> provinceMap = getStringStringMap();


        List<Municipality> newMunicipalities = municipalitiesList.stream()
                .map(row -> {
                    Municipality municipality = new Municipality();
                    municipality.setName(row[2]);
                    String province = row[3];
                    if (provinceMap.containsKey(province)) {
                        province = provinceMap.get(province);
                    }
                    Province actualProvince = provinceRepository.findFirstByName(province);
                    municipality.setProvince(actualProvince);
                    return municipality;
                })
                .toList();

        newMunicipalities.forEach(municipalityRepository::save);
    }

    @Bean
    public void initializeUsers(){
        User newUser = new User( "aldo.baglio", "gabibbo@striscia.it",  bCrypt.encode("AldoBaglio"), "Aldo", "Baglio");

        Role newRole = new Role(UserRoles.USER);
        Role newRole2 = new Role(UserRoles.ADMIN);

        List<Role> roleList = new ArrayList<>();

        roleRepository.save(newRole);
        roleRepository.save(newRole2);

        roleList.add(newRole);
        roleList.add(newRole2);

        newUser.setRolesList(roleList);

        userRepository.save(newUser);
    }
}
