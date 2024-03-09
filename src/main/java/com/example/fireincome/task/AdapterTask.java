package com.example.fireincome.task;

import com.example.fireincome.api.client.AdapterClient;
import com.example.fireincome.model.Branch;
import com.example.fireincome.model.ClientSale;
import com.example.fireincome.model.Organization;
import com.example.fireincome.repos.ClientSaleRepo;
import com.example.fireincome.service.OrganizationService;
import com.example.fireincome.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class AdapterTask {
	private final OrganizationService organizationService;
	private final AdapterClient adapterClient;
	private final ClientSaleRepo clientSaleRepo;
	private final SaleService saleService;

	@Scheduled(fixedDelay = 300000)
	public void scheduleLoadSalesTask() {
		List<Organization> orgs = organizationService.allOrgs();
		orgs.forEach(org -> asyncLoadSales(org.getBranches(), org));
	}

	@Async
	public void asyncLoadSales(List<Branch> branches, Organization organization) {
		branches.forEach(branch -> {
			List<ClientSale> sales = adapterClient.loadSales(URI.create(branch.getAdapterUrl()));
			clientSaleRepo.saveAll(sales);
			saleService.process(sales, organization);
		});
	}
}
