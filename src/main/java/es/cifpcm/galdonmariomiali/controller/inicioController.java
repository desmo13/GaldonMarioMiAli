package es.cifpcm.galdonmariomiali.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.cifpcm.galdonmariomiali.dao.MunicipioOfferRepository;
import es.cifpcm.galdonmariomiali.dao.ProductOfferRepository;
import es.cifpcm.galdonmariomiali.dao.ProvinciaRepository;
import es.cifpcm.galdonmariomiali.model.Municipio;
import es.cifpcm.galdonmariomiali.model.Productoffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class inicioController {
    @Autowired
    ProductOfferRepository productOfferRepository;
    @Autowired
    MunicipioOfferRepository municipioOfferRepository;
    @Autowired
    ProvinciaRepository provinciaRepository;

    @GetMapping("/")
    public String getRequest() {
        return "Inicio";
}

    @GetMapping("/Producto")
    public String getProducts(Model model){
        model.addAttribute("Provincias",provinciaRepository.findAll());
        model.addAttribute("Municipios",municipioOfferRepository.findAll());
        model.addAttribute("Products",productOfferRepository.findAll());
        return "Producto";
    }
    @RequestMapping("/obtenerMunicipio")
    public ResponseEntity getProductsPots(@RequestParam Long municipioId, Model model){


        // agregar objetos a la lista
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(productOfferRepository.findProductoffersByIdMunicipio(Math.toIntExact(municipioId)));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("Municipios",municipioOfferRepository.findAll());
        return "create";
    }

    @PostMapping("/save")
    public String save(@RequestParam String prodName,@RequestParam int Municipio, @RequestParam float prodPrice, @RequestParam Integer prodStock, @RequestParam("prodImage") MultipartFile prodImage) {
        Productoffer product = new Productoffer();
        Path rootLocation = Paths.get("src/main/resources/static/Imagenes");
        try {
            Files.copy(prodImage.getInputStream(), rootLocation.resolve(prodImage.getOriginalFilename()));

        } catch ( IOException e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        Optional<es.cifpcm.galdonmariomiali.model.Municipio> municipio =municipioOfferRepository.findById(Long.valueOf(Municipio));
        if(product==null ||prodName.trim().isEmpty()||prodImage.isEmpty()||municipio==null||prodStock<0||prodPrice<0){
            return "redirect:/error";
        }
        String nombreProducto =prodImage.getOriginalFilename();
        product.setProductId((int) productOfferRepository.count()+1);
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
        Productoffer producto= productOfferRepository.findById(id).orElse(null);
        if (producto !=null) {
            model.addAttribute("product", producto);
            return "show";
        }
        return "redirect:/error";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam Long id) {
        Productoffer product = productOfferRepository.findById(id).orElse(null);
        if(product!=null) {

            try {
                productOfferRepository.delete(product);


                Path archivo = Paths.get("src/main/resources/static/Imagenes"+product.getProductPicture());
                Files.delete(archivo);
                return "redirect:/Producto";
            } catch (IOException e) {
                return "redirect:/error";
            }

        }
        return "redirect:/error";

    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Productoffer product = productOfferRepository.findById(id).orElse(null);
        List<Municipio> municipio = municipioOfferRepository.findAll();
        if(product!=null||municipio.size()>0) {
            model.addAttribute("product", product);
            model.addAttribute("Municipios",municipio);
            return "edit";
        }
        return "redirect:/error";
    }

    @RequestMapping("/update")
    public String update(@RequestParam Long id, @RequestParam String prodName,@RequestParam int Municipio, @RequestParam float prodPrice, @RequestParam Integer prodStock, @RequestParam("prodImage") MultipartFile prodImage) throws IOException {
        Productoffer product = productOfferRepository.findById(id).orElse(null);
        Path rootLocation = Paths.get("src/main/resources/static/Imagenes");
        try {
            if(!prodImage.isEmpty()){
                Path archivo = Paths.get("src/main/resources/static/Imagenes"+product.getProductPicture());
                Files.delete(archivo);
                Files.copy(prodImage.getInputStream(), rootLocation.resolve(prodImage.getOriginalFilename()));
            }


        } catch ( IOException e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        //Optional<es.cifpcm.galdonmariomiali.model.Municipio> municipio =municipioOfferRepository.findById(Municipio);
        if(product==null ||prodName.trim().isEmpty()||Municipio<0||prodStock<0||prodPrice<0){
            return "redirect:/error";
        }
        String nombreProducto =prodImage.getOriginalFilename();
        product.setProductId(Math.toIntExact(id));
        product.setProductName(prodName);
        product.setProductPrice(prodPrice);
        product.setProductPicture(nombreProducto);
        product.setProductStock(prodStock);
        product.setIdMunicipio(Municipio);

        return "redirect:/show/" + product.getProductId();
    }
}
