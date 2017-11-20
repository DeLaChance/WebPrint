import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { DataTablesModule } from 'angular-datatables';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { JoblistComponent } from './joblist/joblist.component';
import { JobactivityComponent } from './jobactivity/jobactivity.component';
import { DraganddropComponent } from './draganddrop/draganddrop.component';

import { RestService } from './rest.service';

@NgModule({
  declarations: [
    AppComponent,
    JoblistComponent,
    JobactivityComponent,
    DraganddropComponent
  ],
  imports: [
    BrowserModule,
    DataTablesModule,
    HttpClientModule
  ],
  providers: [ RestService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
