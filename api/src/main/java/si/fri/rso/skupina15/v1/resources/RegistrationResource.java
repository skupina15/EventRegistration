package si.fri.rso.skupina15.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import si.fri.rso.skupina15.beans.CDI.RegistrationBean;
import si.fri.rso.skupina15.beans.config.RestProperties;
import si.fri.rso.skupina15.config.Properties;
import si.fri.rso.skupina15.dtos.NotificationDTO;
import si.fri.rso.skupina15.entities.Registration;
import si.fri.rso.skupina15.services.NotificationApi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.logging.Logger;

@ApplicationScoped
@Path("registrations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegistrationResource {
    @Inject
    private RestProperties restProperties;

    private Logger log = Logger.getLogger(RegistrationResource.class.getName());

    @Inject
    private RegistrationBean registrationBean;

    @Inject
    private Properties properties;

    @Context
    protected UriInfo uriInfo;

    @Inject
    @RestClient
    private NotificationApi notificationApi;

    @GET
    public Response getRegistrations() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        Long count = registrationBean.registrationsCount(query);
        List<Registration> registrations = registrationBean.findAllRegistrations(query);

        return Response.ok(registrations).header("X-Total-Count", count).build();
    }

    @GET
    @Path("{id}")
    public Response getRegistration(@PathParam("id") Integer id){
        Registration i = registrationBean.findRegistration(id);
        if (i != null){
            return Response.ok(i).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response addRegistration(Registration i){
        Registration registration = registrationBean.createRegistration(i);
        if(registration == null){
            log.info("Invalid API input.");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        if(!properties.getMaintenece_mode()){
            log.info("sending");
            // start image processing over async API
            CompletionStage<String> stringCompletionStage =
                    notificationApi.processImageAsynch(new NotificationDTO(i.getEvent().getHost().getEmail(),
                            i.getPersone().getUser_name(), i.getPersone().getEmail(), i.getEvent().getTitle()));

            stringCompletionStage.whenComplete((s, throwable) -> System.out.println(s));
            stringCompletionStage.exceptionally(throwable -> {
                log.severe(throwable.getMessage());
                return throwable.getMessage();
            });
        }

        return Response.status(Response.Status.CREATED).entity(registration).build();
    }

    @PUT
    @Path("{id}")
    public Response UpdateRegistration(@PathParam("id") Integer id, Registration i){
        Registration registration = registrationBean.updateRegistration(id, i);
        if(registration == null){
            log.info("Registration for update does not exist");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.CREATED).entity(registration).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteRegistration(@PathParam("id") Integer id){
        Integer registration = registrationBean.deleteRegistration(id);
        return Response.status(Response.Status.OK).entity(registration).build();
    }
}
