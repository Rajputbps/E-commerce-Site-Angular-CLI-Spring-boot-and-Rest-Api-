import { Component, OnInit, Input } from '@angular/core';
import { NavServiceService } from '../service/nav-service.service';
import { ApiService } from '../service/api.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  @Input() fieldValue='';

  form:any={};
  passwordModel:any={};
  errorMessage='';
  resetPasswordTokenId:string
 

  constructor(private nav:NavServiceService,private apiservice:ApiService,private route:ActivatedRoute,private router:Router,private authService:AuthService) { }

  ngOnInit() {
    this.nav.hide();
    this.route.params.subscribe(params => {
      this.resetPasswordTokenId = params['resetToken']})
      console.log(this.resetPasswordTokenId)
  }  

  newResetPassword(){
    this.apiservice.newUserResetPassword(this.passwordModel.newpassword,this.resetPasswordTokenId).subscribe()
  }

}
