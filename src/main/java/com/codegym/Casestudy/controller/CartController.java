package com.codegym.Casestudy.controller;

import com.codegym.Casestudy.model.Cart;
import com.codegym.Casestudy.model.Product;
import com.codegym.Casestudy.model.Sku;
import com.codegym.Casestudy.service.cart.ICartService;
import com.codegym.Casestudy.service.customer.ICustomerService;
import com.codegym.Casestudy.service.option.IOptionService;
import com.codegym.Casestudy.service.product.IProductService;
import com.codegym.Casestudy.service.sku.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SessionAttributes("userIdLogin")
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ICartService cartService;
    @Autowired
    IProductService productService;
    @Autowired
    ISkuService skuService;
    @Autowired
    IOptionService optionService;
    @Autowired
    ICustomerService customerService;
    @ModelAttribute("userIdLogin")
    public Long userIdLogin(){
        String mail = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            mail = ((UserDetails)principal).getUsername();
        } else {
            mail = principal.toString();
        }
        Long userIdLogin = null;
        userIdLogin = customerService.findByMail(mail).getId();
        return userIdLogin;
    }


    @GetMapping("/addtocart/{productId}/{sizeOption}/{colorOption}")
    public ResponseEntity<Sku> addToCart(@PathVariable("productId") Long productId,@PathVariable("sizeOption") Long sizeOption, @PathVariable("colorOption") Long colorOption,@ModelAttribute("userIdLogin") Long userIdLogin) {
        Cart cart = cartService.findCartByUserId(userIdLogin);
        if (cart != null) {
            List<Sku> skus = (List<Sku>) cart.getSkus();
            skus.add(skuService.findByProductIdAndOptions(productId,sizeOption,colorOption));
            cart.setCartQuantity(1);
            cart.setSkus(skus);
            cartService.save(cart);
        } else {
            Cart cart1 = new Cart(userIdLogin);
            List<Sku> skus = new ArrayList<>();

            skus.add(skuService.findByProductIdAndOptions(productId,sizeOption,colorOption));
            cart1.setSkus(skus);
            cart1.setCartQuantity(1);
            cartService.save(cart1);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ModelAndView listCart(@ModelAttribute("userIdLogin") Long userIdLogin) {
        Cart cart = cartService.findCartByUserId(userIdLogin);
        ModelAndView modelAndView;
        if (cart!= null){
            List<Sku> skus= (List<Sku>) cart.getSkus();
            Map<Product, Map<Long, Long>> outerMap = new HashMap<>();
            double totalPrice = 0;
            for (int i = 0; i < skus.size();i++){
                Map<Long, Long> innerMap = new HashMap<>();
                Long size = optionService.findByOptionId(skus.get(i).getOption(1L)).get().getOptionId();
                Long color = optionService.findByOptionId(skus.get(i).getOption(2L)).get().getOptionId();
                innerMap.put(size,color);
                outerMap.put(productService.findProductBySkuId(skus.get(i).getSkuId()),innerMap);
                totalPrice+=productService.findProductBySkuId(skus.get(i).getSkuId()).getPrice();
            }
             modelAndView = new ModelAndView("cart/cart");
            modelAndView.addObject("productCart", outerMap);
            modelAndView.addObject("cart", cart);
            modelAndView.addObject("totalPrice", totalPrice);
        }else {

             modelAndView = new ModelAndView("redirect:/home");

        }

        return modelAndView;
    }

    @DeleteMapping("/delete-product/{productId}/{sizeOption}/{colorOption}")
    public ResponseEntity<Double> deleteProduct(@PathVariable("productId") Long productId,@PathVariable("sizeOption") Long sizeOption, @PathVariable("colorOption") Long colorOption,@ModelAttribute("userIdLogin") Long userIdLogin){
        Cart cart = cartService.findCartByUserId(userIdLogin);
        List<Sku> skus = (List<Sku>) cart.getSkus();
        skus.remove(skuService.findByProductIdAndOptions(productId,sizeOption,colorOption));
        cart.setSkus(skus);
        cartService.save(cart);
        double totalPrice = 0;
        for (int i = 0; i < skus.size();i++){
            totalPrice+=productService.findProductBySkuId(skus.get(i).getSkuId()).getPrice();
        }
        return new ResponseEntity<>(totalPrice, HttpStatus.OK);
    }
}
