package com.bphost.principal.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bphost.principal.exception.ProductNotFoundException;
import com.bphost.principal.exception.SpecificationNotFoundException;
import com.bphost.principal.exception.UserCartNotFoundException;
import com.bphost.principal.exception.UserException;
import com.bphost.principal.exception.UserNotFoundException;
import com.bphost.principal.infra.security.tokenService;
import com.bphost.principal.model.product;
import com.bphost.principal.model.specification_color;
import com.bphost.principal.model.specification_prod;
import com.bphost.principal.model.specification_size;
import com.bphost.principal.model.userCartDTO;
import com.bphost.principal.model.userDTO;
import com.bphost.principal.model.user_account;
import com.bphost.principal.model.user_address;
import com.bphost.principal.model.user_addressId;
import com.bphost.principal.model.user_cart;
import com.bphost.principal.model.user_cartId;
import com.bphost.principal.model.user_history;
import com.bphost.principal.model.user_historyId;
import com.bphost.principal.model.security.userRole;
import com.bphost.principal.repository.commentsProdRepo;
import com.bphost.principal.repository.productRepo;
import com.bphost.principal.repository.specification_colorRepo;
import com.bphost.principal.repository.specification_prodRepo;
import com.bphost.principal.repository.specification_sizeRepo;
import com.bphost.principal.repository.userCartDTORepo;
import com.bphost.principal.repository.userDTORepo;
import com.bphost.principal.repository.user_accountRepo;
import com.bphost.principal.repository.user_addressRepo;
import com.bphost.principal.repository.user_cartRepo;
import com.bphost.principal.repository.user_historyRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class userService {
    @Autowired
    private user_accountRepo userAccountRepo;

    @Autowired
    private user_addressRepo userAddressRepo;

    @Autowired
    private user_historyRepo userHistoryRepo;
    
    @Autowired
    private commentsProdRepo commentsprodrepo;

    @Autowired
    private user_cartRepo userCartRepo;

    @Autowired
    private productRepo prodRepo;

    @Autowired
    private specification_colorRepo speccolorRepo;

    @Autowired
    private specification_sizeRepo specsizeRepo;

    @Autowired
    private specification_prodRepo specprodRepo;

    @Autowired
    private tokenService tokenService;

    @Autowired
    private userCartDTORepo userCartDTOrepo;

    @Autowired
    private userDTORepo userDTORepo;


    public Integer getUserAccountId(String username) {
        user_account user = userAccountRepo.findIdByUsername(username);
        if(user != null) return user.getId();

        return 0;
    }

    public String getUserName(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if("authToken".equals(cookie.getName())){
                    String token    = cookie.getValue();
                    String username = tokenService.getExtractedUsernameFromToken(token);
                    return username;
                }
            }
        }
        return "";
    }

    @Transactional
    public void registerUserAccount( String username, String email, String telephone, String password){
        if(username == null || username.isBlank()) throw new UserException("Deve ser informado o nome do usuário!");
        if(username.length() < 7) throw new UserException("Nome do usuário deve conter mais de 7 Caracteres!");
        
        if(email == null || email.isBlank()) throw new UserException("Deve ser informado o email do usuário!");
        if(!email.contains("@") || !email.contains(".com")) throw new UserException("Deve ser informado o email do usuário!");

        if(telephone == null || telephone.isBlank()) throw new UserException("Deve ser informado um número de Telefone!");
        if(!isValidPhoneNumber(telephone)) throw new UserException("O número de Telefone não é valido!");
        
        if(password == null || password.isBlank()) throw new UserException("Deve ser informado uma senha!");
        if(password.length() < 11) throw new UserException("A senha deve conter mais de 11 caracteres!");

        if(userAccountRepo.findByUsername(username) != null) throw new UserException("Nome de usuário já está em uso! Por favor escolha outro.");

        String encryptedPasskey = new BCryptPasswordEncoder().encode(password);

        user_account account = new user_account();
        account.setUsername(username);
        account.setTelephone(telephone);
        account.setPassword(encryptedPasskey);
        account.setEmail(email);
        account.setRole(userRole.USER);
        account.setCreate_at(LocalDate.now());
        userAccountRepo.save(account);
    }

    @Transactional
    public void registerUserHistory(Integer useraccount_id, Integer product_id){
        user_account user = userAccountRepo.findAccountById(useraccount_id);
        product prod      = prodRepo.findProductById(product_id);

        if(user == null) throw new UserNotFoundException("Usuário não encontrado com o id '" + useraccount_id + "'");
        if(prod == null) throw new ProductNotFoundException("Produto não encontrado com o id '" + product_id + "'");

        user_history history = userHistoryRepo.findHistoryById(useraccount_id, product_id);
        if(history == null){
            history = new user_history();

            user_historyId histId = new user_historyId();
            histId.setUseraccount_id(useraccount_id);
            histId.setProduct_id(product_id);

            history.setId(histId);
            history.setUserAccount(user);
            history.setProduct(prod);
        }

        history.setUpdate_at(LocalDate.now());
        userHistoryRepo.save(history);
    }

    @Transactional
    public void registerUserCart(Integer useraccount_id, Integer product_id, Integer size_id, Integer color_id, Integer quantity){
        user_account user = userAccountRepo.findAccountById(useraccount_id);
        if(user == null) throw new UserNotFoundException("Usuário não encontrado com o id '" + useraccount_id + "'");

        product prod = prodRepo.findProductById(product_id);
        if(prod == null) throw new ProductNotFoundException("Produto não encontrado com o id '" + product_id + "'");

        specification_size size = specsizeRepo.findSizeById(size_id);
        if(size == null) throw new SpecificationNotFoundException("Tamanho não encontrado com o id '" + size_id + "'");

        specification_color color = speccolorRepo.findColorById(color_id);
        if(color == null) throw new SpecificationNotFoundException("Cor não encontrado com o id '" + color_id + "'");

        specification_prod specprod = specprodRepo.findSpecProdById(product_id, size_id, color_id);
        if(specprod == null) throw new SpecificationNotFoundException("Especificação do produto não encontrada!");

        Integer quantitySum = quantity; 

        user_cart cart = userCartRepo.findCardById(useraccount_id, product_id, size_id, color_id);
        if(cart == null){
            cart = new user_cart();

            user_cartId cartId = new user_cartId();
            cartId.setUseraccount_id(useraccount_id);
            cartId.setProduct_id(product_id);
            cartId.setSpec_size_id(size_id);
            cartId.setSpec_color_id(color_id);

            cart.setId(cartId);
            cart.setUserAccount(user);
            cart.setProduct(prod);
            cart.setSpec_size(size);
            cart.setSpec_color(color);
            cart.setCreate_at(LocalDate.now());

        }else {
            quantitySum += cart.getQuantity();
        }

        cart.setQuantity(quantitySum);

        userCartRepo.save(cart);
    }

    @Transactional
    public void removeProductFromCart(Integer useraccount_id, Integer product_id, Integer size_id, Integer color_id, Integer quantity){
        user_account user = userAccountRepo.findAccountById(useraccount_id);
        if(user == null) throw new UserNotFoundException("Usuário não encontrado com o id '" + useraccount_id + "'");

        product prod = prodRepo.findProductById(product_id);
        if(prod == null) throw new ProductNotFoundException("Produto não encontrado com o id '" + product_id + "'");

        specification_size size = specsizeRepo.findSizeById(size_id);
        if(size == null) throw new SpecificationNotFoundException("Tamanho não encontrado com o id '" + size_id + "'");

        specification_color color = speccolorRepo.findColorById(color_id);
        if(color == null) throw new SpecificationNotFoundException("Cor não encontrado com o id '" + color_id + "'");

        specification_prod specprod = specprodRepo.findSpecProdById(product_id, size_id, color_id);
        if(specprod == null) throw new SpecificationNotFoundException("Especificação do produto não encontrada!");

        user_cart cart = userCartRepo.findCardById(useraccount_id, product_id, size_id, color_id);
        if(cart == null) throw new UserCartNotFoundException("Produto não encontrado no carrinho!");

        userCartRepo.delete(cart);
    }

    public List<userCartDTO> getCartByUserAccount(Integer userId){
        List<userCartDTO> usercart = userCartDTOrepo.findAllActivesSubcategories(userId);
        return usercart;
    }

    public userDTO getUserInformation(Integer userId){
        user_account account = userAccountRepo.findAccountById(userId);
        if(account == null) throw new UserNotFoundException("Usuário não encontrado!");

        userDTO user = new userDTO();
        user.setUsername(account.getUsername());
        user.setEmail(account.getEmail());
        user.setTelephone(account.getTelephone());

        return user;
    }

    public user_address getUserAddress(Integer userId){
        user_address address = userAddressRepo.findAllAddressByUser(userId);
        if(address == null) return null;

        return address;
    }

    public List<userCartDTO> getAllUserHistory(Integer userId){
        List<userCartDTO> hist = userDTORepo.findAllHistortyByUser(userId);
        if(hist == null) return null;

        hist.forEach(prodHist -> {
            Integer total_comments = commentsprodrepo.countCommentsByProductId(prodHist.getProduct_id());

            Double avarage_rating;
            if(total_comments != 0){
                avarage_rating = ((commentsprodrepo.countCommentsWithRating1ByProductId(prodHist.getProduct_id()) * 1.0) +
                                 (commentsprodrepo.countCommentsWithRating2ByProductId(prodHist.getProduct_id()) * 2.0)  + 
                                 (commentsprodrepo.countCommentsWithRating3ByProductId(prodHist.getProduct_id()) * 3.0)  + 
                                 (commentsprodrepo.countCommentsWithRating4ByProductId(prodHist.getProduct_id()) * 4.0)  + 
                                 (commentsprodrepo.countCommentsWithRating5ByProductId(prodHist.getProduct_id()) * 5.0)) /
                                 total_comments;
            } else avarage_rating = 5.0;

            prodHist.setAvarage_rating(avarage_rating);
        });

        return hist;
    }

    public void registerUserAddress(Integer userId, String street, String number, String neighborhood, String cep, String city, String state, String country){
        user_account account = userAccountRepo.findAccountById(userId);
        if(account == null) throw new UserNotFoundException("Usuário não encontrado!");

        if(street == null || street.isBlank()) throw new UserException("Deve ser informado o nome da rua do endereço do usuário");
        if(neighborhood == null || neighborhood.isBlank()) throw new UserException("Deve ser informado o nome do bairro do usuário");
        if(number == null || number.isBlank()) throw new UserException("Deve ser informado o número da residencia do endereço do usuário");
        if(cep == null || cep.isBlank()) throw new UserException("Deve ser informado o CEP do endereço do usuário");
        if(city == null || city.isBlank()) throw new UserException("Deve ser informado a cidade do endereço do usuário");
        if(state == null || state.isBlank()) throw new UserException("Deve ser informado o estado do endereço do usuário");
        if(country == null || country.isBlank()) throw new UserException("Deve ser informado o pais do endereço do usuário");

        if(cep.length() != 8) throw new UserException("O CEP inserido é inválido!");
        
        Integer lastSeq = userAddressRepo.findLastSequenceAddressByUser(userId);
        if(lastSeq == null || lastSeq == 0) lastSeq = 1;

        user_addressId addressId = new user_addressId();
        addressId.setUseraccount_id(userId);
        addressId.setSeq(userId);

        user_address address = new user_address();
        address.setId(addressId);
        address.setStreet(street);
        address.setNeighborhood(neighborhood);
        address.setNumber(number);
        address.setCEP(cep);
        address.setCity(city);
        address.setState(state);
        address.setActive(true);

        userAddressRepo.save(address);
    }

    public void removeUserAddress(Integer userId, Integer sequence){
        user_account account = userAccountRepo.findAccountById(userId);
        if(account == null) throw new UserNotFoundException("Usuário não encontrado!");

        user_address address = userAddressRepo.findAddressByUser(userId, sequence);
        userAddressRepo.delete(address);
    }

    public static boolean isValidPhoneNumber(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) return false;

        String onlyNumbers = telefone.replaceAll("[^0-9]", "");

        return onlyNumbers.matches("\\d{10,11}");
    }
}
