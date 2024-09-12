package com.fstg.JobOfferManagement.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.StandardCopyOption;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

	 private final String UPLOAD_DIR = "jobOffersDescriptions"; // Directory to store files

	public String saveDescriptionToFile(MultipartFile pdfFile, Integer jobOfferId) throws IOException {
        // Ensure the upload directory exists
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();  // Create the directory if it doesn't exist
        }
        
        // Generate a unique file name using job offer ID and title, ensuring the file extension is .pdf
        String fileName = "jobOffer_" + jobOfferId + ".pdf";
        
        // Create the file path
        Path filePath = Paths.get(UPLOAD_DIR, fileName);
        
        // Save the file to the specified location
        try (InputStream inputStream = pdfFile.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        // Return the file path as a string
        return filePath.toString();  
    }


}
