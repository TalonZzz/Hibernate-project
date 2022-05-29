package com.example.hibernate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Table(name = "customer")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval = false)
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

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer student = (Customer) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
