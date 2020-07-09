import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class ShareDataService {

  productId;
  etotal:string="jaishreeram"
   

  constructor() { }

  productCheckOut(id:number){
    window.sessionStorage.setItem('product_Id',id.toString());
  }

  cartTotal(id:string,totalCount:string){
    window.sessionStorage.setItem('cartTotal',id)
    window.sessionStorage.setItem('totalcount',totalCount)
  }
  
  cartCount(id:number){
    window.sessionStorage.setItem("proCount",id.toString())
  }
  
}
