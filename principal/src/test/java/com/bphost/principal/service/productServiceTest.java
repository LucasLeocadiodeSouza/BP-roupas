package com.bphost.principal.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import com.bphost.principal.exception.CommentException;
import com.bphost.principal.exception.ProductException;
import com.bphost.principal.exception.ProductNotFoundException;
import com.bphost.principal.model.product;
import com.bphost.principal.repository.productRepo;
import com.bphost.principal.repository.product_imgRepo;

@ExtendWith(MockitoExtension.class)
public class productServiceTest {
    
    @Mock
    private productRepo prodRepo;
    
    @Mock
    private product_imgRepo prodImgRepo;

    @InjectMocks
    private productService prodServ;

    private MultipartFile mockImage(String name){
        MultipartFile file = Mockito.mock(MultipartFile.class);
        Mockito.when(file.getOriginalFilename()).thenReturn(name + ".jpg");
        return file;
    }

    @Test
    @DisplayName("É necessário informar pelo menos 3 imagens para registrar o produto")
    void mustRegisterProductWithAtLeast3Image() {
        ProductException exception = assertThrows(ProductException.class, ()->{
            prodServ.adapterRegisterProduct(null, 
                                            "produto teste",
                                            "Descricao teste",
                                            BigDecimal.TEN,
                                            1,
                                            1,
                                            1, 
                                            new Integer[]{1, 2},
                                            10,
                                            new MultipartFile[]{});
        });

        assertEquals("Deve ser anexado pelo menos 3 imagens do produto!", exception.getMessage());
    }

    @Test
    @DisplayName("Limite de 10 anexos por produto")
    void limitOf10ImagesPerProduct() {
        MultipartFile[] images = new MultipartFile[]{
            Mockito.mock(MultipartFile.class),
            Mockito.mock(MultipartFile.class),
            Mockito.mock(MultipartFile.class),
            Mockito.mock(MultipartFile.class),
            Mockito.mock(MultipartFile.class),
            Mockito.mock(MultipartFile.class),
            Mockito.mock(MultipartFile.class),
            Mockito.mock(MultipartFile.class),
            Mockito.mock(MultipartFile.class),
            Mockito.mock(MultipartFile.class),
            Mockito.mock(MultipartFile.class)
        };

        ProductException exception = assertThrows(ProductException.class, ()->{
            prodServ.adapterRegisterProduct(null, 
                                            "produto teste",
                                            "Descricao teste",
                                            BigDecimal.TEN,
                                            1,
                                            1,
                                            1, 
                                            new Integer[]{1, 2},
                                            10,
                                            images);
        });

        assertEquals("Limite de anexos ao produto atingido! Limite: 10", exception.getMessage());
    }

    @Test
    @DisplayName("Nome do produto nao foi informado")
    void productNameMustBeProvided() {
        ProductException exception = assertThrows(ProductException.class, ()->{
            prodServ.adapterRegisterProduct(null,
                                            null,
                                            "Descricao teste",
                                            BigDecimal.TEN,
                                            1,
                                            1,
                                            1,
                                            new Integer[]{1, 2},
                                            1,
                                            new MultipartFile[]{
                                                Mockito.mock(MultipartFile.class),
                                                Mockito.mock(MultipartFile.class),
                                                Mockito.mock(MultipartFile.class)
                                            });
        });

        assertEquals("Deve ser informado o nome do produto!", exception.getMessage());
    }

    @Test
    @DisplayName("Descricao do produto não foi informada")
    void productDescriptionMustBeProvided() {
        ProductException exception = assertThrows(ProductException.class, ()->{
            prodServ.adapterRegisterProduct(null,
                                            "produto teste",
                                            null,
                                            BigDecimal.TEN,
                                            1,
                                            1,
                                            1,
                                            new Integer[]{1, 2},
                                            1,
                                            new MultipartFile[]{
                                                Mockito.mock(MultipartFile.class),
                                                Mockito.mock(MultipartFile.class),
                                                Mockito.mock(MultipartFile.class)
                                            });
        });

        assertEquals("Deve ser informado a descrição do produto!", exception.getMessage());
    }

    @Test
    @DisplayName("Preço do produto não foi informado")
    void priceMustBeProvided() {
        ProductException exception = assertThrows(ProductException.class, ()->{
            prodServ.adapterRegisterProduct(null,
                                            "produto teste",
                                            "Descricao teste",
                                            null,
                                            1,
                                            1,
                                            1,
                                            new Integer[]{1, 2},
                                            1,
                                            new MultipartFile[]{
                                                Mockito.mock(MultipartFile.class),
                                                Mockito.mock(MultipartFile.class),
                                                Mockito.mock(MultipartFile.class)
                                            });
        });

        assertEquals("Deve ser informado o preço do produto!", exception.getMessage());
    }

    @Test
    @DisplayName("O produto nao foi informado ao tentar enviar o comentario")
    void productMustBeProvidedWhenSubmittingComment() {
        Integer productid = 1;

        Mockito.when(prodRepo.findProductById(productid)).thenReturn(null);

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, ()->{
            prodServ.sendReviewComment(productid, "comentario teste", 5);
        });

        assertEquals("Não encontrado o produto com o Código '" + productid + "'!", exception.getMessage());
    }

    @Test
    @DisplayName("A descricao nao foi informada ao tentar enviar um comentario sobre o produto")
    void descriptionMustBeProvidedWhenSubmittingComment() {
        Integer productid = 1;

        Mockito.when(prodRepo.findProductById(productid)).thenReturn(new product());

        CommentException exception = assertThrows(CommentException.class, ()->{
            prodServ.sendReviewComment(productid, null, 5);
        });

        assertEquals("Deve ser informado a descrição do comentario!", exception.getMessage());
    }

    @Test
    @DisplayName("A nota nao foi informada ao tentar enviar um comentario sobre o produto")
    void ratingMustBeProvidedWhenSubmittingComment() {
        Integer productid = 1;

        Mockito.when(prodRepo.findProductById(productid)).thenReturn(new product());

        CommentException exception = assertThrows(CommentException.class, ()->{
            prodServ.sendReviewComment(productid, "comentario teste", null);
        });

        assertEquals("Deve ser informado uma avaliação válida para o produto!", exception.getMessage());
    }
}
