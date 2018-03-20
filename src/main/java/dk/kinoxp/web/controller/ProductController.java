package dk.kinoxp.web.controller;

import dk.kinoxp.web.model.entities.Product;
import dk.kinoxp.web.model.repositories.ProductRepository;
import dk.kinoxp.web.model.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @RequestMapping( value = "/create-product", method = RequestMethod.GET)
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "create-product";
    }

    @RequestMapping( value = "/create-product", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute("product") Product product) {
        productRepository.save(product);
        System.out.println(product.getName());
        return "redirect:/index";
    }

    @RequestMapping( value ="/view-products", method = RequestMethod.GET)
    public String viewProduct(Model model) {
        List<Product> products = productRepository.findAll();
        ProductService productService = new ProductService();
        for (Product product : products) {
            productService.initializeImage(product);
        }
        model.addAttribute("products", products);
        //model.addAttribute("products", productRepository.findAll());
        return "view-products";
    }
}
