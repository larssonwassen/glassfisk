package edu.chl.larsdan.glassfisk;

import edu.chl.hajo.shop.core.Product;
import javax.xml.bind.annotation.*;

/**
 *
 * @author xclose
 */

@XmlRootElement(name="product")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(name = "Product", propOrder = {
    "id",
    "name",
    "price"
})
public class ProductProxy {
    private Product product;
    
    public ProductProxy(Product product){
        this.product = product;
    }
    
    public ProductProxy(){
    }
    
    public ProductProxy(Long id, String name, Double price){
        this.product = new Product(id,name,price);
    }
    
    public ProductProxy(String name, Double price){
        this.product = new Product(name,price);
    }
    
    @XmlAttribute(required = true)
    public Long getId(){
        return product.getId();
    }
    @XmlElement(required = true)
    public String getName(){
        return product.getName();
    }
    @XmlElement(required = true)
    public Double getPrice(){
        return product.getPrice();
    }
}
