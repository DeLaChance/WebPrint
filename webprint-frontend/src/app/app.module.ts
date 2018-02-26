import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { DataTablesModule } from 'angular-datatables';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { FileDropModule } from 'ngx-file-drop';

import { AppComponent } from './app.component';
import { JoblistComponent } from './joblist/joblist.component';
import { JobactivityComponent } from './jobactivity/jobactivity.component';
import { DraganddropComponent } from './draganddrop/draganddrop.component';
import { OverviewComponent } from './overview/overview.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

import { RestService } from './rest.service';
import { DataService } from './data.service';

const appRoutes: Routes = [
  { path: 'new', component: DraganddropComponent },
  { path: 'active', component: JobactivityComponent },
  { path: 'list', component: JoblistComponent },
  { path: '', component: OverviewComponent },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    JoblistComponent,
    JobactivityComponent,
    DraganddropComponent,
    PageNotFoundComponent,
    OverviewComponent
  ],
  imports: [
    BrowserModule,
    DataTablesModule,
    HttpClientModule,
    FileDropModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    )
  ],
  providers: [ RestService, DataService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
