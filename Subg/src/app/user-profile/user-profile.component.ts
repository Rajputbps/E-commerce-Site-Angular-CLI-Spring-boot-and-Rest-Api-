import { Component, OnInit } from '@angular/core';
import { Users } from '../model/users';
import { ApiService } from '../service/api.service';
import { HttpClient } from '@angular/common/http';
declare var $:any
@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  userData:Users[];
  constructor(private apiservice:ApiService,private http:HttpClient) { }

  ngOnInit() {
    $(function(){
      $('.tabs').tabs({
        swipeable:true
      });
    });
    this.apiservice.userProfile().subscribe((data) => this.userData=data)
  }

}
