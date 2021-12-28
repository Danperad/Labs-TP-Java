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
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductsService productsService;
    private Filter filter;

    @PostConstruct
    public void post() {
        filter = new Filter();
        filter.setMinPrice(productsService.minPrice());
        filter.setMaxPrice(productsService.maxPrice());
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
        return "products";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute(value = "product") Product product) {
        productsService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/remove/{id}")
    public String removeProduct(Model model, @PathVariable(value = "id") Long id) {
        productsService.deleteByID(id);
        return "redirect:/products";
    }

    @GetMapping("/show/")
    public String showOneProduct(Model model, @RequestParam(required = false, defaultValue = "", name = "id") String id) {
        Product product;
        if (id == null || id.isEmpty()) product = new Product();
        else product = productsService.getById(Long.parseLong(id));
        model.addAttribute("product", product);
        return "product-page";
    }
}
