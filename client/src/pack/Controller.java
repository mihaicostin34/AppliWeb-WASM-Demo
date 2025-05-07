package pack;

import java.util.Collection;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/")
public interface Controller {

    @GET
    @Path("/ajoutsysinfo")
    public void ajoutInfo(
        @QueryParam("hostname") String hostname, 
        @QueryParam("hostip") String hostip,
        @QueryParam("cpuUsage") float cpuUsage
    );

    @GET
    @Path("/listeinfo")
    @Produces({ "application/json" })
	public Collection<SysInfo> listeInfo();
    
}