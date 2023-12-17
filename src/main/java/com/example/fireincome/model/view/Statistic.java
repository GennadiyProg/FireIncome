package com.example.fireincome.model.view;

import com.example.fireincome.model.Branch;
import com.example.fireincome.model.Category;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Statistic {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Category category;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Branch branch;
    private int count;

    public Statistic() {
    }

    public Statistic(Category category, int count) {
        this.category = category;
        this.count = count;
    }

    public Statistic(LocalDate date, int count) {
        this.date = date;
        this.count = count;
    }

    public Statistic(Branch branch, int count) {
        this.branch = branch;
        this.count = count;
    }
}
