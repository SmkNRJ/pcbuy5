package com.pc_buy.pcbuy.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileUploadService {

    public String uploadFile(MultipartFile file) throws IOException {
        String path = "D:\\учеба\\diplom\\pcbuy\\src\\main\\resources\\static\\images\\";
        File dir = new File(path);
        List<String> files = new ArrayList<>();
        for (File file1 : dir.listFiles()){
            if (file1.isFile()) {
                files.add(file1.getName());
            }
        }
        path = check(files, path, file.getOriginalFilename());
        file.transferTo(new File(path));
        path = path.replace('\\',' ');
        String delimetr = " ";
        String[] allPath = path.split(delimetr);
        String result = allPath[allPath.length - 1];
        return result;
    }
    private String check (List<String>mass, String path, String fileName){
        File f = new File(path + fileName);
        Double rnd = Math.random();
        String s = Double.toString(rnd) + f.getName();
        File f2 = new File(path + fileName + s);
        String result = path + fileName;
        for (String name : mass){
            if (name.equals(fileName)){
           Boolean success = f.renameTo(f2);
           if (success){
              result = check(mass, path, fileName + s);
           }
            }
        }
        return result;
    }
}
