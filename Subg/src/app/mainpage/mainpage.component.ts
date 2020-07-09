import { Component, OnInit } from '@angular/core';
import { ApiService } from '../service/api.service';
declare var $:any

@Component({
  selector: 'app-mainpage',
  templateUrl: './mainpage.component.html',
  styleUrls: ['./mainpage.component.css']
})
export class MainpageComponent implements OnInit    {

  constructor(private apiService:ApiService ) { }

  ngOnInit() {

    $(document).ready(function(){
      $('.slider').slider({
        indicators:false
      });
    });
  }

}
