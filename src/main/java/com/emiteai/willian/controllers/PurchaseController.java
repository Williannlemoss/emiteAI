package com.emiteai.willian.controllers;

import com.emiteai.willian.dto.request.PurchaseSaveDTO;
import com.emiteai.willian.dto.response.PurchaseDTO;
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
    public List<PurchaseDTO> getPurchase(){
        return purchaseService.getAllPurchase();
    }

    @GetMapping(value = "/{purchaseId}")
    public PurchaseDTO getProduct(@PathVariable Long purchaseId){
        return this.purchaseService.getPurchase(purchaseId);
    }

    @PutMapping(value = "/{purchaseId}")
    public Purchase updateProduct(@PathVariable Long purchaseId, @RequestBody Purchase purchase){
        return this.purchaseService.updatePurchase(purchaseId, purchase);
    }

    @PostMapping
    public PurchaseDTO saveProduct(@RequestBody PurchaseSaveDTO purchase){
        return this.purchaseService.savePurchase(purchase);
    }

    @DeleteMapping(value = "/{purchaseId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long purchaseId){
        return this.purchaseService.deletePurchase(purchaseId);
    }

    @GetMapping(value = "/excel/{purchaseId}")
    public ResponseEntity<Void> exportToExel(@PathVariable Long purchaseId, HttpServletResponse response) throws IOException {
      return this.purchaseService.exportToExel(purchaseId, response);
    }
}
