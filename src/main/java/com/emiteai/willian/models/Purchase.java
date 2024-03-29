package com.emiteai.willian.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL)
    private List<Product> productList = new ArrayList<>();

    private Double totalOrderAmount;

    private Boolean isSent;

    @ManyToOne(fetch = FetchType.LAZY)
    private TransportOrder transportOrder;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist(){
        this.setTotalOrderAmount(0D);
        this.createdAt = LocalDateTime.now();
        this.setIsSent(false);
    }

    @PreUpdate
    public void preUpdate(){
        this.updatedAt = LocalDateTime.now();
    }
}
