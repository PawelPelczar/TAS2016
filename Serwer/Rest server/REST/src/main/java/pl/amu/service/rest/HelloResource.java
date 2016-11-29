package pl.amu.service.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/hello")
public class HelloResource {

	@GET 
	@Produces("text/plain")
	public String getMsg() {

		return "hello: ";
	}

    @GET 
	@Path("/{name}") 
	@Produces("text/plain")
	public String getMsg(@PathParam("name") String name) {

		return "hello: " + name;
	}
}
