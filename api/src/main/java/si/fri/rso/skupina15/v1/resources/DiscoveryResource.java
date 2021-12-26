package si.fri.rso.skupina15.v1.resources;

import com.kumuluz.ee.discovery.annotations.DiscoverService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("discover_registration")
@ApplicationScoped
public class DiscoveryResource {

    private Logger log = Logger.getLogger(RegistrationResource.class.getName());

    @Inject
    @DiscoverService(value = "event-catalogue-service")
    private WebTarget target;

    @GET
    public Response getEvents() {
        log.info("TULEEEEEE!");
        log.info(target.toString());
        WebTarget service = target.path("v1/events");
        Response response;
        try {
            response = service.request().get();
        } catch (ProcessingException e) {
            return Response.status(408).build();
        }
        return Response.ok(response.readEntity(String.class)).build();
    }

    @GET
    @Path("url")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getUrl() {
        return Response.ok(target.getUri().toString()).build();
    }

//    @GET
//    public Response getProxiedCustomers() {
//        WebTarget service = target.path("v1/customers");
//
//        Response response;
//        try {
//            response = service.request().get();
//        } catch (ProcessingException e) {
//            return Response.status(408).build();
//        }
//
//        ProxiedResponse proxiedResponse = new ProxiedResponse();
//        proxiedResponse.setResponse(response.readEntity(String.class));
//        proxiedResponse.setProxiedFrom(target.getUri().toString());
//
//        return Response.ok(proxiedResponse).build();
//    }

//    @POST
//    public Response addNewCustomer(Event event) {
//        WebTarget service = target.path("v1/customers");
//
//        Response response;
//        try {
//            response = service.request().post(Entity.json(event));
//        } catch (ProcessingException e) {
//            return Response.status(408).build();
//        }
//
//        return Response.fromResponse(response).build();
//    }

}

