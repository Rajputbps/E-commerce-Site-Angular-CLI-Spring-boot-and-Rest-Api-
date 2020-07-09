import { Component, OnInit } from '@angular/core';
import { Products } from 'src/app/model/navbar';
import { ApiService } from 'src/app/service/api.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ShareDataService } from 'src/app/service/share-data.service';
import { TokenStorageService } from 'src/app/auth/token-storage.service';
declare var $:any
@Component({
  selector: 'app-product-full-detail',
  templateUrl: './product-full-detail.component.html',
  styleUrls: ['./product-full-detail.component.css']
})
export class ProductFullDetailComponent implements OnInit {

  product=new Products();

  constructor(private apiservice:ApiService,
    private route:ActivatedRoute,
    private router:Router,
    private shareData:ShareDataService,
    private tokenStorage:TokenStorageService) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      let id = params['productId']
      this.apiservice.getFullProductDetail(id).subscribe((data) => this.product=data);
    })
    $(document).ready(function(){
      $('.slider').slider();
      $('.carousel.carousel-slider').carousel({
        fullWidth: true
      })
    })
    
  }

  quantity(e:number){
    console.log(e);
  } 

  buyProduct(productId:number,quantity:number){
    if(quantity==null){
      quantity=1
    }
    if(this.tokenStorage.getToken()){
    this.shareData.productId=productId
    this.shareData.productCheckOut(productId)
    this.router.navigate(['checkout/buy']) 
    }else{
      $('.modal').modal();
    }

  }

}
