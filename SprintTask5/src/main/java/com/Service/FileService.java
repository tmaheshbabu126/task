package com.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
//import java.nio.file.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import com.CustomException.FileStorageException;


@Service
public class FileService {

	@Value("${app.upload.dir:${user.home}}")
	public String uploadDir;

	public void uploadFile(MultipartFile file) {

		try {
			Path copyLocation = Paths

					.get("C:\\Users\\mahes\\Desktop\\Task-CG-Asha\\Files\\Input File" + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
			Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FileStorageException("Could not store file " + file.getOriginalFilename()
			+ ". Please try again!");
		}
	}
}