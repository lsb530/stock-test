package com.boki.stock.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;

    private Long quantity;

    public Stock() {
    }

    public Stock(Long id, Long productId, Long quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Stock(Long productId, Long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }

    public Long getQuantity() {
        return quantity;
    }

    public void decrease(Long quantity) {
        if (this.quantity - quantity < 0) {
            throw new RuntimeException("재고는 0개 미만이 될 수 없습니다.");
        }

        this.quantity -= quantity;
    }

    public static class StockBuilder {
        private Long id;
        private Long productId;
        private Long quantity;

        public StockBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public StockBuilder productId(Long productId) {
            this.productId = productId;
            return this;
        }

        public StockBuilder quantity(Long quantity) {
            this.quantity = quantity;
            return this;
        }

        public Stock build() {
            return new Stock(id, productId, quantity);
        }

    }
}
