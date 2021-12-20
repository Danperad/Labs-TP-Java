package com.vyatsu.lab.controllers;

import com.vyatsu.lab.entities.Product;
import com.vyatsu.lab.repositories.Filter;
import com.vyatsu.lab.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/products")
public class ProductsController {
    private ProductsService productsService;
    private Filter filter;
    @PostConstruct
    public void post(){
        filter = new Filter();
    }

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showProductsList(Model model) {
        Product product = new Product();
        model.addAttribute("products", productsService.getAllProducts());
        model.addAttribute("product", product);
        model.addAttribute("filter", filter);
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product") Product product) {
        productsService.add(product);
        return "redirect:/products";
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        productsService.remove(product);
        return "redirect:/products";
    }

    @GetMapping("/filter/{minPrice}&{maxPrice}&{text}")
    public String filterProduct(Model model, @RequestParam(name = "minPrice") String min, @RequestParam(name = "maxPrice") String max, @RequestParam(name = "text") String text) {
        Product product = new Product();
        if(!max.equals("")) filter.setMaxPrice(Integer.parseInt(max));
        else filter.setMaxPrice(0);
        if(!min.equals("")) filter.setMinPrice(Integer.parseInt(min));
        else filter.setMinPrice(0);
        filter.setText(text);
        model.addAttribute("products", productsService.getFilterProducts(filter.getMinPrice(), filter.getMaxPrice(), text));
        model.addAttribute("product", product);
        model.addAttribute("filter", filter);
        return "products";
    }

    @GetMapping("/show/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") String id) {
        Product product;
        product = productsService.getById(Long.parseLong(id));
        model.addAttribute("product", product);
        return "product-page";
    }
    @GetMapping("/show/")
    public String showOneProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product-page";
    }
}
