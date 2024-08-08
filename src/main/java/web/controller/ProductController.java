package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.model.dto.ProductDto;
import web.service.ProductService;

import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    // 1. 제품등록
    @PostMapping("/register")
    public boolean pRegister(ProductDto productDto){
        // @RequestParam 생략 가능 변수명이 다를경우는 @RequestParam("명시할이름")
        System.out.println("ProductController.pRegister");
        System.out.println("productDto = " + productDto);
        return productService.pRegister(productDto);
    }   // pRegister() end

    // 2. 모든 제품 출력
    @GetMapping("/find/all")
    public List<ProductDto> getProductFindAll(){
        return productService.getProductFindAll();
    }   // getProductFindAll() end

}   // class end
