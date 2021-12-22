import { Injectable, Inject } from '@angular/core';
import { Exhibitions } from './exhibitions.response';
import { HttpClient } from '@angular/common/http';
import { Observable, of, map } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class ExhibitionService {

    constructor(
        private http: HttpClient,
        @Inject('BASE_API_URL') private baseUrl: string
    ) { }

    getActive(): Observable<Exhibitions[]> {
        return this.http.get<Exhibitions[]>(`${this.baseUrl}/exhibitions`);
    }
}