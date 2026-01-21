package com.bphost.principal.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bphost.principal.model.userCartDTO;
import com.bphost.principal.model.userDTO;
import com.bphost.principal.model.user_account;
import com.bphost.principal.model.user_address;
import com.bphost.principal.service.userService;

@RestController
@RequestMapping("/account")
public class accountController {
    @Autowired
    private userService user;

    // ############### Registers ###############

    @PostMapping(value = "/registerUserAccount")
    public ResponseEntity<?> registerUserAccount(@RequestBody user_account userAccount) throws IOException{
        user.registerUserAccount(userAccount.getUsername(), userAccount.getEmail(), userAccount.getTelephone(), userAccount.getPassword());

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "User registed successfully"
        ));
    }

    @PostMapping(value = "/setUserAccount")
    public ResponseEntity<?> setUserAccount(@RequestBody user_account userAccount, @AuthenticationPrincipal UserDetails userDetails) throws IOException{
        user.setUserAccount(user.getUserAccountId(userDetails.getUsername()), userAccount.getUsername(), userAccount.getEmail(), userAccount.getTelephone());

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "User updated successfully"
        ));
    }

    @PostMapping(value = "/registerUserHistory")
    public ResponseEntity<?> registerUserHistory(@RequestBody userCartDTO cart,
                                                 @AuthenticationPrincipal UserDetails userAccount) throws IOException{
        user.registerUserHistory(user.getUserAccountId(userAccount.getUsername()), cart.getProduct_id());

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "History updated successfully"
        ));
    }

    
    @PostMapping(value = "/registerProductFromCart")
    public ResponseEntity<?> registerProductFromCart(@RequestBody userCartDTO cart,
                                                     @AuthenticationPrincipal UserDetails userAccount) throws IOException{
        user.registerUserCart(user.getUserAccountId(userAccount.getUsername()), cart.getProduct_id(), cart.getSize_id(), cart.getColor_id(), cart.getQuantity());

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Cart updated successfully"
        ));
    }

    @PostMapping(value = "/removeProductFromCart")
    public ResponseEntity<?> removeProductFromCart(@RequestBody userCartDTO cart,
                                                   @AuthenticationPrincipal UserDetails userAccount) throws IOException{
        user.removeProductFromCart(user.getUserAccountId(userAccount.getUsername()), cart.getProduct_id(), cart.getSize_id(), cart.getColor_id(), cart.getQuantity());

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Cart updated successfully"
        ));
    }

    @GetMapping("/getCartByUserAccount")
    public List<userCartDTO> getCartByUserAccount(@AuthenticationPrincipal UserDetails userAccount){
        return user.getCartByUserAccount(user.getUserAccountId(userAccount.getUsername()));
    }

    @PostMapping("/getUserInformation")
    public userDTO getUserInformation(@AuthenticationPrincipal UserDetails userAccount){
        return user.getUserInformation(user.getUserAccountId(userAccount.getUsername()));
    }

    @PostMapping("/getUserAddress")
    public List<user_address> getUserAddress(@AuthenticationPrincipal UserDetails userAccount){
        return user.getUserAddress(user.getUserAccountId(userAccount.getUsername()));
    }

    @PostMapping("/getActiveAddress")
    public user_address getActiveAddress(@AuthenticationPrincipal UserDetails userAccount){
        return user.getActiveAddress(user.getUserAccountId(userAccount.getUsername()));
    }

    @PostMapping("/setActiveAddress")
    public ResponseEntity<?> setActiveAddress(@RequestParam(value = "sequence", required = true) Integer sequence, @AuthenticationPrincipal UserDetails userAccount){
        user.setActiveAddress(user.getUserAccountId(userAccount.getUsername()), sequence);

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Address updated successfully"
        ));
    }

    @PostMapping("/getAllUserHistory")
    public List<userCartDTO> getAllUserHistory(@AuthenticationPrincipal UserDetails userAccount){
        return user.getAllUserHistory(user.getUserAccountId(userAccount.getUsername()));
    }

    @PostMapping("/registerUserAddress")
    public ResponseEntity<?> registerUserAddress(@RequestBody userDTO userDTO,
                                                 @AuthenticationPrincipal UserDetails userAccount){
        user.registerUserAddress(user.getUserAccountId(userAccount.getUsername()), userDTO.getStreet(), userDTO.getNumber(), userDTO.getNeighborhood(), userDTO.getCep(), userDTO.getCity(), userDTO.getState(), userDTO.getCountry());

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Address register successfully"
        ));
    }

    @PostMapping("/registerUserCartPurchases")
    public ResponseEntity<?> registerUserCartPurchases(@AuthenticationPrincipal UserDetails userAccount){
        user.registerUserCartPurchases(user.getUserAccountId(userAccount.getUsername()));

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Purchases register successfully"
        ));
    }

    @GetMapping("/getDeliveredPurchase")
    public List<userCartDTO> getDeliveredPurchase(@AuthenticationPrincipal UserDetails userAccount){
        return user.getDeliveredPurchase(user.getUserAccountId(userAccount.getUsername()));
    }

    @GetMapping("/getWaitingPurchase")
    public List<userCartDTO> getWaitingPurchase(@AuthenticationPrincipal UserDetails userAccount){
        return user.getWaitingPurchase(user.getUserAccountId(userAccount.getUsername()));
    }

    @GetMapping("/getPreparingPurchase")
    public List<userCartDTO> getPreparingPurchase(@AuthenticationPrincipal UserDetails userAccount){
        return user.getPreparingPurchase(user.getUserAccountId(userAccount.getUsername()));
    }

    @GetMapping("/getAllImagesproductInCart")
    public List<String> getAllImagesproductInCart(@AuthenticationPrincipal UserDetails userAccount){
        return user.getAllImagesproductInCart(user.getUserAccountId(userAccount.getUsername()));
    }

    @GetMapping("/getAllUserListByUser")
    public List<userCartDTO> getAllUserListByUser(@AuthenticationPrincipal UserDetails userAccount){
        return user.getAllUserListByUser(user.getUserAccountId(userAccount.getUsername()));
    }

    @GetMapping("/getUserListById")
    public List<userCartDTO> getUserListById(@RequestParam(value =  "seqlist", required = true) Integer seqlist,
                                             @AuthenticationPrincipal UserDetails userAccount){
        return user.getUserListById(user.getUserAccountId(userAccount.getUsername()), seqlist);
    }

    @GetMapping("/findSomeProductByList")
    public List<userCartDTO> findSomeProductByList(@RequestParam(value = "seqlist", required = true) Integer seqlist,
                                                   @AuthenticationPrincipal UserDetails userAccount){
        return user.findSomeProductByList(user.getUserAccountId(userAccount.getUsername()), seqlist);
    }

    @PostMapping("/adapterCreateUserList")
    public ResponseEntity<?> adapterCreateUserList(@RequestParam(value = "listName", required = false) String listName, 
                                                   @RequestParam(value = "prodId", required = false) Integer prodId, 
                                                   @AuthenticationPrincipal UserDetails userAccount){
        user.adapterCreateUserList(user.getUserAccountId(userAccount.getUsername()), prodId, listName);

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Created list successfully"
        ));
    }

    @PostMapping("/createUserListProd")
    public ResponseEntity<?> createUserListProd(@RequestParam(value = "sequence", required = true) Integer sequence, 
                                                @RequestParam(value = "prodId", required = true) Integer prodId, 
                                                @AuthenticationPrincipal UserDetails userAccount){
        user.createUserListProd(user.getUserAccountId(userAccount.getUsername()), sequence, prodId);

        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Added product in the list successfully"
        ));
    }

    @GetMapping("/getFinalFrete")
    public BigDecimal getFinalFrete(@AuthenticationPrincipal UserDetails userAccount){
        return user.getFinalFrete(user.getUserAccountId(userAccount.getUsername()));
    }
}
