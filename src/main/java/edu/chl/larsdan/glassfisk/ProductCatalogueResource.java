package edu.chl.larsdan.glassfisk;


import edu.chl.hajo.shop.core.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ws.rs.core.*;


/**
 *
 * @author xclose
 */
@Path("/products")
public class ProductCatalogueResource {
    
    private final static Logger log = Logger.getAnonymousLogger();
    private Shop shop = Shop.INSTANCE;
    private IProductCatalogue prodcat = shop.getProductCatalogue();
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
    public void add(@FormParam("id") Long id,
            @FormParam("name") String name, @FormParam("price") Double price) {
        log.log(Level.INFO, "add {0} {1} {2}", new Object[]{id, name, price});
        try {
            prodcat.add(new Product(id, name, price));
        } catch (IllegalArgumentException e) {
            throw new WebApplicationException(e);
        }
    }
    
    @GET
    @Path("{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ProductProxy> getByName(@PathParam("name") String name) {
        List<ProductProxy> found = new ArrayList<>();
        for (ProductProxy p : getAll()) {
            if (p.getName().equals(name)) {
                found.add(p);
            }
        }
        return found;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<ProductProxy> getAll(){
        log.log(Level.INFO, "getAll");
        List<ProductProxy> prodproxycat = new ArrayList<>();
        for(Product p : prodcat.getAll()){
            prodproxycat.add(new ProductProxy(p));
        }
        return prodproxycat;
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void update(@PathParam("id") Long id, @FormParam("name") String name,
            @FormParam("price") Double price) {
        prodcat.update(new Product(id, name, price));
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            prodcat.remove(id);
        } catch (IllegalArgumentException e) {
            throw new WebApplicationException(e);
        }
    }
    
}
