package io.gary.bestshop.product.controller;

import io.gary.bestshop.product.domain.Category;
import io.gary.bestshop.product.domain.Product;
import io.gary.bestshop.product.domain.Review;
import io.gary.bestshop.product.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {

    private ProductService productService;

    @GetMapping
    public List<Product> getProducts(@RequestParam(name = "category", required = false) Optional<Category> category) {
        return productService.getProducts(category);
    }

    @PostMapping
    public Product createProduct(@RequestBody @Valid Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public Product updateProduct(@PathVariable("id") String id, @RequestBody @Valid Product product) {
        return productService.updateProduct(id, product);
    }

    @GetMapping("{id}")
    public Product getProduct(@PathVariable("id") String id) {
        return productService.getProduct(id);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
    }

    @PostMapping("{id}/review")
    public Product addReview(@PathVariable("id") String id, @RequestBody @Valid Review review) {
        return productService.addReview(id, review);
    }

}
