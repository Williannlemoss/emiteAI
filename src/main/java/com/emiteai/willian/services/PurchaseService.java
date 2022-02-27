package com.emiteai.willian.services;

import com.emiteai.willian.dto.request.PurchaseSaveDTO;
import com.emiteai.willian.dto.response.PurchaseDTO;
import com.emiteai.willian.models.Purchase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface PurchaseService {

    List<PurchaseDTO> getAllPurchase();

    PurchaseDTO getPurchase(Long id);

    PurchaseDTO savePurchase(PurchaseSaveDTO purchase);

    Purchase updatePurchase(Long id, Purchase purchase);

    ResponseEntity<Void> deletePurchase(Long id);

    ResponseEntity<Void> exportToExel(Long purchaseId, HttpServletResponse response) throws IOException;

}
