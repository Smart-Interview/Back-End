package com.ilyaselmabrouki.application_service.file;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FileService {
    private final FileRepository repository;

    public File uploadFile(MultipartFile data) throws IOException {
        String FOLDER_PATH = "C:\\Users\\Lenovo\\Documents\\Smart_Interview\\";
        File file = repository.save(
                File.builder()
                        .name(data.getOriginalFilename())
                        .type(data.getContentType())
                        .path(FOLDER_PATH +data.getOriginalFilename())
                        .build()
        );

        data.transferTo(new java.io.File(file.getPath()));
        return file;
    }

    public byte[] downloadFile(String fileName) throws IOException {
        Optional<File> data = repository.findByName(fileName);
        String filePath = data.get().getPath();
        return Files.readAllBytes(new java.io.File(filePath).toPath());
    }
}