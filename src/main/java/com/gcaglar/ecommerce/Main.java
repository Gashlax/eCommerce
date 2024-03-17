package com.gcaglar.ecommerce;

import com.gcaglar.ecommerce.model.Customer;
import com.gcaglar.ecommerce.model.Invoice;
import com.gcaglar.ecommerce.model.Order;
import com.gcaglar.ecommerce.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Hello, E-commerce!");

        Product mockProduct = new Product("Electronics", "Laptop", 1000, 10);
        Invoice mockInvoice = new Invoice(100, 10);
        Order mockOrder = new Order(mockInvoice, List.of(mockProduct));
        Customer customer = new Customer("Cem", 29, List.of(mockOrder));

        Customer registeredCustomer = addCustomer(customer);
        System.out.println(registeredCustomer.getName());

        int customerCount = customerSize();
        System.out.println(customerCount);

        List<Customer> customersByName = findCustomerByName("Cem");
        double totalPriceOfCustomersAgeBetween = calculateTotalPriceOfCustomersAgeBetween(customersByName, 25, 30);
        System.out.println(totalPriceOfCustomersAgeBetween);

        List<Invoice> invoicesAboveAmount = invoicesAboveAmount(customers, 100);
        System.out.println(invoicesAboveAmount);

    }

    public static Customer addCustomer(Customer customer) {
        customers.add(customer);
        return customer;
    }

    public static int customerSize() {
        System.out.println(customers.size());
        return customers.size();
    }

    public static List<Customer> findCustomerByName(String name) {
        return customers.stream().filter(customer -> customer.getName().equals(name)).toList();
    }

    public static double calculateTotalPriceOfCustomersAgeBetween(List<Customer> customerList, int start, int end) {
        return customerList.stream()
                .filter(customer -> customer.getAge() >= start && customer.getAge() <= end)
                .flatMapToDouble(customer -> customer.getOrders().stream()
                        .mapToDouble(order -> order.getInvoice().getTotalPrice() * order.getInvoice().getAmount()))
                .sum();
    }

    public static List<Invoice> invoicesAboveAmount(List<Customer> customerList, double totalPrice) {
        return customerList.stream()
                .flatMap(customer -> customer.getOrders().stream()
                        .filter(order -> order.getInvoice().getTotalPrice() * order.getInvoice().getAmount() > totalPrice)
                        .map(Order::getInvoice))
                .toList();
    }
}
