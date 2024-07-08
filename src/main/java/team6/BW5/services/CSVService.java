package team6.BW5.services;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team6.BW5.entities.Province;
import team6.BW5.repositories.ProvinceRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

    @Service
    public class CSVService {

        @Autowired
        private ProvinceRepository provinceRepository;

    }





