package com.emiteai.willian.services.impl;

import com.emiteai.willian.models.Product;
import com.emiteai.willian.models.Purchase;
import com.emiteai.willian.repositories.PurchaseRepository;
import com.emiteai.willian.services.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Override
    public List<Purchase> getAllPurchase(){
        return this.purchaseRepository.findAll();
    }

    @Override
    public Purchase getPurchase(Long id){
        return this.purchaseRepository.findById(id).orElseThrow();
    }

    @Override
    public Purchase savePurchase(Purchase purchase){
        if (this.verifySizeListProduct(purchase.getProductList())){
            purchase.setTotalOrderAmount(this.calcTotalOrderAmount(purchase.getProductList()));
            return this.purchaseRepository.save(purchase);
        }
        return null;
    }

    @Override
    public Purchase updatePurchase(Long id, Purchase purchase) {
        return this.purchaseRepository.save(purchase);
    }

    @Override
    public ResponseEntity<Void> deletePurchase(Long id) {
        Purchase purchase = this.getPurchase(id);
        this.purchaseRepository.deleteById(purchase.getId());
        return ResponseEntity.noContent().build();
    }

    private Boolean verifySizeListProduct(List<Product> productList){
        return productList.size() >= 1 && productList.size() <= 3;
    }

    private Double calcTotalOrderAmount(List<Product> productList){
        Double totalPrice = 0.0;

        for (Product product : productList) {
            totalPrice += product.getPrice();
        }

        return totalPrice += totalPrice * 0.1;
    }

}
