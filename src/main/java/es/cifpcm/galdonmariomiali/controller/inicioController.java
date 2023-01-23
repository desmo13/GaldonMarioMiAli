package es.cifpcm.galdonmariomiali.controller;


import es.cifpcm.galdonmariomiali.dao.MunicipioOfferRepository;
import es.cifpcm.galdonmariomiali.dao.ProductOfferRepository;
import es.cifpcm.galdonmariomiali.model.Productoffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class inicioController {
    @Autowired
    ProductOfferRepository productOfferRepository;
    @Autowired
    MunicipioOfferRepository municipioOfferRepository;

    @GetMapping("/")
    public String getRequest() {
        return "Inicio";
}

    @GetMapping("/Producto")
    public String getProducts(Model model){
        model.addAttribute("Products",productOfferRepository.findAll());
        return "Producto";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("Municipios",municipioOfferRepository.findAll());
        return "create";
    }

    @RequestMapping("/save")
    public String save(@RequestParam String prodName,@RequestParam int Municipio, @RequestParam float prodPrice, @RequestParam Integer prodStock, @RequestParam("prodImage") MultipartFile prodImage) {
        Productoffer product = new Productoffer();
        Path rootLocation = Paths.get("src/main/resources/static/Imagenes");
        try {
            Files.copy(prodImage.getInputStream(), rootLocation.resolve(prodImage.getOriginalFilename()));

        } catch ( IOException e) {
            e.printStackTrace();
        }
        String nombreProducto =prodImage.getOriginalFilename();
        product.setProductId((int) productOfferRepository.count());
        product.setProductName(prodName);
        product.setProductPrice(prodPrice);
        product.setProductPicture(nombreProducto);
        product.setProductStock(prodStock);
        product.setIdMunicipio(Municipio);
        productOfferRepository.save(product);

        return "redirect:/show/" + product.getProductId();
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("product", productOfferRepository.findById(id).orElse(null));
        return "show";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long id) {
        Productoffer product = productOfferRepository.findById(id).orElse(null);
        productOfferRepository.delete(product);

        return "redirect:/product";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("product", productOfferRepository.findById(id).orElse(null));
        return "edit";
    }

    @RequestMapping("/update")
    public String update(@RequestParam Long id, @RequestParam String prodName,@RequestParam int Municipio, @RequestParam float prodPrice, @RequestParam Integer prodStock, @RequestParam("prodImage") MultipartFile prodImage) throws IOException {
        Productoffer product = productOfferRepository.findById(id).orElse(null);
        Path rootLocation = Paths.get("src/main/resources/static/Imagenes");
        try {
             Files.copy(prodImage.getInputStream(), rootLocation.resolve(prodImage.getOriginalFilename()));

        } catch ( IOException e) {
            e.printStackTrace();
        }
        String nombreProducto =prodImage.getOriginalFilename();
        product.setProductId((int) productOfferRepository.count());
        product.setProductName(prodName);
        product.setProductPrice(prodPrice);
        product.setProductPicture(nombreProducto);
        product.setProductStock(prodStock);
        product.setIdMunicipio(Municipio);

        return "redirect:/show/" + product.getProductId();
    }
}
