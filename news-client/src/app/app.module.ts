import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { PostComponent } from './component/post.component';
import { NewsService } from './service/news.service';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { TagsComponent } from './component/tags.component';
import { NewsComponent } from './component/news.component';

@NgModule({
  declarations: [
    AppComponent,
    PostComponent,
    TagsComponent,
    NewsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule

  ],
  providers: [NewsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
