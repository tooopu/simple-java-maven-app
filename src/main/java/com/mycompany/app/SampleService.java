package com.mycompany.app;

import io.helidon.webserver.Service;
import io.helidon.webserver.Routing;
import io.helidon.webserver.ServerRequest;
import io.helidon.webserver.ServerResponse;

public class SampleService implements Service {

    @Override
    public void update(Routing.Rules rules) {
        rules
            .get("/", this::getSampleHandler);
    }

    private void getSampleHandler(
        ServerRequest request, 
        ServerResponse response
    ) {
        String data = "{ " +
            "status: 'success', " +
            "deploy from: 'server'" +
        "}";
        
        response.status(200);
        response.send(data);
    }
}