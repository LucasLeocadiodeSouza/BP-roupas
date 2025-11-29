package com.bphost.principal.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import com.bphost.principal.service.categoryService;
import com.bphost.principal.service.productService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class genController {

    @Autowired
    private categoryService categservice;

    @Autowired
    private productService prodService;

    public static String uploadDirectory = System.getProperty("user.dir") + "/uploadImage/products";
    public static String tempDirectory = System.getProperty("user.dir") + "/uploadImage/temp";


    @PostMapping(value = "/registerTempImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> registerTempImage(@RequestParam(value = "image", required = false) MultipartFile image,
                                                HttpServletRequest request) throws IOException{
        try {
            Path fileNameAndPath = Paths.get(tempDirectory, image.getOriginalFilename());
            Files.write(fileNameAndPath, image.getBytes());

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("filename", image.getOriginalFilename());
            response.put("message", "Image uploaded successfully");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno ao cadastrar Imagem do Produto: " + e.getMessage());
        }
    }

    @GetMapping("/temp/{filename}")
    public ResponseEntity<Resource> getTempImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(tempDirectory).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());
            
            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/registerImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> registerImage(@RequestParam(value = "product_id", required = false) Integer product_id,
                                           @RequestParam(value = "image", required = false) MultipartFile image,
                                            HttpServletRequest request) throws IOException{
        try {
            Path fileNameAndPath = Paths.get(uploadDirectory, image.getOriginalFilename());
            Files.write(fileNameAndPath, image.getBytes());

            prodService.registerImage(image.getOriginalFilename(), product_id);

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Image uploaded successfully");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno ao cadastrar Imagem do Produto: " + e.getMessage());
        }
    }

    @PostMapping(value = "/registerProduct")
    public ResponseEntity<?> registerProduct(@RequestParam(value = "product_id", required = false) Integer product_id,
                                             @RequestParam(value = "name", required = false) String name,
                                             @RequestParam(value = "description", required = false) String description,
                                             @RequestParam(value = "price", required = false) BigDecimal price,
                                             @RequestParam(value = "category_id", required = false) Integer category_id,
                                             @RequestParam(value = "subcategory_seq", required = false) Integer subcategory_seq,
                                             HttpServletRequest request) throws IOException{
        try {
            prodService.registerProduct(product_id, uploadDirectory, tempDirectory, null);

            categservice.registerSubCategProd(product_id, category_id, subcategory_seq);

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Product uploaded successfully");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno ao cadastrar Imagem do Produto: " + e.getMessage());
        }
    }

    @GetMapping("/getEspecificationColor")
    public String[] getEspecificationColor(){
        return categservice.getColorsOption();
    }

    @GetMapping("/getEspecificationSize")
    public String[] getEspecificationSize(@RequestParam(value = "categ_id", required = false) Integer categ_id){
        return categservice.getEspecificationSize(categ_id);
    }

    @GetMapping("/findAllCategoriesActive")
    public List<?> findAllCategoriesActive(){
        return categservice.findAllCategoriesActives();
    }

    @GetMapping("/findAllSubcategoriesByCategory")
    public List<?> findAllSubcategoriesByCategory(@RequestParam(value = "categ_id", required = false) Integer categ_id){
        return categservice.findAllSubcategoriesByCategory(categ_id);
    }
}
