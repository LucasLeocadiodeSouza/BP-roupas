package com.bphost.principal.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bphost.principal.model.category;
import com.bphost.principal.model.subcategory;
import com.bphost.principal.repository.categoryRepo;
import com.bphost.principal.repository.subcategoryRepo;

@Service
public class categoryService {
    @Autowired
    private categoryRepo categRepo;

    @Autowired
    private subcategoryRepo subcategRepo;

    public String[] getClothesSize(){
        String[] list = {"PP","P","M","G","GG","XGG"};
        return list;
    }

    public String[] getPantsSize(){
        String[] list = {"30","32","34","36","38","40","42","44","46","48"};
        return list;
    }

    public String[] getTennisSize(){
        String[] list = {"36","37","38","39","40","41","42","43","44","45"};
        return list;
    }

    public String[] getColorsOption(){
        String[] list = {"Preto","Branco","Vermelho","Cinza","Verde"};
        return list;
    }

    public String[] getEspecificationSize(Integer categ_id){
        switch (categ_id) {
            case 2:
            case 3:
            case 6: return getClothesSize();
            case 4: return getTennisSize();
            case 5: return getPantsSize();
            default: return null;
        }
    }
    
    public List<category> findAllCategoriesActives(){
        List<category> categories = categRepo.findAllCategActives();
        if(categories == null || categories.isEmpty()) {
            return null;
        }

        return categories;
    }

    public List<subcategory> findAllSubcategoriesByCategory(Integer categ){
        List<subcategory> subcategories = subcategRepo.findAllSubcategoriesByCategory(categ);
        if(subcategories == null || subcategories.isEmpty()) return null;

        return subcategories;
    }

}
