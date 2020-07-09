import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ApiService } from 'src/app/service/api.service';
import { Products, Cart, Categorys } from 'src/app/model/navbar';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { AuthService } from 'src/app/service/auth.service';
import { CookieService } from 'ngx-cookie-service';
import { ShareDataService } from 'src/app/service/share-data.service';
declare var $:any
@Component({
  selector: 'app-product-all',
  templateUrl: './product-all.component.html',
  styleUrls: ['./product-all.component.css']
})
export class ProductAllComponent implements OnInit {

  jquery_code(){
    $(document).ready(function(){
      $('.modal-trigger').leanModal();
    });
  }

  product:Products[]=[];
  category:Categorys[]=[];
  isSignedIn=false;
  errorMessage='';
  roles: string[] = [];
  cart:Cart[]=[];
  p_range1:number=0 
  p_range2:number=0
  value:boolean=false
  private autherity:String;
  cartItemCount:number

  constructor(
    private apiservice:ApiService,
    private route:ActivatedRoute,
    private router:Router,
    private authService:AuthService,
    private tokenStorage:TokenStorageService,
    private cookieService: CookieService,
    private shareDataService:ShareDataService) { }


  


  ngOnInit() {
    $(document).ready(function(){
      $('.modal-trigger').leanModal();
      $('.slider').slider({
        indicators:false
      });
      
    });
    
    this.route.params.subscribe(params => {
      let subid = params['subCatecoryId']
      let catid = params['categoryId']
      this.apiservice.getCategoryListById(catid).subscribe((data) =>this.category=data)
      this.apiservice.getProductList(subid).subscribe((data) => this.product=data);
    })
    if (this.tokenStorage.getToken()) {
      this.isSignedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if(role === 'ROLE_USER'){
          this.autherity = 'user';
          this.apiservice.getCartList().subscribe((data) => this.cart=data); 
          this.apiservice.getCartCount().subscribe((data) => this.cartItemCount= +data)
          return true;
        }
      })
    }
  }

  cartCont(){
    this.apiservice.getCartCount().subscribe((data) => this.cartItemCount= +data)
    this.shareDataService.cartCount(this.cartItemCount+1)
      console.log(this.cartItemCount+1)
  }

  addCart(productId:number){

    if (this.tokenStorage.getToken()) {
      this.apiservice.addcart(productId).subscribe(()=>this.cartCont())
    }
    else{
      let cookesLength: Array<string> = document.cookie.split(';')
      console.log(cookesLength.length)
      if(cookesLength.length <= 0){
        this.cookieService.set("Cookies_Id1",productId.toString()) 
      }else{
        var id=cookesLength.length
        id++
        this.cookieService.set("Cookies_Id"+id.toString(),productId.toString())
      }
    }
   
  }
 
  priceRange(n1:number,n2:number){
    console.log(n1+"-----------"+ n2)
    console.log(this.p_range1+" >>>>>>>>>>>> " + this.p_range2) 


    if(n1<=this.p_range1)

    for(let pricepp of this.product){
     // if( n1<=this.p_range1 && n2>=this.p_range2){
        
        if(pricepp.product_price>= n1 && pricepp.product_price<=n2){
          console.log(pricepp.product_name+ "  " + pricepp.product_price)
        }
      }
   // }
    this.p_range1=n1 
    this.p_range2=n2
    console.log(this.p_range1+"---------> " + this.p_range2) 

  }

}
