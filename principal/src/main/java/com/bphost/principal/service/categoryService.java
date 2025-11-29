package com.bphost.principal.service;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bphost.principal.model.category;
import com.bphost.principal.model.product;
import com.bphost.principal.model.subcat_product;
import com.bphost.principal.model.subcat_productId;
import com.bphost.principal.model.subcategory;
import com.bphost.principal.repository.categoryRepo;
import com.bphost.principal.repository.productRepo;
import com.bphost.principal.repository.subcat_productRepo;
import com.bphost.principal.repository.subcategoryRepo;

import jakarta.transaction.Transactional;

@Service
public class categoryService {
    @Autowired
    private categoryRepo categRepo;

    @Autowired
    private subcategoryRepo subcategRepo;

    @Autowired
    private subcat_productRepo subcatprodRepo;

    @Autowired
    private productRepo prodRepo;

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


    @Transactional
    public void registerSubCategProd(Integer product_id, Integer categ_id, Integer subcateg_seq){
        product product = prodRepo.findProductById(product_id);
        if(product == null) throw new RuntimeException("Não encontrado o produto com o Código '" + product_id + "'!");

        category categ = categRepo.findCategById(categ_id);
        if(categ == null) throw new RuntimeException("Não encontrado a Categoria informada com o Código'" + categ_id + "'!");

        subcategory subcateg = subcategRepo.findSubCategById(categ_id, subcateg_seq);
        if(subcateg == null) throw new RuntimeException("Não encontrado a Subcategoria informada com o Código'" + subcateg + "'!");

        subcat_product subcategprod = subcatprodRepo.findSubcategProdById(product_id, categ_id, subcateg_seq);
        if(subcategprod == null) subcategprod = new subcat_product();
        else return;

        subcat_productId subcategprodId = new subcat_productId();
        subcategprodId.setProduct_id(product_id);
        subcategprodId.setCategory_id(categ_id);
        subcategprodId.setSubcategory_seq(subcateg_seq);

        subcategprod.setId(subcategprodId);

        subcatprodRepo.save(subcategprod);
    }
}
