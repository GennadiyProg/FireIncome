package com.example.fireincome.model.view;

import com.example.fireincome.model.Organization;
import com.example.fireincome.model.User;
import lombok.Data;

@Data
public class AdminOrgPrewView {
    private String id;
    private String name;
    private User director;
    private String inn;

    public AdminOrgPrewView(Organization organization) {
        this.id = organization.getId();
        this.name = organization.getName();
        this.director = organization.getDirector();
        this.inn = organization.getInn();
    }
}
