import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { News, Tags } from "../model/models";
import { Observable, firstValueFrom } from "rxjs";

@Injectable()
export class NewsService {
  constructor(private http: HttpClient) { }

  postNews(news: News): Promise<any> {
    return firstValueFrom(
      this.http.post<News>('/api/postnews', news)
    ).then(result => console.log(result))
  }

  getTags(): Observable<Tags[]> {
    return this.http.get<Tags[]>('/api/gettags')
  }

  getNews(tag: string): Observable<News[]> {
    return this.http.get<News[]>('api/getnews/'+tag)
  }
}
