package com.emiteai.willian.services;

import com.emiteai.willian.models.Purchase;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PurchaseService {

    List<Purchase> getAllPurchase();

    Purchase getPurchase(Long id);

    Purchase savePurchase(Purchase purchase);

    Purchase updatePurchase(Long id, Purchase purchase);

    ResponseEntity<Void> deletePurchase(Long id);

}
