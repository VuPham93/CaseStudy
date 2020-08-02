package com.codegym.Casestudy.controller;

import com.codegym.Casestudy.model.Cart;
import com.codegym.Casestudy.model.Product;
import com.codegym.Casestudy.model.Sku;
import com.codegym.Casestudy.service.cart.ICartService;
import com.codegym.Casestudy.service.option.IOptionService;
import com.codegym.Casestudy.service.product.IProductService;
import com.codegym.Casestudy.service.sku.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

//    @GetMapping("/addtocart/{id}")
//    public ResponseEntity<Product> addToCart(@PathVariable("id") Long id) {
//        Cart cart = cartService.findCartByUserId(1L);
//        if (cart != null) {
//            List<Product> products = (List<Product>) cart.getProducts();
//            products.add(productService.findById(id).get());
//            cart.setCartQuantity(1);
//            cart.setProducts(products);
//            cartService.save(cart);
//        } else {
//            cart.setUserId(1L);
//            List<Product> products = (List<Product>) cart.getProducts();
//            products.add(productService.findById(id).get());
//            cart.setCartQuantity(1);
//            cart.setProducts(products);
//            cartService.save(cart);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @GetMapping("/addtocart/{productId}/{sizeOption}/{colorOption}")
    public ResponseEntity<Sku> addToCart(@PathVariable("productId") Long productId,@PathVariable("sizeOption") Long sizeOption,
      @PathVariable("colorOption") Long colorOption) {
        Cart cart = cartService.findCartByUserId(1L);
        if (cart != null) {
            List<Sku> skus = (List<Sku>) cart.getSkus();
            skus.add(skuService.findByProductIdAndOptions(productId,sizeOption,colorOption));
            cart.setCartQuantity(1);
            cart.setSkus(skus);
            cartService.save(cart);
        } else {
            cart.setUserId(1l);
            List<Sku> skus = (List<Sku>) cart.getSkus();
            skus.add(skuService.findByProductIdAndOptions(productId,sizeOption,colorOption));
            cart.setCartQuantity(1);
            cart.setSkus(skus);
            cartService.save(cart);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ModelAndView listCart() {
        Cart cart = cartService.findCartByUserId(1L);
        List<Sku> skus= (List<Sku>) cart.getSkus();
        Map<Product,Sku> productCart = new HashMap<>();
        for (int i = 0; i < skus.size();i++){
            productCart.put(productService.findProductBySkuId(skus.get(i).getSkuId()),skus.get(i));
        }
        ModelAndView modelAndView = new ModelAndView("cart/cart");
        modelAndView.addObject("productCart", productCart);
        modelAndView.addObject("cart", cart);
        return modelAndView;
    }
//
//    @GetMapping("/deleteproduct/{id}")
//    public ModelAndView detleteProduct(@PathVariable("id") Long id) {
//        Cart cart = cartService.findCartByUserId(1L);
//        List<Product> products = (List<Product>) cart.getProducts();
//        products.remove(productService.findById(id));
//        cart.setProducts(products);
//        cartService.save(cart);
//        ModelAndView modelAndView = new ModelAndView("/cart/cart");
//        modelAndView.addObject("products", products);
//        modelAndView.addObject("cart", cart);
//        return modelAndView;
//
//    }
}
