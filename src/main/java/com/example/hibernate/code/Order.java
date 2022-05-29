package com.example.hibernate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "order")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<Customer_Order> customer_order = new ArrayList<>();

    public List<Customer_Order> getCustomer_Order() {
        return customer_order;
    }

    public void setCustomer_Order(List<Customer_Order> ts) {
        this.customer_order = ts;
    }

    public void addCustomer_Order(Customer_Order ts) {
        this.customer_order.add(ts);
    }


}
