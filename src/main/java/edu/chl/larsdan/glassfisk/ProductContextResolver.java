package edu.chl.larsdan.glassfisk;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.json.JSONJAXBContext;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;


@Provider
public class ProductContextResolver implements ContextResolver<JAXBContext> {

    private JAXBContext context;
    private Class[] types = {ProductProxy.class};

    public ProductContextResolver() throws Exception {
        this.context = new JSONJAXBContext(JSONConfiguration.natural().build(),
                types);
    }

    @Override
    public JAXBContext getContext(Class<?> objectType) {
        return (types[0].equals(objectType)) ? context : null;
    }
}
