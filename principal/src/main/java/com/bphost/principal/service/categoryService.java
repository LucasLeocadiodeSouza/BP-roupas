package com.bphost.principal.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bphost.principal.model.category;
import com.bphost.principal.model.categoryCardDTO;
import com.bphost.principal.model.product;
import com.bphost.principal.model.specification_color;
import com.bphost.principal.model.specification_prod;
import com.bphost.principal.model.specification_prodId;
import com.bphost.principal.model.specification_size;
import com.bphost.principal.model.subcat_product;
import com.bphost.principal.model.subcat_productId;
import com.bphost.principal.model.subcategory;
import com.bphost.principal.repository.categoryCardDTORepo;
import com.bphost.principal.repository.categoryRepo;
import com.bphost.principal.repository.productRepo;
import com.bphost.principal.repository.specification_colorRepo;
import com.bphost.principal.repository.specification_prodRepo;
import com.bphost.principal.repository.specification_sizeRepo;
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

    @Autowired
    private specification_colorRepo speccolorRepo;

    @Autowired
    private specification_sizeRepo specsizeRepo;

    @Autowired
    private specification_prodRepo specprodRepo;

    @Autowired
    private categoryCardDTORepo categoryDTORepo;


    public List<specification_color> getColorsOption(){
        List<specification_color> colors = speccolorRepo.findAll();
        return colors;
    }

    public List<specification_size> getSpecificationSize(Integer categ_id){
        List<specification_size> sizes = specsizeRepo.findAllSizeByCategoryID(categ_id);
        return sizes;
    }
    
    public List<category> findAllCategoriesActives(){
        List<category> categories = categRepo.findAllCategActives();
        if(categories == null || categories.isEmpty()) {
            return null;
        }

        return categories;
    }

    public categoryCardDTO findSubCategoryDTOByProduct(Integer product_id){
        categoryCardDTO subcategoryDTO = categoryDTORepo.findSubCategoryDTOByProduct(product_id);

        return subcategoryDTO;
    }


    public List<subcategory> findAllSubcategoriesByCategory(Integer categ){
        List<subcategory> subcategories = subcategRepo.findAllSubcategoriesByCategory(categ);
        if(subcategories == null || subcategories.isEmpty()) return null;

        return subcategories;
    }

    public categoryCardDTO getSubCategoryById(Integer categ, Integer subcateg){
        category category = categRepo.findCategById(categ);
        if(category == null) return null;

        categoryCardDTO categDTO = new categoryCardDTO();

        categDTO.setCategory_id(category.getId());
        categDTO.setCategory_name(category.getName());

        subcategory subcategory = subcategRepo.findSubCategById(categ, subcateg);
        if(subcategory == null) return categDTO;

        categDTO.setSubcategory_name(subcategory.getName());
        categDTO.setSubcategory_seq(subcategory.getId().getSeq());

        return categDTO;
    }

    public List<categoryCardDTO> findAllActivesSubcategories(Integer categ){
        List<categoryCardDTO> categoryDTO = categoryDTORepo.findAllActivesSubcategories(categ);
        if(categoryDTO == null) return null;

        return categoryDTO;
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

    @Transactional
    public void registerProductSize(Integer product_id, Integer size_id, Integer color_id, Integer storage){
        product product = prodRepo.findProductById(product_id);
        if(product == null) throw new RuntimeException("Não encontrado o produto com o Código '" + product_id + "'!");

        specification_size size = specsizeRepo.findSizeById(size_id);
        if(size == null)  throw new RuntimeException("Não encontrado o tamanho do produto informado!"); 

        specification_color color = speccolorRepo.findColorById(color_id);
        if(color == null)  throw new RuntimeException("Não encontrado a cor produto informado!"); 

        specification_prodId specprodId = new specification_prodId();
        specprodId.setColor_id(color_id);
        specprodId.setSize_id(size_id);
        specprodId.setProduct_id(product_id);

        specification_prod specprod = new specification_prod();
        specprod.setId(specprodId);
        specprod.setStorage(storage);

        specprodRepo.save(specprod);
    }
}
