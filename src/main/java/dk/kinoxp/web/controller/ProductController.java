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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.text.DecimalFormat;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    DecimalFormat decimalFormat = new DecimalFormat("###.#");

    @RequestMapping( value = "/create-product", method = RequestMethod.GET)
    public String createProduct(Model model, HttpSession session) {
        model.addAttribute("product", new Product());

        if (sessionController(session)){
            return "create-product";
        } else {
            return "login";
        }
    }

    @RequestMapping( value = "/create-product", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute("product") Product product) {
        productRepository.save(product);
        System.out.println(product.getName());
        return "redirect:/view-products";
    }

    @RequestMapping( value ="/view-products", method = RequestMethod.GET)
    public String viewProduct(Model model, HttpSession session) {
        List<Product> products = productRepository.findAll();
        ProductService productService = new ProductService();
        for (Product product : products) {
            productService.initializeImage(product);
        }
        model.addAttribute("products", products);

        /*
        if (sessionController(session)){
            return "view-products";
        } else {
            return "login";
        }
        */

        return "view-products";
    }

    @RequestMapping (value = "/update-product", method = RequestMethod.GET)
    public String updateProduct(Model model, @RequestParam("id") int id, HttpSession session) {
        model.addAttribute("product", productRepository.findById(id));

        if (sessionController(session)){
            return "update-product";
        } else {
            return "login";
        }
    }

    @RequestMapping (value = "/update-product", method = RequestMethod.POST)
    public  String getUpdateProduct(@ModelAttribute("product") Product product)
    {
        product = productRepository.save(product);

        return "redirect:/view-products";
    }

    @RequestMapping (value = {"delete-product"}, method = RequestMethod.GET)
    public String deleteProduct(@RequestParam("id") int productId) {
        productRepository.deleteById(productId);
        return "redirect:/view-products";
    }

    private boolean sessionController(HttpSession session){
        if(session.getAttribute("status") != null && session.getAttribute("status").equals("1")){
            return true;
        } else {
            return false;
        }
    }
}
