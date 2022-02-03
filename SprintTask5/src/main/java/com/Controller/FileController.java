package com.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import java.util.Arrays;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Service.FileService;

@Controller
public class FileController {

    @Autowired
    FileService fileService;

//    @GetMapping("/admin")
//    public String index() {
//        return "admin";
//    }

//    @PostMapping("/uploadFile")
//    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
//
//        fileService.uploadFile(file);
//
//        redirectAttributes.addFlashAttribute("message",
//            "You successfully uploaded " + file.getOriginalFilename() + "!");
//
//        return "redirect:/";
//    }
    @PostMapping("/uploadFiles")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes) {

        Arrays.asList(files)
            .stream()
            .forEach(file -> fileService.uploadFile(file));

        redirectAttributes.addFlashAttribute("message",
            "You successfully uploaded all files!");

        return "redirect:/admin";
    }
    
    @RequestMapping(value = "/download", method = RequestMethod.GET) 
    public ResponseEntity<Object> downloadFile() throws IOException  {
       String filename = "C:\\Users\\mahes\\Desktop\\Task-CG-Asha\\Files\\Input File\\baby.jfif";
       File file = new File(filename);
       InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
       HttpHeaders headers = new HttpHeaders();
       
       headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
       headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
       headers.add("Pragma", "no-cache");
       headers.add("Expires", "0");
       
       ResponseEntity<Object> 
       responseEntity = ResponseEntity.ok().headers(headers).contentLength(
          file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
       
      
       
       return responseEntity;
    }
    
    @RequestMapping(value = "/generate", method = RequestMethod.GET)
    public String create_file(RedirectAttributes redirectAttributes) throws IOException  {
    	final String FILE_NAME = "C://Users/mahes/Desktop/Task-CG-Asha/Files/Output File/CreatedFile.csv";
        File newFile = new File(FILE_NAME);
        boolean success = newFile.createNewFile();
        //InputStreamResource resource = new InputStreamResource(new FileInputStream(newFile));
        if(success) System.out.println("File created");
        else System.out.println("already file exist please remove it if want to create again");
        
//        HttpHeaders headers = new HttpHeaders();
//        
//        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", newFile.getName()));
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//        
//        ResponseEntity<Object> 
//        responseEntity = ResponseEntity.ok().headers(headers).contentLength(
//           newFile.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
//        
//        return responseEntity;
        
        redirectAttributes.addFlashAttribute("message",
                "You have successfully Exported the file!");
        return "redirect:/admin";
    }
    
    
    
}