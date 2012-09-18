
$(document).ready(function(){
    $('#btnList').on('click', control.menuListener);
    $('#btnAdd').on('click', control.menuListener);
    $('#btnEdit').on('click', control.menuListener);
    $('#btnDel').on('click', control.menuListener);
    control.init();
})

{
    var table;
}  
var control = (function (){
    
   
    var proxy = new ProductProxy("http://localhost:8080/glassfisk/rs/products");
    var selectedProduct;
    var table;
    
    // Listeners
    return {
        init: function(){ 
            table = new Table(["Id", "Name", "Price"]); 
            table.addHandler(control.tableListener);
            table.setParent('#pageSection');
            var deferred;
            deferred = proxy.getAll();
                deferred.done(function(products){
                    table.clear();
                    table.addRows(products);
                    table.render(); 
                });
        },
        
        menuListener: function(){
            var deferred;
            if( this.id === "btnAdd"){
                var d = new Dialog();
                d.addHandler(control.dialogListener);
                d.setParent("#dialog");
                d.render();
            } else if( this.id === "btnList"){
                deferred = proxy.getAll();
                deferred.done(function(products){
                    table.clear();
                    table.addRows(products);
                    table.render(); 
                });
                deferred.fail(function(){
                    alert("Failed")
                });
            } else if(this.id === "btnEdit"){
                if( ! selectedProduct){
                    return;
                }
                d = new Dialog(selectedProduct);
                d.addHandler(control.dialogListener);
                d.setParent("#dialog");
                d.render();
            } else if( this.id === "btnDel"){
                if( ! selectedProduct){
                    return;
                }
                deferred = proxy.remove(selectedProduct.id);
                deferred.done(function(){
                    selectedProduct = null;
                    alert("Done");
                });
                deferred.fail(function(){
                    alert("Fail");
                });
            }
        }, 
        
        tableListener: function( product ){
            selectedProduct = product;
        },
        
        dialogListener: function(action, data ){
            var deferred;
            if( action === "save"){  // Always = = = (3)
                if(! selectedProduct){
                    deferred = proxy.add(data);
                }
                else{
                    deferred = proxy.edit(data);
                }
                deferred.done(function(){
                    alert("Done");
                });
                deferred.fail(function(){
                    alert("Fail");
                });
            }
        }        
    }       
}()); 
