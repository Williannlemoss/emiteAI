package com.emiteai.willian.repositories;

import com.emiteai.willian.models.TransportOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportOrderRepository extends JpaRepository<TransportOrder, Long> {
}