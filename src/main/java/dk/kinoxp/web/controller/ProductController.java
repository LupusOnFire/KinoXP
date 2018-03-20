package dk.kinoxp.web.controller;

import dk.kinoxp.web.model.entities.Product;
import dk.kinoxp.web.model.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @RequestMapping( value = "/create-product", method = RequestMethod.GET)
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        return "create-product";
    }

}
