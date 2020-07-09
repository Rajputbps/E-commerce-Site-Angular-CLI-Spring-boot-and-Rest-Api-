import { Component, OnInit } from '@angular/core';
import { NavServiceService } from '../service/nav-service.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor( private nav:NavServiceService) { }

  ngOnInit() {
    this.nav.hide();
  }

}
