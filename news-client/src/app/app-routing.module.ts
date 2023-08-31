import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PostComponent } from './component/post.component';
import { TagsComponent } from './component/tags.component';
import { NewsComponent } from './component/news.component';

const routes: Routes = [
  {path: '', component: TagsComponent, title: 'Home'},
  {path: 'post', component: PostComponent, title: 'Share News'},
  {path: 'news/:tag' , component: NewsComponent, title: 'List of News'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
