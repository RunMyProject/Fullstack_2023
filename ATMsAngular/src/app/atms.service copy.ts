import { Injectable } from '@angular/core';
import { Observable, Subject, ReplaySubject, of } from 'rxjs';
import { delay, map, tap } from 'rxjs/operators';

import { ATM } from './model/atm';
import { MOCK_ATMs } from './MOCK/mock-atms-list';

@Injectable({
  providedIn: 'root'
})

export class ATMsService {

  private atms: ATM[] = []

  private filteredATMs$: Subject<ATM[]> = new ReplaySubject<ATM[]>(1);

  getSearchResults(): Observable<ATM[]> {
    return this.filteredATMs$.asObservable()
  }

  search(searchTerm: string): Observable<void> {
    return this.fetchATMs().pipe(
      tap((atms: ATM[]) => {
        atms = atms.filter(atms => atms.type.toLowerCase().includes(searchTerm))
        this.filteredATMs$.next(atms)
      }),
      map(() => void 0)
    )
  }

  private fetchATMs(): Observable<ATM[]> {

    if (this.atms) {
      return of(this.atms)
    }

    const atms: ATM[] = MOCK_ATMs;

    return of(atms).pipe(
      delay(1000),
      tap((apps: ATM[]) => this.atms = atms)
    )
  }
}
