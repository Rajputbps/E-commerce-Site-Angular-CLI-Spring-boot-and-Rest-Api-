import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { DetailComponent } from './detail/detail.component';
import { EcommerceComponent } from './admin/ecommerce/ecommerce.component';
import { MordernComponent } from './admin/mordern/mordern.component';
import { UserDetailComponent } from './admin/user-detail/user-detail.component';
import { ProductAllComponent } from './mainpage/product-all/product-all.component';
import { MainpageComponent } from './mainpage/mainpage.component';
import { ProductFullDetailComponent } from './mainpage/product-full-detail/product-full-detail.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { CheckoutComponent } from './mainpage/checkout/checkout.component';
import { FinalPageComponent } from './final-page/final-page.component';

const routes: Routes = [
  
  {path:'admin', component:AdminComponent,children:[
    {path:'', component:MordernComponent },
    {path:'e-com', component:EcommerceComponent },
    {path:'userDetail', component:UserDetailComponent }
  ]
},
  {
    path:'detail',
    component:DetailComponent
  },

  {
    path:'FreshVegetables/:subCatecoryId/cat/:categoryId',
     component:ProductAllComponent
  
  },
  {
    path:'getProduct/:subCatecoryId/:categoryId',
    component:ProductAllComponent}, 
  {
    path:'',
    component:MainpageComponent,
  },
  {
    path:'FreshVegetables/:product_name/:productId',
    component:ProductFullDetailComponent
  },
  {
    path:'Profile',
    component:UserProfileComponent
  },
  {
    path:'passwordReset/:resetToken',
    component:ResetPasswordComponent
  },
  {
    path:'checkout/:carts',
    component:CheckoutComponent
  },
  {
    path:'finalPage',
    component:FinalPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
