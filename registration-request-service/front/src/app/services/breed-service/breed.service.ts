import { BreedResponse } from './breed.response'
import { HttpClient } from '@angular/common/http';
import { Inject } from '@angular/core';
import { Breed } from './breed';
import { Observable, of, map } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root',
})
export class BreedService {

    constructor(
        private http: HttpClient,
        @Inject('BASE_API_URL') private baseUrl: string
    ) { }

    getByName(name: string): Observable<BreedResponse[] | null> {
        //todo убедиться что это норм
        if (!name && name.length < 3) {
            return of<BreedResponse[]>(<BreedResponse[]>[]);
        }
        return this.http.get<BreedResponse[]>(`${this.baseUrl}/breed/search/${name}`);
    }
}