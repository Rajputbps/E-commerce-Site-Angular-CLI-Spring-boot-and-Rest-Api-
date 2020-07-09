import { Component, OnInit } from '@angular/core';
import { NavServiceService } from '../service/nav-service.service';
import { HttpClient } from '@angular/common/http';
declare var $:any

@Component({
  selector: 'app-final-page',
  templateUrl: './final-page.component.html',
  styleUrls: ['./final-page.component.css']
})
export class FinalPageComponent implements OnInit {

  public payuform: any = {};
  constructor(private nav:NavServiceService,private http:HttpClient) { }

  ngOnInit() {
    this.nav.hide()
    
  }

  
}
