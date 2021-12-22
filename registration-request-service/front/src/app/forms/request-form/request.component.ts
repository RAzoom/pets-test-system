import { Component, Inject } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { BreedService } from '../../base-components/breed-service/breed.service'
import { BreedResponse } from '../../base-components/breed-service/breed.response'
import { Observable, distinctUntilChanged, debounceTime, filter, switchMap } from 'rxjs';
import { ExhibitionService } from '../../base-components/exhibitions-service/exhibitions.service';
import { Exhibitions } from '../../base-components/exhibitions-service/exhibitions.response';

@Component({
    selector: 'request-register',
    templateUrl: './request.component.html',
    styleUrls: ['./request.component.scss']
})
export class RequestComponent {
    breed$: Observable<BreedResponse[] | null>;
    exhibitions: Exhibitions[] = [];

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
        private exhibitionService: ExhibitionService
    ) {

        //todo поправить что бы при ответе 400 не валился.
        this.breed$ = this.profileForm.get('breedIdentifier')!.valueChanges.pipe(
            debounceTime(400),
            distinctUntilChanged(),
            filter((name: string) => !!name),
            switchMap((name: string) => this.breedService.getByName(name))
        );
    }

    ngOnInit() {
        this.exhibitionService.getActive().subscribe(
            (el: Exhibitions[]) => this.exhibitions = el
        )
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

    getBreedText(breed: BreedResponse): string {
        return breed.breedRu;
    }

    getExhibitionText(exhibitions: Exhibitions): string {
        return exhibitions.name;
    }

}