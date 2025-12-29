package com.bphost.principal.controller;

import java.io.IOException;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RestController;
import com.bphost.principal.model.userCartDTO;
import com.bphost.principal.model.userDTO;
import com.bphost.principal.model.user_account;
import com.bphost.principal.model.user_address;
import com.bphost.principal.model.user_history;
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
    public user_address getUserAddress(@AuthenticationPrincipal UserDetails userAccount){
        return user.getUserAddress(user.getUserAccountId(userAccount.getUsername()));
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
}
