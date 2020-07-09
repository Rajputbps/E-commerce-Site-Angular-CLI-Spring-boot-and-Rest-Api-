import { Component, OnInit, Input } from '@angular/core';
import { ApiService } from 'src/app/service/api.service';
import { ShareDataService } from 'src/app/service/share-data.service';
import { Products, Cart } from 'src/app/model/navbar';
import { Address } from 'src/app/model/users';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
import { ActivatedRoute, Router } from '@angular/router';
import * as CryptoJS from 'crypto-js';
import { HttpClient, HttpHeaders } from '@angular/common/http';
declare var $:any

const httpOptions = {
  headers : new HttpHeaders({ 'Authorization': 'Bearer ' + localStorage.getItem("Access") })
}

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})


export class CheckoutComponent implements OnInit {
  @Input() fieldValue='';
  product=new Products();
  address:Address[] 
  selectedAddressId:number
  isSignedIn=false;
  isSignedInFailed=false;
  isSignedUp=false;
  isSignUpFailed=false;
  errorMessage='';
  roles: string[] = [];
  private autherity:String;
  cart:Cart[]=[];
  total:number;
  cartItemCount:number;
  itemrequest:string
  productQuantiti:number=1
  changeProductId:number
  qunatity:number=1
  proPrice:number

  disablePaymentButton: boolean = true;
  public payuform: any = {};
  
  constructor(private apiservice:ApiService,
    private shareData:ShareDataService,
    private tokenStorage:TokenStorageService,
    private route:ActivatedRoute,
    private router:Router,
    private http:HttpClient) { }

  ngOnInit() {

    $(document).ready(function(){
      $('.modal').modal();
    });

    if (!this.tokenStorage.getToken()){
      this.router.navigate(['/'])
    }
    this.route.params.subscribe(params => {
      this.itemrequest = params['carts']  
     this.checkoutProduct()
    })
  } 


  confirmPayment(produtcId:number,addressId:number,amounts:number) {
    const paymentPayload = { 
      productinfo: produtcId,
      amount: amounts
    }
    return this.http.post<any>('http://localhost:8080/api/payment-details', paymentPayload,httpOptions).subscribe(
      data => {
      console.log(data);
      this.payuform.firstname=data.firstname
      this.payuform.email=data.email
      this.payuform.productinfo=data.productinfo
      this.payuform.phone=data.phone
      this.payuform.amount=amounts 
      this.payuform.txnid = data.txnId; 
      this.payuform.surl = data.surl;
      this.payuform.furl = data.furl; 
      this.payuform.key = data.key;
      this.payuform.hash = data.hash;
      this.payuform.txnid = data.txnId;
      this.payuform.service_provider="payu_paisa"; 
      
    }, error1 => {
        console.log(error1);
      })
     
  }

  finalpayment(){
    this.router.navigate(['/finalPage'])
  }

  removeAddress(addId:number){
    this.selectedAddressId=null
  }

  removeProduct(proIf:number){
    
  }
  checkoutProduct(){


      if (this.tokenStorage.getToken()) { 
        if(this.itemrequest == 'carts'){
            this.apiservice.getCartList().subscribe((data) => this.cart=data);
            let cartTotal = window.sessionStorage.getItem("cartTotal")
            let totalcount = window.sessionStorage.getItem("totalcount")
         
            var bytes = CryptoJS.AES.decrypt(cartTotal.toString(),this.shareData.etotal)
            this.total =JSON.parse(bytes.toString(CryptoJS.enc.Utf8));
         
            var totalcout = CryptoJS.AES.decrypt(totalcount.toString(),this.shareData.etotal)
            this.cartItemCount =JSON.parse(totalcout.toString(CryptoJS.enc.Utf8));
            return true;
      }
      if(this.itemrequest == 'buy'){
        let productId = window.sessionStorage.getItem("product_Id")
        this.apiservice.getFullProductDetail(+productId).subscribe((data) => {this.product=data
          // let amount = data.product_price * this.qunatity
          // console.log(amount)
         this.confirmPayment(data.productId,this.selectedAddressId,data.product_price)
        }
        );
      }
    this.apiservice.getUserAddress().subscribe((data) => this.address=data); 
    }


  }
 
  selectedAddress(saddressId:number){
     this.selectedAddressId = saddressId;
  }

  productQuantity(qun:number,type:number){ 
    if(type==1 && 1<qun && qun <= 10){ 
      this.productQuantiti = qun-1
    }else if(type ==2 && qun >= 1 && qun < 10){
      this.productQuantiti = +qun+1
    }  
  }

  quantityIncrese(qub:number,proId:number,price:number){
    console.log(qub + " " + proId)
    this.productQuantiti=qub
    this.proPrice=price
    this.changeProductId = proId
    this.qunatity=qub
  }
  onChange(e:any){
    console.log(e.target.value)
  }


  processToCheckout(produtcId:number,addressId:number,qunatity:number){
    
    this.router.navigate(['/finalPage'])

  }
}
