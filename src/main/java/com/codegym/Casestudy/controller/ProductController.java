package com.codegym.Casestudy.controller;

import com.codegym.Casestudy.model.Category;
import com.codegym.Casestudy.model.Option;
import com.codegym.Casestudy.model.Product;
import com.codegym.Casestudy.model.Sku;
import com.codegym.Casestudy.service.category.ICategoryService;
import com.codegym.Casestudy.service.option.IOptionService;
import com.codegym.Casestudy.service.product.IProductService;
import com.codegym.Casestudy.service.sku.ISkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/product")
@Controller
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private ISkuService skuService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IOptionService optionService;

    @Autowired
    private Environment environment;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @ModelAttribute("sizes")
    public Iterable<Option> sizes() {
        return optionService.findByOptionGroup(1L);
    }

    @ModelAttribute("colors")
    public Iterable<Option> colors() {
        return optionService.findByOptionGroup(2L);
    }

    @GetMapping("/")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("/product/list");
        Iterable<Product> productPageable = productService.findAll();
        modelAndView.addObject("products", productPageable);
        return modelAndView;
    }

    @GetMapping("/list")
    public ResponseEntity<Iterable<Product>> getAll() {
        Iterable<Product> productPageable = productService.findAll();
        return new ResponseEntity<>(productPageable, HttpStatus.OK);
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newProduct = productService.save(product);
        List<Sku> skuList = newProduct.getSkuList();
        for (Sku sku: skuList){
            sku.setProduct(newProduct);
            skuService.save(sku);
        }
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @PostMapping("/img-upload")
    @ResponseBody
    public ResponseEntity<String> fileUpload(@RequestParam Map<String, MultipartFile> data) {
        MultipartFile file = data.get("image");

        String fileName = file.getOriginalFilename();
        String fileUpload = environment.getProperty("upload.path");
        try {
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showCreate(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/product/edit");
        Product product = productService.findById(id).get();
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Product> edit(@PathVariable Long id, @RequestBody Product product) {
        List<Sku> newSkuList = product.getSkuList();
        List<Long> oldSkuIdList = productService.findById(id).get().getSkuIdList();
        List<Long> newSkuIdList = product.getSkuIdList();

        product.removeSku();

        for (Long skuId : oldSkuIdList) {
            if (!newSkuIdList.contains(skuId)) {
                Optional<Sku> sku = skuService.findById(skuId);
                    skuService.delete(skuId);
            }
        }

        for (Sku sku : newSkuList) {
            product.addSku(sku);
        }

        productService.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDelete(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("/product/delete");
        Product product = productService.findById(id).get();
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/find-by-product-id-and-and-options/{productId}/{sizeOptionId}/{colorOptionId}")
    public ResponseEntity<Sku> findByProductIdAndAndOptions(@PathVariable Long productId, @PathVariable Long sizeOptionId, @PathVariable Long colorOptionId) {
        Sku sku = skuService.findByProductIdAndAndOptions(productId, sizeOptionId, colorOptionId);
        return new ResponseEntity<>(sku, HttpStatus.OK);
    }
}
