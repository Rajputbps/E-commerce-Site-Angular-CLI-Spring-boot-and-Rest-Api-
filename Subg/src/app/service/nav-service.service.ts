import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NavServiceService {

  private visible:boolean;
  constructor() {
    this.visible=true; 
  }
  hide(){
    this.visible=false;
  }
}
