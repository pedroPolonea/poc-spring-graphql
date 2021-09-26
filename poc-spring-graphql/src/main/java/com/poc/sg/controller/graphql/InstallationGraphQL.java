package com.poc.sg.controller.graphql;

import com.poc.sg.domain.Installation;
import com.poc.sg.service.InstallationService;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InstallationGraphQL implements GraphQLQueryResolver {

    @Autowired
    private InstallationService installationService;

    public List<Installation> getAllInstallation(){
        return installationService.getAll();
    }

}
