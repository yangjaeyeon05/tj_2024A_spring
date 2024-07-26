package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.service.FileService;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    // 1. 다운로드 요청
    @GetMapping("/file/download")
    public void fileDownLoad(String filename){
        System.out.println("FileController.fileDownLoad");
        System.out.println("filename = " + filename);
        fileService.fileDownLoad(filename);
    }   // fileDownLoad() end

}   // class end
