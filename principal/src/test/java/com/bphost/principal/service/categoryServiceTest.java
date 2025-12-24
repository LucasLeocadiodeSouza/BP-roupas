package com.bphost.principal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.bphost.principal.exception.CategoryException;
import com.bphost.principal.exception.ProductNotFoundException;
import com.bphost.principal.exception.SpecificationNotFoundException;
import com.bphost.principal.exception.SubCategoryException;
import com.bphost.principal.model.category;
import com.bphost.principal.model.product;
import com.bphost.principal.model.specification_size;
import com.bphost.principal.repository.categoryRepo;
import com.bphost.principal.repository.productRepo;
import com.bphost.principal.repository.specification_colorRepo;
import com.bphost.principal.repository.specification_sizeRepo;
import com.bphost.principal.repository.subcategoryRepo;

@ExtendWith(MockitoExtension.class)
public class categoryServiceTest {
    @Mock
    private categoryRepo categoryRepo;

    @Mock
    private subcategoryRepo subcategoryRepo;
    
    @Mock
    private specification_sizeRepo specsizerepo;

    @Mock
    private productRepo prodRepo;

    @Mock
    private specification_colorRepo speccolorrepo;

    @InjectMocks
    private categoryService categservice;

    @Test
    @DisplayName("O produto não foi informado ao tentar incluí-lo em uma subcategoria")
    void productMustBeProvided() {
        Integer productId = 1;

        Mockito.when(prodRepo.findProductById(productId)).thenReturn(null);

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, ()->{
            categservice.registerSubCategProd(productId, 1, 1);
        });

        assertEquals("Não encontrado o produto com o Código '" + productId + "'!", exception.getMessage());
    }

    @Test
    @DisplayName("A Categoria não foi informada ao tentar incluí-la em um produto")
    void categoryMustBeProvidedInRegister() {
        Integer categoryId = 1;

        Mockito.when(prodRepo.findProductById(1)).thenReturn(new product());
        Mockito.when(categoryRepo.findCategById(categoryId)).thenReturn(null);

        CategoryException exception = assertThrows(CategoryException.class, ()->{
            categservice.registerSubCategProd(1, categoryId, 1);
        });

        assertEquals("Não encontrado a Categoria informada com o Código'" + categoryId + "'!", exception.getMessage());
    }

    @Test
    @DisplayName("A Subcategoria não foi informada ao tentar incluí-la em um produto")
    void subcategoryMustBeProvidedInRegister() {
        Integer categoryId    = 1;
        Integer subcategoryId = 1;

        Mockito.when(prodRepo.findProductById(1)).thenReturn(new product());
        Mockito.when(categoryRepo.findCategById(categoryId)).thenReturn(new category());
        Mockito.when(subcategoryRepo.findSubCategById(categoryId, subcategoryId)).thenReturn(null);

        SubCategoryException exception = assertThrows(SubCategoryException.class, ()->{
            categservice.registerSubCategProd(1, categoryId, subcategoryId);
        });

        assertEquals("Não encontrado a Subcategoria informada com o Código'" + subcategoryId + "'!", exception.getMessage());
    }

    @Test
    @DisplayName("O produto nao foi informado ao tentar cadastrar um tamanho nas suas especificacoes")
    void productMustBeProvidedInSpecificationSize() {
        Integer productId = 1;

        Mockito.when(prodRepo.findProductById(productId)).thenReturn(null);

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, ()->{
            categservice.registerProductSize(productId, 1, 1, 1);
        });

        assertEquals("Não encontrado o produto com o Código '" + productId + "'!", exception.getMessage());
    }

    @Test
    @DisplayName("A especificacao de tamanho nao foi informada ao tentar vincula-la ao produto")
    void sizeMustBeProvidedInSpecificationSize() {
        Integer sizeId = 1;

        Mockito.when(prodRepo.findProductById(1)).thenReturn(new product());
        Mockito.when(specsizerepo.findSizeById(sizeId)).thenReturn(null);

        SpecificationNotFoundException exception = assertThrows(SpecificationNotFoundException.class, ()->{
            categservice.registerProductSize(1, sizeId, 1, 1);
        });

        assertEquals("Não encontrado o tamanho do produto informado!", exception.getMessage());
    }

    @Test
    @DisplayName("A especificacao da cor nao foi informada ao tentar vincula-la ao produto")
    void colorMustBeProvidedInSpecificationSize() {
        Integer colorId = 1;

        Mockito.when(prodRepo.findProductById(1)).thenReturn(new product());
        Mockito.when(specsizerepo.findSizeById(1)).thenReturn(new specification_size());
        Mockito.when(speccolorrepo.findColorById(colorId)).thenReturn(null);

        SpecificationNotFoundException exception = assertThrows(SpecificationNotFoundException.class, ()->{
            categservice.registerProductSize(1, 1, colorId, 1);
        });

        assertEquals("Não encontrado a cor produto informado!", exception.getMessage());
    }
}
