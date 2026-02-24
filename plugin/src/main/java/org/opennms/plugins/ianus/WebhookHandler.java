package org.opennms.plugins.ianus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("ianus-plugin")
public interface WebhookHandler {

    @GET
    @Path("/ping")
    Response ping();

}
