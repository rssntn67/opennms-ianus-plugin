package org.opennms.plugins.ianus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

public class WebhookHandlerImpl implements WebhookHandler {
    private static final Logger LOG = LoggerFactory.getLogger(WebhookHandlerImpl.class);

    @Override
    public Response ping() {
        LOG.info("ping: got it");
        return Response.ok("pong").build();
    }

}

