package com.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.entity.Product;
import com.project.repository.ProductRepository;

@Controller
public class ProductController {
	@Autowired
	private ProductRepository productRepository;

	@GetMapping("/")
	public String loadIndex(Model model) {

		model.addAttribute("product", new Product());
		return "index";
	}

	@PostMapping("/saveProduct")
	public String saveProduct(@Validated @ModelAttribute("product") Product p, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "index";
		}
		p = productRepository.save(p);

		if (p.getPid() != null) {
			model.addAttribute("msg", "Produt Saved");
		}
		return "index";
	}

	@GetMapping("/getAllProduct")
	public String getAllProduct(Model model) {
		List<Product> productList = productRepository.findAll();
		model.addAttribute("products", productList);
		return "productList";
	}

	@GetMapping("/deleteProduct")
	public String deletProduct(@RequestParam("pid") Integer pid, Model model) {
		productRepository.deleteById(pid);

		model.addAttribute("msg", "Product Deleted ");
		model.addAttribute("products", productRepository.findAll());

		return "productList";
	}
	
	@GetMapping("/editProduct")
	public String editProduct(@RequestParam("pid") Integer pid, Model model) {
		Optional<Product> byId = productRepository.findById(pid);
		if(byId.isPresent()) {
			Product product = byId.get();
			model.addAttribute("product", product);
		}
		
		return "index";
	}

}
