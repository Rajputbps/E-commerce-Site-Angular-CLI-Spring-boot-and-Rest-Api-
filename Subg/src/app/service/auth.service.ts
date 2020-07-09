import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { SignUpInfo } from '../auth/signUpInfo';
import { Observable } from 'rxjs';
import { JwtResponse } from '../auth/jwtResponse';
import { AuthLoginInfo } from '../auth/loginInfo';


const httpOptions={
  headers:new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl='http://localhost:8080/api/auth/signin';
  private signUpUrl='http://localhost:8080/api/auth/signup';

  constructor(private http:HttpClient) { }

  signin(loginInfo:AuthLoginInfo):Observable<JwtResponse>{
    return this.http.post<JwtResponse>(this.loginUrl,loginInfo);
  }

  signup(info:SignUpInfo):Observable<String>{
    return this.http.post<String>(this.signUpUrl,info,httpOptions);
  }
}
