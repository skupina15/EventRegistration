package si.fri.rso.skupina15.v1.resources;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.fri.rso.skupina15.beans.CDI.RegistrationBean;
import si.fri.rso.skupina15.beans.config.RestProperties;
import si.fri.rso.skupina15.entities.Registration;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

public class RegistrationResource {
    @Inject
    private RestProperties restProperties;

    private Logger log = Logger.getLogger(RegistrationResource.class.getName());

    @Inject
    private RegistrationBean registrationBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getRegistrations() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        Long count = registrationBean.registrationsCount(query);
        List<Registration> registrations = registrationBean.findAllRegistrations(query);

        return Response.ok(registrations).header("X-Total-Count", count).build();
    }

    @GET
    @Path("{id}")
    public Response returnItems(@PathParam("id") Integer id){
        Registration i = registrationBean.findRegistration(id);
        if (i != null){
            return Response.ok(i).build();
        }
        else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response addItem(Registration i){
        Registration registration = registrationBean.createRegistration(i);
        if(registration == null){
            log.info("Invalid API input.");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.CREATED).entity(registration).build();
    }

    @PUT
    @Path("{id}")
    public Response UpdateItem(@PathParam("id") Integer id, Registration i){
        Registration registration = registrationBean.updateRegistration(id, i);
        if(registration == null){
            log.info("Registration for update does not exist");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.CREATED).entity(registration).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteItem(@PathParam("id") Integer id){
        Integer registration = registrationBean.deleteRegistration(id);
        return Response.status(Response.Status.OK).entity(registration).build();
    }
}
