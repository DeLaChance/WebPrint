import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { DataTablesModule } from 'angular-datatables';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { FileDropModule } from 'ngx-file-drop';
import { DropzoneModule } from 'ngx-dropzone-wrapper';

import { AppComponent } from './app.component';
import { JoblistComponent } from './joblist/joblist.component';
import { JobactivityComponent } from './jobactivity/jobactivity.component';
import { DraganddropComponent } from './draganddrop/draganddrop.component';
import { OverviewComponent } from './overview/overview.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { RestService } from './rest.service';
import { DataService } from './data.service';
import { StompConfig, StompService } from '@stomp/ng2-stompjs';

import { MatTableModule, MatToolbarModule, MatButtonModule, MatIconModule
  , MatDividerModule, MatDialogModule, MatCardModule } from '@angular/material';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { DragAndDropImporterComponent } from './draganddrop/drag-and-drop-importer.component';
import { DateToAgoPipe } from './jobactivity/date.to.ago.pipe'

const appRoutes: Routes = [
  { path: 'active', component: JobactivityComponent },
  { path: 'list', component: JoblistComponent },
  { path: '', component: OverviewComponent },
  { path: '**', component: PageNotFoundComponent }
];

const stompConfig: StompConfig = {
  // Which server?
  url: 'ws://127.0.0.1:8181/stomp',

  // Headers
  // Typical keys: login, passcode, host
  headers: {
//    login: 'guest',
//    passcode: 'guest'
  },

  // How often to heartbeat?
  // Interval in milliseconds, set to 0 to disable
  heartbeat_in: 0, // Typical value 0 - disabled
  heartbeat_out: 20000, // Typical value 20000 - every 20 seconds

  // Wait in milliseconds before attempting auto reconnect
  // Set to 0 to disable
  // Typical value 5000 (5 seconds)
  reconnect_delay: 5000,

  // Will log diagnostics on console
  debug: true
};

@NgModule({
  declarations: [
    AppComponent,
    JoblistComponent,
    JobactivityComponent,
    DraganddropComponent,
    PageNotFoundComponent,
    OverviewComponent,
    DragAndDropImporterComponent,
    DateToAgoPipe
  ],
  imports: [
    BrowserModule,
    DataTablesModule,
    HttpClientModule,
    FileDropModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    MatDialogModule,
    DropzoneModule,
    MatCardModule,
    MatProgressSpinnerModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    )
  ],
  providers: [ RestService, DataService,
    StompService,
    {
      provide: StompConfig,
      useValue: stompConfig
    }
  ],
  entryComponents: [
    DragAndDropImporterComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
