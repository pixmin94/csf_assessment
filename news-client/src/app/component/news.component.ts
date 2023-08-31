import { Component, OnInit } from '@angular/core';
import { NewsService } from '../service/news.service';
import { ActivatedRoute } from '@angular/router';
import { News } from '../model/models';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit {
  tag!: string
  news!: News[]

  constructor(private service: NewsService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.tag = this.activatedRoute.snapshot.params['tag']
    this.service.getNews(this.tag).subscribe(news => {
      this.news = news
    })
  }

}
