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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bphost.principal.model.userCartDTO;
import com.bphost.principal.model.user_account;
import com.bphost.principal.service.userService;

@RestController
@RequestMapping("/account")
public class accountController {
    @Autowired
    private userService user;

    // ############### Registers ###############

    @PostMapping(value = "/registerUserAccount")
    public ResponseEntity<?> registerUserAccount(@RequestBody user_account userAccount) throws IOException{
        try {
            user.registerUserAccount(userAccount.getUsername(), userAccount.getEmail(), userAccount.getTelephone(), userAccount.getPassword());

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "User registed successfully");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno ao cadastrar o usuario: " + e.getMessage());
        }
    }

    @PostMapping(value = "/registerUserHistory")
    public ResponseEntity<?> registerUserHistory(@RequestBody userCartDTO cart,
                                                 @AuthenticationPrincipal UserDetails userAccount) throws IOException{
        try {
            user.registerUserHistory(user.getUserAccountId(userAccount.getUsername()), cart.getProduct_id());

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "History updated successfully");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno ao atualizar o historico do usuario: " + e.getMessage());
        }
    }

    
    @PostMapping(value = "/registerProductFromCart")
    public ResponseEntity<?> registerProductFromCart(@RequestBody userCartDTO cart,
                                                     @AuthenticationPrincipal UserDetails userAccount) throws IOException{
        try {
            user.registerUserCart(user.getUserAccountId(userAccount.getUsername()), cart.getProduct_id(), cart.getSize_id(), cart.getColor_id(), cart.getQuantity());

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Cart updated successfully");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno ao atualizar o carrinho do usuario: " + e.getMessage());
        }
    }

    @PostMapping(value = "/removeProductFromCart")
    public ResponseEntity<?> removeProductFromCart(@RequestBody userCartDTO cart,
                                                   @AuthenticationPrincipal UserDetails userAccount) throws IOException{
        try {
            user.removeProductFromCart(user.getUserAccountId(userAccount.getUsername()), cart.getProduct_id(), cart.getSize_id(), cart.getColor_id(), cart.getQuantity());

            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Cart updated successfully");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro interno ao atualizar o carrinho do usuario: " + e.getMessage());
        }
    }

    @GetMapping("/getCartByUserAccount")
    public List<userCartDTO> getCartByUserAccount(@AuthenticationPrincipal UserDetails userAccount){
        return user.getCartByUserAccount(user.getUserAccountId(userAccount.getUsername()));
    }
}
