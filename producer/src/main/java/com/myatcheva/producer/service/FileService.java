package com.myatcheva.producer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class FileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);
    private static final String RANDOM_NUMBERS_FILE_EXTENSION = ".csv";
    private static final String RANDOM_NUMBERS_FILE_SUFFIX = "-random-numbers";
    private static final String RANDOM_NUMBERS_FOLDER = "output";
    private static final String DELIMITER = ",";

    public void saveRandomNumbersInFile(String fileId, List<Integer> numbers) {
        String fileName = new StringBuilder()
                .append(fileId)
                .append(RANDOM_NUMBERS_FILE_SUFFIX)
                .append(RANDOM_NUMBERS_FILE_EXTENSION)
                .toString();
        File folder = new File(RANDOM_NUMBERS_FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }
        try (FileWriter fileWriter = new FileWriter(new File(RANDOM_NUMBERS_FOLDER, fileName))) {
            String content = numbers.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(DELIMITER));
            fileWriter.write(content);
            LOGGER.info("Random numners saved in file: {}", fileName);
        } catch (IOException e) {
            LOGGER.error("Error while writing random numbers in file: {}", fileName);
            throw new RuntimeException(e);
        }
    }
}

