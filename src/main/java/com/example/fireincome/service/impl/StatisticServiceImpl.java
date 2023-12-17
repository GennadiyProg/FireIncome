package com.example.fireincome.service.impl;

import com.example.fireincome.model.Category;
import com.example.fireincome.model.Organization;
import com.example.fireincome.model.view.Statistic;
import com.example.fireincome.service.CategoryService;
import com.example.fireincome.service.SaleService;
import com.example.fireincome.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final SaleService saleService;
    private final CategoryService categoryService;

    @Override
    public List<Statistic> loadStatisticByCategory(Organization organization) {
        List<Category> allCategories = categoryService.findAllByOrganization(organization);
        List<Statistic> fullStatistic = new ArrayList<>();
        allCategories.forEach(category -> fullStatistic.add(new Statistic(category, saleService.countSalesByCategory(category))));
        return fullStatistic;
    }

    @Override
    public List<Statistic> loadStatisticByBranch(Organization organization) {
        return organization.getBranches().stream().map(branch -> new Statistic(branch, saleService.countSalesByBranch(branch))).toList();
    }

    @Override
    public List<Statistic> loadStatisticByMonth(Organization organization) {
        LocalDate firstSaleTime = saleService.loadTimeFirstSaleInOrganization(organization).toLocalDate();
        LocalDate lastSaleTime = saleService.loadTimeLastSaleInOrganization(organization).toLocalDate();
        List<Statistic> fullStatistic = new ArrayList<>();
        int firstYear = firstSaleTime.getYear();
        int firstMonth = firstSaleTime.getMonthValue();
        int lastYear = lastSaleTime.getYear();
        int lastMonth = lastSaleTime.getMonthValue() + 1;
        int monthCount = (lastYear - firstYear) * 12 + lastMonth - firstMonth;

        LocalDate date = firstSaleTime.withDayOfMonth(1);
        for (int i = 0; i < monthCount; i++) {
            fullStatistic.add(new Statistic(date, saleService.countSalesInPeriod(date.atStartOfDay(), date.plusMonths(1).atStartOfDay())));
            date = date.plusMonths(1);
        }
        return fullStatistic;
    }
}
