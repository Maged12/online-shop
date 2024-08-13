package online_shop.online_shop.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;
import online_shop.online_shop.dto.ProductRequestDto;
import online_shop.online_shop.dto.response.FileUploadResponse;
import online_shop.online_shop.dto.response.ProductResponseDto;
import online_shop.online_shop.service.ProductService;
import online_shop.online_shop.util.FileStorageService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {
        List<ProductResponseDto> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long id) {
        ProductResponseDto productDto = productService.getProductById(id);
        if (productDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDto);
    }

    @PostMapping("/add")
    public ResponseEntity<ProductResponseDto> createProduct(@ModelAttribute ProductRequestDto productDto) {
        final ProductResponseDto product = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@PathVariable Long id,
            @RequestBody ProductRequestDto productDto) {
        final ProductResponseDto productResponseDto = productService.updateProduct(id, productDto);
        return ResponseEntity.ok(productResponseDto);
    }

    @PutMapping("update/{id}/image")
    public ResponseEntity<ProductResponseDto> updateProductImage(@PathVariable Long id,
            @RequestParam MultipartFile image) {
        final ProductResponseDto productDto = productService.updateProductImage(id, image);
        return ResponseEntity.ok(productDto);
    }

    @PostMapping("/uploadSingleFile")
    public FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/downloadFile/")
                .path(fileName)
                .toUriString();
        return new FileUploadResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        System.out.println("downloadFile: " + fileName);
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}