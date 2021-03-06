import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatSliderModule } from '@angular/material/slider';
import { ReactiveFormsModule } from '@angular/forms';
import { environment } from './../environments/environment';
import { HttpClientModule } from '@angular/common/http';
import { RequestComponent } from './forms/request-form/request.component'
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { BreedService } from './services/breed-service/breed.service';
import { ExhibitionService } from './services/exhibitions-service/exhibitions.service';
import { HeaderComponent } from './base-components/header-component/header.component';
import { ExhibitionCardComponent } from './base-components/exhibition-card-component/exhibition-card.component';

@NgModule({
  declarations: [
    AppComponent,
    RequestComponent,
    HeaderComponent,
    ExhibitionCardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatFormFieldModule,
    MatSliderModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatAutocompleteModule,
  ],
  providers: [
    BreedService, 
    ExhibitionService,
    { provide: "BASE_API_URL", useValue: environment.apiUrl }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
