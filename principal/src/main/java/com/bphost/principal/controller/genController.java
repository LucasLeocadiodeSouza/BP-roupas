package com.bphost.principal.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bphost.principal.service.categoryService;

@RestController
@RequestMapping("/gen")
public class genController {
    @Autowired
    private categoryService categservice;

    // @GetMapping("/findAllCategoriesActive")
    // public ResponseEntity<?> findAllCategoriesActive(){
    //     try {
    //         return ResponseEntity.ok("OK");
    //     } catch (RuntimeException e) {
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     } catch (Exception e) {
    //         return ResponseEntity.internalServerError().body("Erro interno ao executar metodo: " + e.getMessage());
    //     }
    // }

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
