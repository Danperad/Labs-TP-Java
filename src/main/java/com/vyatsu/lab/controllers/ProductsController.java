package com.vyatsu.lab.controllers;

import com.vyatsu.lab.entities.Product;
import com.vyatsu.lab.repositories.Filter;
import com.vyatsu.lab.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Controller
public class ProductsController {
    @Autowired
    private ProductsService productsService;
    private Filter filter;

    @PostConstruct
    public void post() {
        filter = new Filter();
    }

    @GetMapping
    public String showProductsList(
            Model model,
            @RequestParam(required = false, defaultValue = "", name = "minPrice") String min,
            @RequestParam(required = false, defaultValue = "", name = "maxPrice") String max,
            @RequestParam(required = false, defaultValue = "", name = "text") String text,
            @PageableDefault(sort = {"title"}, direction = Sort.Direction.ASC, size = 5) Pageable pagable
    ) {
        Page<Product> page;
        if ((min != null && !min.isEmpty()) || (max != null && !max.isEmpty()) || (text != null && !text.isEmpty())) {
            if (min != null && !min.isEmpty()) filter.setMinPrice(Integer.parseInt(min));
            if (max != null && !max.isEmpty()) filter.setMaxPrice(Integer.parseInt(max));
            if (text != null && !text.isEmpty()) filter.setText(text);
            page = productsService.getFilterProducts(filter, pagable);
        } else {
            page = productsService.getAllProducts(pagable);
        }
        Product product = new Product();
        model.addAttribute("products", page);
        model.addAttribute("product", product);
        model.addAttribute("filter", filter);
        model.addAttribute("topproducts", productsService.findTop());
        return "products";
    }

    @GetMapping("/add")
    public String addProduct(@RequestParam(value = "Name") String Name,
                             @RequestParam(value = "Price") Integer Price) {
        Product product = new Product();
        product.setTitle(Name);
        product.setPrice(Price);
        productsService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(Model model, @PathVariable(value = "id") Long id) {
        productsService.deleteByID(id);
        return "redirect:/products";
    }

    @GetMapping("/show/")
    public String showOneProductAdmin(
            Model model,
            @RequestParam(name = "id", defaultValue = "", required = false) Long id) {
        Product product;
        if(id == null) product = new Product();
        else product = productsService.getById(id);
        model.addAttribute("product", product);
        return "product-page-admin";
    }

    @GetMapping("/show_product/{id}")
    public String showOneProduct(Model model, @PathVariable(value = "id") Long id) {
        Product product = productsService.getById(id);
        product.incCount();
        productsService.save(product);
        model.addAttribute("product", product);
        return "product-page";
    }

    @PostMapping("/editproduct")
    public String editProduct(@ModelAttribute(value = "product") Product product) {
        productsService.save(product);
        return "redirect:/products";
    }
}
