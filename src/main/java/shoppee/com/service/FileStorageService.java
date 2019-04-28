package shoppee.com.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import shoppee.com.entities.File;
import shoppee.com.entities.Product;
import shoppee.com.exception.FileStorageException;
import shoppee.com.exception.MyFileNotFoundException;
import shoppee.com.repository.FileRepository;

@Service
public class FileStorageService {

    @Autowired
    private FileRepository fileRepository;

    public File storeFile(MultipartFile file, Product product) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			File dbFile = new File(fileName, file.getContentType(), file.getBytes(), product);

			return fileRepository.save(dbFile);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
    }

    public File getFile(String fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}
