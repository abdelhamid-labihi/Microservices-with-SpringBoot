package org.glsid.billingservice.proxies;

import org.glsid.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerServiceProxy {
    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable Long id);
}
