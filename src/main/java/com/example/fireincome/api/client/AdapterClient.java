package com.example.fireincome.api.client;

import com.example.fireincome.model.ClientSale;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;
import java.util.List;

@FeignClient(name = "adapter")
public interface AdapterClient {
	@GetMapping
	List<ClientSale> loadSales(URI baseUrl);
}
