package com.example.warehouse.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String type;
    @ManyToOne
    @JoinColumn(name = "material_id")
    private Material material;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(nullable = false)
    private Double amount;
    @Column(nullable = false)
    private LocalDateTime dateTime;
    @JoinColumn(name = "username", nullable = false)
    private String username;
    protected Transaction() { }
    public static class Builder {
        private final Transaction transaction;
        public Builder() {
            transaction = new Transaction();
        }
        public Builder setType(String type) {
            transaction.type = type;
            return this;
        }
        public Builder setMaterial(Material material) {
            transaction.material = material;
            return this;
        }
        public Builder setProduct(Product product) {
            transaction.product = product;
            return this;
        }
        public Builder setAmount(Double amount) {
            transaction.amount = amount;
            return this;
        }
        public Builder setDateTime(LocalDateTime dateTime) {
            transaction.dateTime = dateTime;
            return this;
        }
        public Builder setUsername(String username) {
            transaction.username = username;
            return this;
        }
        public Transaction build() {
            return transaction;
        }
    }
}
