import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { News } from "../model/models";
import { firstValueFrom } from "rxjs";

@Injectable()
export class NewsService {
  constructor(private http: HttpClient) { }

  postNews(news: News): Promise<any> {
    return firstValueFrom(
      this.http.post<News>('/api/postnews', news)
    ).then(result => console.log(result))
  }
}
