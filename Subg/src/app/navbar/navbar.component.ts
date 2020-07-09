import { Component, OnInit, Input, Pipe } from '@angular/core';
import { Categorys, Products, Cart } from '../model/navbar';
import { ApiService } from '../service/api.service';
import { NavServiceService } from '../service/nav-service.service';
import { Users } from '../model/users';
import { SignUpInfo } from '../auth/signUpInfo';
import { AuthService } from '../service/auth.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { AuthLoginInfo } from '../auth/loginInfo';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { ShareDataService } from '../service/share-data.service';
import * as CryptoJS from 'crypto-js';
import { CookieService } from 'ngx-cookie-service';
declare var $:any
 

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
@Input() fieldValue='';


  user:Users  = new Users();
  category:Categorys[];
  productList:Products[];
  fullProductsList:Products[];
  proSearchItem:Categorys[];
  product_name:string;
  form:any={};
  resetemail:any={};
  signUpInfo:SignUpInfo;
  isSignedIn=false;
  isSignedInFailed=false;
  isSignedUp=false;
  isSignUpFailed=false;
  resetEmail=false;
  errorMessage='';
  roles: string[] = [];
  private autherity:String;
  cart:Cart[]=[];
  total:number;
  cartItemCount:number;
  allCookies:any={}
  private logininfo:AuthLoginInfo;
  dsubCategoryId:number
  dropproductList:boolean=false;
  





  constructor(private apiService:ApiService,
    private nav:NavServiceService,
    private authService:AuthService,
    private tokenStorage:TokenStorageService,
    private route:ActivatedRoute,
    private router:Router,
    private shareData:ShareDataService,
    private cookieService: CookieService) {  }

  
  ngOnInit() {
    $(document).ready(function(){
      $(".dropdown-trigger").dropdown({
        inDuration: 300,
            outDuration: 225,
            hover: true, 
            belowOrigin: true, 
            alignment: 'right' 
      });
    });
    this.apiService.getCategoryList().subscribe((data) => this.category=data);
    this.apiService.getFullProductList().subscribe((data) => this.fullProductsList=data)
    if (this.tokenStorage.getToken()) {
      this.isSignedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if(role === 'ROLE_USER'){
          this.autherity = 'user';
           this.apiService.getCartCount().subscribe((data) => this.cartItemCount=+data)
          this.cartList()
          this.cartTotalPrice()
          return true;
        }
      })
    }
  }



  OnSingup(){
    this.signUpInfo = new SignUpInfo(this.form.fname,this.form.lname,this.form.password,this.form.phone,this.form.email);
    this.authService.signup(this.signUpInfo).subscribe((data) => {console.log(data);
    this.isSignedUp=true;
    this.isSignUpFailed=false;
    },
    (error) => {
      this.errorMessage = error.error.message;
      this.isSignUpFailed=true;
    }
      );
      this.user = new Users();
  }




  // Login code
  OnSingin(){
    this.logininfo = new AuthLoginInfo(
      this.form.email,
      this.form.password);
 
    this.authService.signin(this.logininfo).subscribe(
      data => {
        this.tokenStorage.saveToken(data.token);
        this.tokenStorage.saveAuthorities(data.authorities);
        this.isSignedInFailed = false;
        this.isSignedIn = true;
        this.roles = this.tokenStorage.getAuthorities();  
        this.reloadPage()
      },
      error => {
        
        this.errorMessage = error.error.message;
        this.isSignedInFailed = true;
      }
    );
  }


 


  logout(){
    this.tokenStorage.signOut();
    this.reloadPage();
  }
  reloadPage() { 
    window.location.reload();
  }



  getId(userId:number){
    this.apiService.getByUserId(userId).subscribe((data) => {this.user=data});
  }

  delete(userId:number){
    this.apiService.deleteUser(userId).subscribe(() => this.ngOnInit());
  }



  searchProduct(event) { 
    if(event != ""){
    console.log(event);
      this.apiService.searchProduct(event).subscribe((productList) => this.productList=productList);   
    }
    else{
      this.productList=null;
    }
  }

  searchItems(searchedItem:string){
    console.log(searchedItem)
    if(searchedItem !=""){
      //this.apiService.serchedItems(searchedItem).subscribe((data) => this.proSearchItem =data)
    }
  }     
  
  cartList(){
    if (this.tokenStorage.getToken()) {
      this.isSignedIn = true;
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if(role === 'ROLE_USER'){
          this.autherity = 'user';
          this.apiService.getCartList().subscribe((data) => this.cart=data);
          // this.cartItemCount=+window.sessionStorage.getItem("proCount")
          // console.log(this.cartItemCount)
          this.cartTotalPrice();
          return true;
        }
      })
    }
    else{
      this.allCookies = this.cookieService.getAll();
      let cookesLength: Array<string> = document.cookie.split(';')
      console.log(cookesLength.length)
       if(this.allCookies!= null){ 
         for(var i=1;i<=cookesLength.length;i++){
           console.log(this.allCookies.Cookies_Id+i)
         } 
       }
    }

  }  

  cartTotalPrice(){
    this.total=0;
    this.cartItemCount=0;
    for (let obj of this.cart) {
      this.total +=obj.products.product_price;
       this.cartItemCount++
    }
  }


pricedelete(product_price:number){
  this.total=0;
  this.apiService.getCartList().subscribe((data) => this.cart=data);
    for (let obj of this.cart) {
    this.total +=obj.products.product_price;
  }
  this.cartItemCount--;
  this.total= this.total-product_price;
}
  


  deleteCartItem(cart_id:number,product_price:number){
    this.apiService.deleteCartItem(cart_id).subscribe(() => this.pricedelete(product_price));
  }  


  resetPassword(){
    console.log(this.resetemail.resetEmail)
    this.apiService.resetPasswordEmail(this.resetemail.resetEmail).subscribe(
      data=>{this.resetEmail=true},
      error => {
        this.errorMessage = "Your email is not valid !";
        this.resetEmail = false;
      }
    )
    
  }
 
checkout(total:number,cartItemCount:number){
  var totalvalue = CryptoJS.AES.encrypt(JSON.stringify(total),this.shareData.etotal);
  var cartItemCount1 = CryptoJS.AES.encrypt(JSON.stringify(cartItemCount),this.shareData.etotal);
  this.shareData.cartTotal(totalvalue,cartItemCount1)
  this.router.navigate(['checkout/carts/'])
}


productDList(e:number){
  this.dropproductList=true
  this.dsubCategoryId=e
}
 
} 
 