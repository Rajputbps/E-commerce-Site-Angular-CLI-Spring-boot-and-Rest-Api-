

export class Categorys{
    private categoryId:number;
    private categoryName:string;
    private categoryDesc:string;
    private subcategory:Subcategorys[];
}

export class Subcategorys{
    subcategoryId:number;
    categoryId:number;
    subCateName:string;
    subCatDesc:string;
    products:Products[];
}


export class Products{
    productId:number;
    product_name:string;
    product_price:number;
    product_description:string;
    product_unit:number;
    subcategoryId:number;
}

export class Cart{
    cart_id:number;
    user_id:number;
    products:{
        productId:number;
        product_name:string;
        product_price:number;
        product_description:string;
        product_unit:number;
        subcategoryId:number;
    }; 

  
}
export class Images{
    img_id:number;
    img_url:string;
    product_id:number;
}