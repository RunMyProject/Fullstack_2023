import { Component, OnInit } from '@angular/core';
import{ GlobalConstants } from './config/constants';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

  title = GlobalConstants.TitleOfSite;

  constructor() {
  }

  ngOnInit() {
  }
}
