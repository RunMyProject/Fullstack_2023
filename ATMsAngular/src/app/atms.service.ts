import { Injectable } from '@angular/core';

import { HttpClient} from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { delay, tap } from 'rxjs/operators';

import { ATM } from './model/atm';
import { MOCK_ATMs } from './MOCK/mock-atms-list';

import{ GlobalConstants } from './config/constants';

@Injectable({
  providedIn: 'root',
})
export class ATMsService {

  constructor(private httpClient: HttpClient) {}

  getMockResults(): Observable<ATM[]> {
    return of(MOCK_ATMs).pipe(
      delay(1000),
      tap(_ => this.log('fetched MOCK ATMs results')),
    )
  }
  getSearchResults(fullTextSearch: string): Observable<ATM[]> {

    console.log(GlobalConstants.API_FULL_TEXT_SEARCH_ENDPOINT)

    let params = {fullTextSearch: fullTextSearch}

    return this.httpClient.get<ATM[]>(GlobalConstants.API_FULL_TEXT_SEARCH_ENDPOINT, {params: params})
      .pipe(
        delay(1000),
        tap(_ => this.log('fetched ATMs results'))
      )
  }

  private log(message: string) {
    console.log(message)
  }
}
