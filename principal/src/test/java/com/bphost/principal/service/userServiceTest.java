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
import com.bphost.principal.exception.ProductNotFoundException;
import com.bphost.principal.exception.SpecificationNotFoundException;
import com.bphost.principal.exception.UserNotFoundException;
import com.bphost.principal.model.product;
import com.bphost.principal.model.specification_color;
import com.bphost.principal.model.specification_prod;
import com.bphost.principal.model.specification_size;
import com.bphost.principal.model.user_account;
import com.bphost.principal.repository.categoryRepo;
import com.bphost.principal.repository.productRepo;
import com.bphost.principal.repository.specification_colorRepo;
import com.bphost.principal.repository.specification_prodRepo;
import com.bphost.principal.repository.specification_sizeRepo;
import com.bphost.principal.repository.subcategoryRepo;
import com.bphost.principal.repository.user_accountRepo;

@ExtendWith(MockitoExtension.class)
public class userServiceTest {
    
    @Mock
    private productRepo prodRepo;

    @Mock
    private categoryRepo categoryRepo;

    @Mock
    private subcategoryRepo subcategoryRepo;
    
    @Mock
    private specification_sizeRepo specsizerepo;

    @Mock
    private user_accountRepo userRepo;

    @Mock
    private specification_colorRepo speccolorrepo;

    @Mock
    private specification_prodRepo specprodrepo;

    @InjectMocks
    private userService userServ;

    @Test
    @DisplayName("O usuário deve ser identificado para registrar o carrinho.")
    void userMustBeIdentificatedForRegisterCart() {
        Integer userid = 1;

        Mockito.when(userRepo.findAccountById(userid)).thenReturn(null);

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, ()->{
            userServ.registerUserCart(userid, 1, 1, 1, 1);
        });

        assertEquals("Usuário não encontrado com o id '" + userid + "'", exception.getMessage());
    }

    @Test
    @DisplayName("O produto nao foi informado corretamente para inclui-lo no carrinho")
    void productMustBeProvidedForRegisterCart() {
        Integer productid = 1;

        Mockito.when(userRepo.findAccountById(1)).thenReturn(new user_account());
        Mockito.when(prodRepo.findProductById(productid)).thenReturn(null);

        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, ()->{
            userServ.registerUserCart(1, productid, 1, 1, 1);
        });

        assertEquals("Produto não encontrado com o id '" + productid + "'", exception.getMessage());
    }

    @Test
    @DisplayName("O tamanho do produto nao foi informado corretamente para inclui-lo no carrinho")
    void sizeSpecMustBeProvidedForRegisterCart() {
        Integer sizeid = 1;

        Mockito.when(userRepo.findAccountById(1)).thenReturn(new user_account());
        Mockito.when(prodRepo.findProductById(1)).thenReturn(new product());
        Mockito.when(specsizerepo.findSizeById(sizeid)).thenReturn(null);

        SpecificationNotFoundException exception = assertThrows(SpecificationNotFoundException.class, ()->{
            userServ.registerUserCart(1, 1, sizeid, 1, 1);
        });

        assertEquals("Tamanho não encontrado com o id '" + sizeid + "'", exception.getMessage());
    }

    @Test
    @DisplayName("A coloração do produto nao foi informada corretamente para inclui-lo no carrinho")
    void colorSpecMustBeProvidedForRegisterCart() {
        Integer colorid = 1;

        Mockito.when(userRepo.findAccountById(1)).thenReturn(new user_account());
        Mockito.when(prodRepo.findProductById(1)).thenReturn(new product());
        Mockito.when(specsizerepo.findSizeById(1)).thenReturn(new specification_size());
        Mockito.when(speccolorrepo.findColorById(colorid)).thenReturn(null);

        SpecificationNotFoundException exception = assertThrows(SpecificationNotFoundException.class, ()->{
            userServ.registerUserCart(1, 1, 1, colorid, 1);
        });

        assertEquals("Cor não encontrado com o id '" + colorid + "'", exception.getMessage());
    }

    @Test
    @DisplayName("Especificação do produto não encontrada ao tentar inclui-lo no carrinho do usuário")
    void prodSpecNotFoundInTheRegisterUserCart() {
        Mockito.when(userRepo.findAccountById(1)).thenReturn(new user_account());
        Mockito.when(prodRepo.findProductById(1)).thenReturn(new product());
        Mockito.when(specsizerepo.findSizeById(1)).thenReturn(new specification_size());
        Mockito.when(speccolorrepo.findColorById(1)).thenReturn(new specification_color());
        Mockito.when(specprodrepo.findSpecProdById(1, 1, 1)).thenReturn(null);

        SpecificationNotFoundException exception = assertThrows(SpecificationNotFoundException.class, ()->{
            userServ.registerUserCart(1, 1, 1, 1, 1);
        });

        assertEquals("Especificação do produto não encontrada!", exception.getMessage());
    }
}
