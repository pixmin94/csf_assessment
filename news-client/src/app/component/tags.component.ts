import { NewsService } from './../service/news.service';
import { Component, OnInit } from '@angular/core';
import { Tags } from '../model/models';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tags',
  templateUrl: './tags.component.html',
  styleUrls: ['./tags.component.css']
})
export class TagsComponent implements OnInit{
  tags!: Tags[]

  constructor(private service: NewsService,
              private router: Router) { }

  ngOnInit(): void {
    this.service.getTags().subscribe( tag => {
      this.tags = tag
    })
  }

  getNewsByTag(tag) {
    this.router.navigate(['news', tag])
  }
}
