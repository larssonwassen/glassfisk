
var ProductProxy = function( baseUri ){
    this.baseUri = baseUri;
}


ProductProxy.prototype = (function (){ 
    

    return {
        
        add: function( product ){
            return $.ajax({
                type: 'POST',
                url: this.baseUri,
                data: product   
            }); 
        },
        
        getByName: function( name ){            
            return $.getJSON(this.baseUri + "/" + name); 
        },
        
        getAll: function() {
            return $.getJSON(this.baseUri);
        },
        
        edit: function ( product ){  
           return $.ajax({
                type: 'PUT',
                url: this.baseUri+ "/" + product.id,
                data: product
            });
        },
        
        remove: function ( id ){ 
            return $.ajax({
                type: 'DELETE',
                url: this.baseUri + "/" + id
            });
        }
    }
}());





