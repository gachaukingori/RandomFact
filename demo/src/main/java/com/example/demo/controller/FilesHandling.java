package com.example.demo.controller;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@ControllerAdvice
public class FilesHandling {

    @RequestMapping(value = "/uploadReceipt", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadReceipts(@RequestParam("file") MultipartFile file) throws IOException {
        File convertFile = new File("/var/tmp/"+file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        System.out.println("File is uploaded successfully");

        return "File is uploaded successfully";
    }

    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public ResponseEntity<Object> download() throws FileNotFoundException {
        String filename = "/home/victor/Documents/spring_boot_tutorial.pdf";
        File file = new File(filename);
        InputStreamResource inputStreamResource  = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment;filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
                headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> responseEntity =
                ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(inputStreamResource);
        return responseEntity;
    }
}
