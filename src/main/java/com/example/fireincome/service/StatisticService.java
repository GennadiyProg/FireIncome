package com.example.fireincome.service;

import com.example.fireincome.model.Organization;
import com.example.fireincome.model.view.Statistic;

import java.time.LocalDateTime;
import java.util.List;

public interface StatisticService {
    List<Statistic> loadStatisticByCategory(Organization organization);

    List<Statistic> loadStatisticByBranch(Organization organization);

    List<Statistic> loadStatisticByMonth(Organization organization);
}
