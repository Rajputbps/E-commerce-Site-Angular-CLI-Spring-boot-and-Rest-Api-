import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HttpClientModule } from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { from } from 'rxjs';
import { AdminComponent } from './admin/admin.component';
import { DetailComponent } from './detail/detail.component';
import { MordernComponent } from './admin/mordern/mordern.component';
import { EcommerceComponent } from './admin/ecommerce/ecommerce.component';
import { UserDetailComponent } from './admin/user-detail/user-detail.component';
import { AuthInterceptor, httpInterceptorProviders } from './auth/auth-interceptor';
import { MainpageComponent } from './mainpage/mainpage.component';
import { FooterComponent } from './footer/footer.component';
import { ProductAllComponent } from './mainpage/product-all/product-all.component';
import { ProductFullDetailComponent } from './mainpage/product-full-detail/product-full-detail.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { CheckoutComponent } from './mainpage/checkout/checkout.component'; 
import { CookieService } from 'ngx-cookie-service';
import { FinalPageComponent } from './final-page/final-page.component';  


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    NavbarComponent,
    AdminComponent,
    DetailComponent,
    MordernComponent,
    EcommerceComponent,
    UserDetailComponent,
    MainpageComponent,
    FooterComponent,
    ProductAllComponent,
    ProductFullDetailComponent,
    UserProfileComponent,
    ResetPasswordComponent,
    CheckoutComponent,
    FinalPageComponent 
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [httpInterceptorProviders,CookieService], 
  bootstrap: [AppComponent]
})
export class AppModule { 
  
}
