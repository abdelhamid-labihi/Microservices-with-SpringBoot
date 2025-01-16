package org.glsid.billingservice.controller;

import lombok.RequiredArgsConstructor;
import org.glsid.billingservice.entities.Bill;
import org.glsid.billingservice.model.Customer;
import org.glsid.billingservice.model.Product;
import org.glsid.billingservice.proxies.CustomerServiceProxy;
import org.glsid.billingservice.proxies.ProductServiceProxy;
import org.glsid.billingservice.repositories.BillRepository;
import org.glsid.billingservice.repositories.ProductItemRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BillingRestController {
    private final BillRepository billRepository;
    private final ProductItemRepository productItemRepository;
    private final CustomerServiceProxy customerServiceProxy;
    private final ProductServiceProxy productServiceProxy;

    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable Long id) {
        Bill bill = billRepository.findById(id).orElseThrow(() -> new RuntimeException("Bill not found"));
        Customer customer = customerServiceProxy.getCustomerById(bill.getCustomerID());
        bill.setCustomer(customer);
        bill.getProductItems().forEach(pi -> {
            Product product = productServiceProxy.getProductById(pi.getProductID());
            pi.setProduct(product);
        });
        return bill;
    }

}
