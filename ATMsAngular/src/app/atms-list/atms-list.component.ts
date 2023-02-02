import { Component, OnInit } from '@angular/core';

import { Observable } from 'rxjs';

import { ATMsService } from '../atms.service';
import { ATM } from '../model/atm';

import{ GlobalConstants } from '../config/constants';

@Component({
  selector: 'app-atms-list',
  templateUrl: './atms-list.component.html',
  styleUrls: ['./atms-list.component.css']
})
export class ATMsListComponent implements OnInit {

  fullTextSearchDefault = GlobalConstants.CITY_DEMO_DEFAULT
  atms$: Observable<ATM[]>

  constructor(private aTMsService: ATMsService) {
    this.atms$ = aTMsService.getSearchResults(this.fullTextSearchDefault)
    this.atms$.subscribe(result => {console.log("result=" + result.length)})
  }

  ngOnInit(): void {}

  public myCallbackFunction = (fullTextSearch: string): void => {

    // callback code here
    //
    this.atms$ = this.aTMsService.getSearchResults(fullTextSearch)
    this.atms$.subscribe(result => {console.log("result=" + result.length)})
  }
}
