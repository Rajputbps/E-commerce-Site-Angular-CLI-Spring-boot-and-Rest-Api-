import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Users } from '../model/users';
import { Products, Categorys } from '../model/navbar';



 
const httpOptions = {
  headers : new HttpHeaders({ 'Authorization': 'Bearer ' + localStorage.getItem("Access") })
}

@Injectable({
  providedIn: 'root'
})


export class ApiService {

  constructor(private http:HttpClient) { 
    
  }
  //Without login
  baseUrl:string = 'http://localhost:8080/api/auth/';
  //With Login
  mainUrl:string = 'http://localhost:8080/api/';  
  
    
  insertUser(user:Users):Observable<ApiService>{
    return this.http.post<ApiService>(this.baseUrl+'signup',user);
  }
  getUserList():Observable<Users[]>{
    return this.http.get<Users[]>(this.baseUrl+'');
  }
  updateUser(user:Users):Observable<any>{ 
    return this.http.put<ApiService>(this.baseUrl+'updateUser',user);
  }
  getByUserId(userId:number):Observable<Users>{
    return this.http.get<Users>(this.baseUrl+'getByUserId'+userId);
  }
  deleteUser(userId:number):Observable<ApiService[]>{
    return this.http.delete<ApiService[]>(this.baseUrl+'delete'+userId);
  }

  getUserAddress():Observable<any[]>{
    return this.http.get<any[]>(this.mainUrl+'getUserAddress/',httpOptions)
  }
  

  // Category Code

  getCategoryList():Observable<any[]>{
    return this.http.get<any[]>(this.baseUrl+'categoryList');
  }

  getCategoryListById(catId:number):Observable<any[]>{
    return this.http.get<any[]>(this.baseUrl+'getCategoryLisyById/'+catId);
  }

  getProductList(catId:number):Observable<any[]>{
    return this.http.get<any[]>(this.baseUrl+'productList/'+catId);
  }


  addcart(productid:number){
    return this.http.post(this.mainUrl+'addCart/'+productid,httpOptions)
  }

  getCartCount(){
    return this.http.get(this.mainUrl+'getCartCount/',httpOptions)
  }
  getCartList():Observable<any[]>{
    return this.http.get<any[]>(this.mainUrl+'cartList',httpOptions)
  }

 deleteCartItem(cart_id:number){
   return this.http.delete(this.mainUrl+'deleteCartItem/'+cart_id,httpOptions);
 }

  getFullProductList():Observable<any[]>{
    return this.http.get<any[]>(this.baseUrl+'AllProductList/'); 
  }

  searchProduct(fielValue:string):Observable<any[]>{ 
    return this.http.get<any[]>(this.baseUrl+'searchProduct/'+fielValue);
  }

  userProfile():Observable<Users[]>{
    return this.http.get<Users[]>(this.mainUrl+'UserProfile',httpOptions)
  }

  getFullProductDetail(productid:number):Observable<Products>{
    return this.http.get<Products>(this.baseUrl+'fullProductDetail/'+productid)
  }

  resetPasswordEmail(resetEamil:string){
    return this.http.post(this.baseUrl+'resetPassword/'+resetEamil,httpOptions)
  }
  newUserResetPassword(newPassword:string,resetTokentId:string){
    return this.http.post(this.baseUrl+'newPaswsword/'+newPassword+'/' +resetTokentId,httpOptions)
  }

  serchedItems(items:string){
    return this.http.get(this.mainUrl+'searchItems/'+items)
  }
}         
