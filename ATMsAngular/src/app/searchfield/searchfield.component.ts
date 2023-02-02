import { Component, OnInit, Input } from '@angular/core';
import{ GlobalConstants } from '../config/constants';

@Component({
  selector: 'searchfield',
  templateUrl: './searchfield.component.html',
  styleUrls: ['./searchfield.component.css']
})
export class SearchfieldComponent implements OnInit {

  @Input() callbackFunction?: any

  public searchfield: string = ""

  constructor() {
    this.searchfield = GlobalConstants.CITY_DEMO_DEFAULT
  }

  ngOnInit(): void {
  }

  CallApiService(): void {
    console.log(this.searchfield)
    this.callbackFunction(this.searchfield);
  }

}
