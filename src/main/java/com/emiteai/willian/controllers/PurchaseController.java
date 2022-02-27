package com.emiteai.willian.controllers;

import com.emiteai.willian.models.Product;
import com.emiteai.willian.utils.ProductExelExport;
import com.emiteai.willian.models.Purchase;
import com.emiteai.willian.services.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @GetMapping
    public List<Purchase> getPurchase(){
        return purchaseService.getAllPurchase();
    }

    @GetMapping(value = "/{purchaseId}")
    public Purchase getProduct(@PathVariable Long purchaseId){
        return this.purchaseService.getPurchase(purchaseId);
    }

    @PutMapping(value = "/{purchaseId}")
    public Purchase updateProduct(@PathVariable Long purchaseId, @RequestBody Purchase purchase){
        return this.purchaseService.updatePurchase(purchaseId, purchase);
    }

    @PostMapping
    public Purchase saveProduct(@RequestBody Purchase purchase){
        return this.purchaseService.savePurchase(purchase);
    }

    @DeleteMapping(value = "/{purchaseId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long purchaseId){
        return this.purchaseService.deletePurchase(purchaseId);
    }

    @GetMapping(value = "exel/{purchaseId}")
    public void exportToExel(@PathVariable Long purchaseId, HttpServletResponse response) throws IOException {
        Purchase purchase = this.purchaseService.getPurchase(purchaseId);

        if (purchase.getTotalOrderAmount() >= 500){
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";

            DateFormat dateFormat = new SimpleDateFormat("_dd-MM-yyyy_HH");
            String currentDateTime = dateFormat.format(new Date());

            String headerValue = "attachement; filename=purchase_" + purchaseId + currentDateTime  +".xlsx";

            response.setHeader(headerKey,headerValue);

            List<Product> productList = this.purchaseService.getPurchase(purchaseId).getProductList();

            ProductExelExport productExelExport = new ProductExelExport(purchase.getProductList());

            productExelExport.export(response);
        }
    }
}
