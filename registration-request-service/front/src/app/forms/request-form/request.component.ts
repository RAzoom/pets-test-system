import { Component, Inject } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { BreedService } from '../../base-components/breed-service/breed.service'
import { BreedResponse } from '../../base-components/breed-service/breed.response'
import { Observable, distinctUntilChanged, debounceTime, filter, switchMap } from 'rxjs';

@Component({
    selector: 'request-register',
    templateUrl: './request.component.html',
    styleUrls: ['./request.component.scss']
})
export class RequestComponent {
    breed$: Observable<BreedResponse[] | null>;

    profileForm: FormGroup = new FormGroup({
        name: new FormControl('Александр'),
        surname: new FormControl('Пелевин'),
        patronymic: new FormControl('Сергеевич'),
        birthDate: new FormControl('2021-12-14'),
        phone: new FormControl('9998887766'),
        email: new FormControl('test@mail'),
        breedIdentifier: new FormControl('ReFYvakZMQxUkAkfeAv8RA=='),
        exhibitionIdentifier: new FormControl('Y8qHINgHVJ69UySf79h+HQ=='),
        dogName: new FormControl('Кот'),
    });

    constructor(
        private http: HttpClient,
        @Inject('BASE_API_URL') private baseUrl: string,
        private breedService: BreedService,
    ) {
        this.breed$ = this.profileForm.get('breedIdentifier')!.valueChanges.pipe(
            distinctUntilChanged(),
            debounceTime(1000),
            filter((name: string) => !!name),
            switchMap((name: string) => this.breedService.getByName(name))
        );
    }

    sendRequest() {
        console.log("SEND");
        this.http.post(
            this.baseUrl + "/request/create",
            this.profileForm.value
        ).subscribe(data => {
            console.log(data)
        });
    }

    getOptionText(breed: BreedResponse): string {
        return breed.breedRu;
    }



}