package com.emiteai.willian.services.impl;

import com.emiteai.willian.dto.request.PurchaseSaveDTO;
import com.emiteai.willian.dto.response.PurchaseDTO;
import com.emiteai.willian.exceptions.GlobalException;
import com.emiteai.willian.models.Product;
import com.emiteai.willian.models.Purchase;
import com.emiteai.willian.repositories.ProductRepository;
import com.emiteai.willian.repositories.PurchaseRepository;
import com.emiteai.willian.services.ProductService;
import com.emiteai.willian.services.PurchaseService;
import com.emiteai.willian.utils.ProductExelExport;
import com.emiteai.willian.utils.PurchaseConverter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

  private final PurchaseRepository purchaseRepository;
  private final ProductRepository productRepository;
  private final PurchaseConverter purchaseConverter;

  @Override
  public List<PurchaseDTO> getAllPurchase() {
    List<Purchase> purchaseList = purchaseRepository.findAll();

    if (purchaseList.isEmpty()) {
      throw new GlobalException("Lista de pedidos vazia", HttpStatus.BAD_REQUEST);
    }
    return purchaseConverter.toCollectionDTO(purchaseList);
  }

  private Purchase getById(Long id) {
    return purchaseRepository
        .findById(id)
        .orElseThrow(
            () -> new GlobalException("Lista de pedidos não encontrada", HttpStatus.NOT_FOUND));
  }

  @Override
  public PurchaseDTO getPurchase(Long id) {
    return purchaseConverter.toDTO(getById(id));
  }

  @Override
  public PurchaseDTO savePurchase(PurchaseSaveDTO purchase) {

    if (this.verifySizeListProduct(purchase.getProductsIds())) {
      Purchase purchaseToBeCreate = purchaseRepository.save(new Purchase());

      for (Long id : purchase.getProductsIds()) {
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()){
          deletePurchase(purchaseToBeCreate.getId());
          throw new GlobalException("Não há produto com este ID", HttpStatus.NOT_FOUND);
        }
        product.get().setPurchase(purchaseToBeCreate);
        productRepository.save(product.get());

        purchaseToBeCreate.setTotalOrderAmount(
            purchaseToBeCreate.getTotalOrderAmount() + product.get().getPrice());
      }

      purchaseToBeCreate.setTotalOrderAmount(
          purchaseToBeCreate.getTotalOrderAmount() + (purchaseToBeCreate.getTotalOrderAmount()
              * 0.1));
      return purchaseConverter.toDTO(this.purchaseRepository.save(purchaseToBeCreate));
    } else {
      throw new GlobalException("Número de pedidos não está entre 1 e 3", HttpStatus.BAD_REQUEST);
    }
  }

  @Override
  public Purchase updatePurchase(Long id, Purchase purchase) {
    return this.purchaseRepository.save(purchase);
  }

  @Override
  public ResponseEntity<Void> deletePurchase(Long id) {
    Purchase purchase = getById(id);
    this.purchaseRepository.deleteById(purchase.getId());
    return ResponseEntity.noContent().build();
  }

  @Override
  public ResponseEntity<Void> exportToExel(Long purchaseId, HttpServletResponse response)
      throws IOException {
    Purchase purchase = getById(purchaseId);

    if (purchase.getTotalOrderAmount() >= 500) {
      response.setContentType("application/octet-stream");
      String headerKey = "Content-Disposition";

      DateFormat dateFormat = new SimpleDateFormat("_dd-MM-yyyy_HH");
      String currentDateTime = dateFormat.format(new Date());

      String headerValue =
          "attachement; filename=purchase_" + purchaseId + currentDateTime + ".xlsx";

      response.setHeader(headerKey, headerValue);

      ProductExelExport productExelExport = new ProductExelExport(purchase.getProductList());

      productExelExport.export(response);
    } else {
      throw new GlobalException("O valor deve ser superior a 500 reais", HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity.noContent().build();
  }

  private Boolean verifySizeListProduct(List<Long> productList) {
    return productList.size() >= 1 && productList.size() <= 3;
  }
}
