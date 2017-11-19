import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { JoblistComponent } from './joblist/joblist.component';
import { JobactivityComponent } from './jobactivity/jobactivity.component';
import { DraganddropComponent } from './draganddrop/draganddrop.component';

@NgModule({
  declarations: [
    AppComponent,
    JoblistComponent,
    JobactivityComponent,
    DraganddropComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
