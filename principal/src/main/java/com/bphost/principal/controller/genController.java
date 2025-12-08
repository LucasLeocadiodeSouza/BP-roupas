package com.bphost.principal.controller;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.bphost.principal.model.category;
import com.bphost.principal.model.categoryCardDTO;
import com.bphost.principal.model.productCardDTO;
import com.bphost.principal.model.specificationDTO;
import com.bphost.principal.model.specification_color;
import com.bphost.principal.model.specification_size;
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

    public static String uploadDirectory      = System.getProperty("user.dir") + "/uploadImage/products";
    public static String categoryDirectory    = System.getProperty("user.dir") + "/uploadImage/categories";
    public static String subcategoryDirectory = System.getProperty("user.dir") + "/uploadImage/categories/subcategories";
    public static String tempDirectory        = System.getProperty("user.dir") + "/uploadImage/temp";


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

    @GetMapping("/product/{filename}")
    public ResponseEntity<Resource> getProdImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadDirectory).resolve(filename);
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

    @GetMapping("/categoryImg/{filename}")
    public ResponseEntity<Resource> getCategoryImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(categoryDirectory).resolve(filename);
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

    @GetMapping("/subcategoryImg/{filename}")
    public ResponseEntity<Resource> getSubCategoryImage(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(subcategoryDirectory).resolve(filename);
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

    @PostMapping(value = "/registerProduct", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> registerProduct(@RequestParam(value = "product_id", required = false)      Integer product_id,
                                             @RequestParam(value = "name", required = false)            String name,
                                             @RequestParam(value = "description", required = false)     String description,
                                             @RequestParam(value = "price", required = false)           BigDecimal price,
                                             @RequestParam(value = "category_id", required = false)     Integer category_id,
                                             @RequestParam(value = "subcategory_seq", required = false) Integer subcategory_seq,
                                             @RequestParam(value = "color_id", required = false)        Integer color_id,
                                             @RequestParam(value = "size_id[]", required = false)       Integer[] size_id,
                                             @RequestParam(value = "storage", required = false)         Integer storage,
                                             @RequestParam(value = "images[]", required = false)        MultipartFile[] images,
                                             HttpServletRequest request) throws IOException{
        try {
            prodService.adapterRegisterProduct(product_id, name, description, price, category_id, subcategory_seq, color_id, size_id, storage, images);

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Product uploaded successfully");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno ao cadastrar Produto: " + e.getMessage());
        }
    }

    @GetMapping("/getSpecificationColor")
    public List<specification_color> getSpecificationColor(){
        return categservice.getColorsOption();
    }

    @GetMapping("/getSpecificationSize")
    public List<specification_size> getSpecificationSize(@RequestParam(value = "category_id", required = false) Integer category_id){
        return categservice.getSpecificationSize(category_id);
    }

    @GetMapping("/findAllCategoriesActive")
    public List<category> findAllCategoriesActive(){
        return categservice.findAllCategoriesActives();
    }

    @GetMapping("/getSubCategoryById")
    public categoryCardDTO getSubCategoryById(@RequestParam(value = "category_id", required = false) Integer category_id,
                                              @RequestParam(value = "subcategory_id", required = false) Integer subcategory_id){
        return categservice.getSubCategoryById(category_id, subcategory_id);
    }

    @GetMapping("/findAllSubcategoriesByCategory")
    public List<?> findAllSubcategoriesByCategory(@RequestParam(value = "category_id", required = false) Integer category_id){
        return categservice.findAllSubcategoriesByCategory(category_id);
    }

    @GetMapping("/getProductCardByCategoryId")
    public List<productCardDTO> getProductCardByCategoryId(@RequestParam(value = "category_id", required = false) Integer category_id,
                                                           @RequestParam(value = "subcategory_id", required = false) Integer subcategory_id,
                                                           @RequestParam(value = "page", required = false) Integer page){
        return prodService.getProductCardByCategoryId(category_id, subcategory_id, page);
    }

    @GetMapping("/getSimilarProductCard")
    public List<productCardDTO> getSimilarProductCard(@RequestParam(value = "category_id", required = false) Integer category_id,
                                                      @RequestParam(value = "subcategory_id", required = false) Integer subcategory_id){
        return prodService.getProductCardByCategoryId(category_id, subcategory_id);
    }

    @GetMapping("/getProductInformation")
    public List<productCardDTO> getProductInformation(@RequestParam(value = "id", required = true) Integer product_id){
        return prodService.getProductInformation(product_id);
    }

    @GetMapping("/getColorsSpecification")
    public List<specification_color> getColorsSpecification(){
        return prodService.getColorsSpecification();
    }

    @GetMapping("/getSizesByCategory")
    public List<specification_size> getSizesByCategory(@RequestParam(value = "category_id", required = true) Integer category_id){
        return prodService.getSizesByCategory(category_id);
    }

    @GetMapping("/getSpecificationColorByProduct")
    public List<specificationDTO> getSpecificationColorByProduct(@RequestParam(value = "id", required = true) Integer product_id,
                                                                 @RequestParam(value = "size_id", required = false) Integer size_id){
        return prodService.getSpecificationColorByProduct(product_id, size_id);
    }

    @GetMapping("/getBestSellingProducts")
    public List<productCardDTO> getBestSellingProducts(@RequestParam(value = "category_id", required = false) Integer category_id,
                                                       @RequestParam(value = "subcategory_id", required = false) Integer subcategory_id){
        return prodService.getBestSellingProducts(category_id, subcategory_id);
    }

    @GetMapping("/getNewDiscovery")
    public List<productCardDTO> getNewDiscovery(){
        return prodService.getNewDiscovery();
    }

    @GetMapping("/getBestRatedDeals")
    public List<productCardDTO> getBestRatedDeals(){
        return prodService.getBestRatedDeals();
    }

    @GetMapping("/getHouseRecommendations")
    public List<productCardDTO> getHouseRecommendations(){
        return prodService.getHouseRecommendations();
    }

    @GetMapping("/findAllActivesSubcategories")
    public List<categoryCardDTO> findAllActivesSubcategories(@RequestParam(value = "category_id", required = false) Integer category_id){
        return categservice.findAllActivesSubcategories(category_id);
    }
}
