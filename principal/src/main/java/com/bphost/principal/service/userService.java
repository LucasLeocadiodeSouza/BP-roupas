package com.bphost.principal.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bphost.principal.exception.ProductNotFoundException;
import com.bphost.principal.exception.SpecificationNotFoundException;
import com.bphost.principal.exception.UserCartNotFoundException;
import com.bphost.principal.exception.UserException;
import com.bphost.principal.exception.UserListNotFoundException;
import com.bphost.principal.exception.UserNotFoundException;
import com.bphost.principal.infra.security.tokenService;
import com.bphost.principal.model.product;
import com.bphost.principal.model.specification_color;
import com.bphost.principal.model.specification_prod;
import com.bphost.principal.model.specification_size;
import com.bphost.principal.model.userCartDTO;
import com.bphost.principal.model.userDTO;
import com.bphost.principal.model.userList;
import com.bphost.principal.model.userListId;
import com.bphost.principal.model.userListProd;
import com.bphost.principal.model.userListProdId;
import com.bphost.principal.model.userPurchases;
import com.bphost.principal.model.userPurchasesId;
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
import com.bphost.principal.repository.userListProdRepo;
import com.bphost.principal.repository.userListRepo;
import com.bphost.principal.repository.userPurchasesRepo;
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
    private userPurchasesRepo userPurcRepo;

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

    @Autowired
    private userListRepo listRepo;

    @Autowired
    private userListProdRepo listProdRepo;


    public Integer getUserAccountId(String username) {
        user_account user = userAccountRepo.findAccountByUsername(username);
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

    // ########### USER ACCOUNT METHODS ###########

    public userDTO getUserInformation(Integer userId){
        user_account account = userAccountRepo.findAccountById(userId);
        if(account == null) throw new UserNotFoundException("Usuário não encontrado!");

        userDTO user = new userDTO();
        user.setUsername(account.getUsername());
        user.setEmail(account.getEmail());
        user.setTelephone(account.getTelephone());

        return user;
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
    public void setUserAccount(Integer userId, String username, String email, String telephone){
        user_account account = userAccountRepo.findAccountById(userId);
        if(account == null) throw new UserException("Usuário não encontrado.");

        if(username == null || username.isBlank()) throw new UserException("Deve ser informado o nome do usuário!");
        if(username.length() < 7) throw new UserException("Nome do usuário deve conter mais de 7 Caracteres!");
        
        if(email == null || email.isBlank()) throw new UserException("Deve ser informado o email do usuário!");
        if(!email.contains("@") || !email.contains(".com")) throw new UserException("Deve ser informado o email do usuário!");

        if(telephone == null || telephone.isBlank()) throw new UserException("Deve ser informado um número de Telefone!");
        if(!isValidPhoneNumber(telephone)) throw new UserException("O número de Telefone não é valido!");

        if(userAccountRepo.findAccountByUsername(username) != null && account.getId() == userAccountRepo.findAccountByUsername(username).getId()) throw new UserException("Nome de usuário já está em uso! Por favor escolha outro.");

        account.setUsername(username);
        account.setTelephone(telephone);
        account.setEmail(email);
        userAccountRepo.save(account);
    }

    // ########### USER CART METHODS ###########

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
        List<userCartDTO> usercart = userCartDTOrepo.findAllProductsCartByUser(userId);
        return usercart;
    }

    public List<String> getAllImagesproductInCart(Integer userId){
        List<userCartDTO> usercart = userCartDTOrepo.findAllProductsCartByUser(userId);
        if(usercart == null) return null;

        List<String> images = new ArrayList<>();
        for(userCartDTO cart : usercart){
            images.add("http://localhost:8080/api/product/" + cart.getImage());
        }

        return images;
    }

    // ########### USER HISTORY METHODS ###########

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

    // ########### USER ADDRESS METHODS ###########

    public List<user_address> getUserAddress(Integer userId){
        List<user_address> address = userAddressRepo.findAllAddressByUser(userId);
        if(address == null) return null;

        return address;
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
        if(lastSeq == null) lastSeq = 0;

        disableAllAddress(userId);

        user_addressId addressId = new user_addressId();
        addressId.setUseraccount_id(userId);
        addressId.setSeq(lastSeq + 1);

        user_address address = new user_address();
        address.setId(addressId);
        address.setStreet(street);
        address.setNeighborhood(neighborhood);
        address.setNumber(number);
        address.setCEP(cep);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        address.setActive(true);

        userAddressRepo.save(address);
    }

    private void disableAllAddress(Integer userId){
        List<user_address> addresses = userAddressRepo.findAllAddressByUser(userId);
        for(user_address address : addresses){
            address.setActive(false);
            userAddressRepo.save(address);
        }
    }

    public void setActiveAddress(Integer userId, Integer sequence){
        user_address addressOld = userAddressRepo.findAddressActiveByUser(userId);
        addressOld.setActive(false);
        userAddressRepo.save(addressOld);

        user_address addressNew = userAddressRepo.findAddressByUser(userId, sequence);
        addressNew.setActive(true);
        userAddressRepo.save(addressNew);
    }

    public void removeUserAddress(Integer userId, Integer sequence){
        user_account account = userAccountRepo.findAccountById(userId);
        if(account == null) throw new UserNotFoundException("Usuário não encontrado!");

        user_address address = userAddressRepo.findAddressByUser(userId, sequence);
        userAddressRepo.delete(address);
    }

    public user_address getActiveAddress(Integer userId){
        user_address activeAddress = userAddressRepo.findAddressActiveByUser(userId);
        return activeAddress;
    }

    // ########### USER PURCHASE METHODS ###########

    @Transactional
    private void registerUserPurchases(Integer useraccount_id, Integer product_id, Integer size_id, Integer color_id){
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
        if(cart == null) throw new UserCartNotFoundException("Produto não foi encontrado no carrinho do usuário!");

        Integer newSequence = userPurcRepo.findLastSequencePurchaseById(useraccount_id, product_id, size_id, color_id);
        newSequence = newSequence == null? 1 : newSequence + 1;

        userPurchasesId purchaseId = new userPurchasesId();
        purchaseId.setUseraccount_id(useraccount_id);
        purchaseId.setProduct_id(product_id);
        purchaseId.setSpec_size_id(size_id);
        purchaseId.setSpec_color_id(color_id);
        purchaseId.setSequence(newSequence);

        userPurchases newPurchase = new userPurchases();
        newPurchase.setId(purchaseId);
        newPurchase.setUserAccount(user);
        newPurchase.setProduct(prod);
        newPurchase.setSpec_size(size);
        newPurchase.setSpec_color(color);
        newPurchase.setQuantity(cart.getQuantity());
        newPurchase.setStatus(1); // 1 - awaiting confirmation | 2 - awaiting delivery | 3 - delivered
        newPurchase.setCreate_at(LocalDate.now());

        userPurcRepo.save(newPurchase);

        userCartRepo.delete(cart);
    }

    @Transactional
    public void registerUserCartPurchases(Integer useraccount_id){
        user_account user = userAccountRepo.findAccountById(useraccount_id);
        if(user == null) throw new UserNotFoundException("Usuário não encontrado com o id '" + useraccount_id + "'");

        List<user_cart> itensInCart = userCartRepo.findAllByUserAccount(useraccount_id);
        for(user_cart cart : itensInCart){
            registerUserPurchases(cart.getId().getUseraccount_id(), cart.getId().getProduct_id(), cart.getId().getSpec_size_id(), cart.getId().getSpec_color_id());
        }
    }

    public void setStatusForWaitingUserPurchases(Integer useraccount_id, Integer product_id, Integer size_id, Integer color_id, Integer sequence){
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

        userPurchases purchase = userPurcRepo.findPurchaseById(useraccount_id, product_id, size_id, color_id, sequence);
        purchase.setStatus(2);

        userPurcRepo.save(purchase);
    }

    public void setStatusForDeliveredUserPurchases(Integer useraccount_id, Integer product_id, Integer size_id, Integer color_id, Integer sequence){
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

        userPurchases purchase = userPurcRepo.findPurchaseById(useraccount_id, product_id, size_id, color_id, sequence);
        purchase.setStatus(3);
        purchase.setDelivered_at(LocalDate.now());

        userPurcRepo.save(purchase);
    }

    public List<userCartDTO> getPreparingPurchase(Integer userId){
        List<userCartDTO> purchase = userCartDTOrepo.findAllPurchaseByUserAndStatus(userId, 1);
        if(purchase == null) return null;

        return purchase;
    }
    
    public List<userCartDTO> getWaitingPurchase(Integer userId){
        List<userCartDTO> purchase = userCartDTOrepo.findAllPurchaseByUserAndStatus(userId, 2);
        if(purchase == null) return null;

        return purchase;
    }

    public List<userCartDTO> getDeliveredPurchase(Integer userId){
        List<userCartDTO> purchase = userCartDTOrepo.findAllPurchaseByUserAndStatus(userId, 3);
        if(purchase == null) return null;

        return purchase;
    }

    // ########### LIST METHODS ###########

    @Transactional
    public userList createUserList(Integer userId, String name){
        if(name == null || name.isBlank()) throw new UserException("É necessario informar o nome da lista para salva-lá");

        user_account user = userAccountRepo.findAccountById(userId);
        if(user == null) throw new UserNotFoundException("Usuário não encontrado com o id '" + userId + "'");

        Integer newSequence = listRepo.findLastSeqListById(userId);
        newSequence = newSequence == null? 1 : newSequence + 1;

        userListId listId = new userListId();
        listId.setSeq(newSequence);
        listId.setUseraccount_id(userId);

        userList list = new userList();
        list.setUserAccount(user);
        list.setName(name);
        list.setCreate_at(LocalDate.now());
        list.setId(listId);

        listRepo.save(list);

        return list;
    }

    @Transactional
    public void adapterCreateUserList(Integer userId, Integer prodId, String name){
        userList userlist = new userList();

        if(name == null || name.isBlank()){
            name = "Favoritos";
            userlist = listRepo.findUserListByName(userId, name);
        }else userlist = listRepo.findUserListByName(userId, name);
        
        if(userlist == null) userlist = createUserList(userId, name);

        createUserListProd(userlist.getId().getUseraccount_id(), userlist.getId().getSeq(), prodId);
    }

    @Transactional
    public void createUserListProd(Integer userId, Integer seqList, Integer productId){
        user_account user = userAccountRepo.findAccountById(userId);
        if(user == null) throw new UserNotFoundException("Usuário não encontrado com o id '" + userId + "'");

        product product = prodRepo.findProductById(productId);
        if(product == null) throw new ProductNotFoundException("Não encontrado o produto com o Código '" + productId + "'!");

        userListProd listProd = listProdRepo.findUserListProdById(userId, seqList, productId);
        if(listProd != null) return;

        userListProdId listProdId = new userListProdId();
        listProdId.setProduct_id(productId);
        listProdId.setSeqlist(seqList);
        listProdId.setUseraccount_id(userId);

        listProd = new userListProd();
        listProd.setId(listProdId);
        listProd.setProduct(product);
        listProd.setUserAccount(user);
        listProd.setCreate_at(LocalDate.now());

        listProdRepo.save(listProd);
    }

    public void removeUserListProd(Integer userId, Integer seqList, Integer productId){
        user_account user = userAccountRepo.findAccountById(userId);
        if(user == null) throw new UserNotFoundException("Usuário não encontrado com o id '" + userId + "'");

        product product = prodRepo.findProductById(productId);
        if(product == null) throw new ProductNotFoundException("Não encontrado o produto com o Código '" + productId + "'!");

        userListProd listProd = listProdRepo.findUserListProdById(userId, seqList, productId);
        if(listProd == null) throw new UserListNotFoundException("Não encontrado o produto na lista informada!");

        listProdRepo.delete(listProd);
    }

    public List<userCartDTO> getUserListById(Integer userId, Integer seq){
        List<userCartDTO> cart = userDTORepo.findListById(userId, seq);
        if(cart == null) return null;

        cart.forEach(prodItem -> {
            Integer total_comments = commentsprodrepo.countCommentsByProductId(prodItem.getProduct_id());

            Double avarage_rating;
            if(total_comments != 0){
                avarage_rating = ((commentsprodrepo.countCommentsWithRating1ByProductId(prodItem.getProduct_id()) * 1.0) +
                                 (commentsprodrepo.countCommentsWithRating2ByProductId(prodItem.getProduct_id()) * 2.0)  + 
                                 (commentsprodrepo.countCommentsWithRating3ByProductId(prodItem.getProduct_id()) * 3.0)  + 
                                 (commentsprodrepo.countCommentsWithRating4ByProductId(prodItem.getProduct_id()) * 4.0)  + 
                                 (commentsprodrepo.countCommentsWithRating5ByProductId(prodItem.getProduct_id()) * 5.0)) /
                                 total_comments;
            } else avarage_rating = 5.0;

            prodItem.setAvarage_rating(avarage_rating);
        });

        return cart;
    }

    public List<userCartDTO> getAllUserListByUser(Integer userId){
        List<userCartDTO> cart = userDTORepo.findAllListByUser(userId);
        if(cart == null) return null;

        return cart;
    }

    public List<userCartDTO> findSomeProductByList(Integer userId, Integer seq){
        List<userCartDTO> cart = userDTORepo.findSomeProductByList(userId, seq, 4);
        if(cart == null) return null;

        return cart;
    }

    // ########### USEFULL METHODS ###########

    public static boolean isValidPhoneNumber(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) return false;

        String onlyNumbers = telefone.replaceAll("[^0-9]", "");

        return onlyNumbers.matches("\\d{10,11}");
    }
}
