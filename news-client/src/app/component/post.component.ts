import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NewsService } from '../service/news.service';
import { News } from '../model/models';
import { SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit{
  form!: FormGroup
  tags!: []
  tagsArray!: FormArray

  @ViewChild('image')
  audio!: ElementRef

  imageFile = ""

  constructor(private fb: FormBuilder,
              private service: NewsService) { }

  ngOnInit(): void {
    this.form = this.createForm()
  }

  postNews() {
    let news: News = this.form.value

    const file = this.image.nativeElement.files[0] as File
    this.imageFile = file.name

    news['image'] = this.service.uploadImage(file)
    news['tags'] = this.tags
    console.log(news)
    this.service.postNews(news)
  }

  addTag() {
    // console.log(this.form.get('tag')!.value)
    this.tagsArray.push(this.form.get('tag')!.value)
    // this.tagsArray.push(
    //   this.fb.control<string>('')
    // )
    // console.log(this.tagsArray)
  }

  removeTag(idx: number) {
    this.tagsArray.removeAt(idx)
  }

  createForm() {
    this.tagsArray = this.fb.array([])
    return this.fb.group({
      title: this.fb.control<string>('', [ Validators.required, Validators.minLength(5)]),
      description: this.fb.control<string>(''),
      tags: this.tagsArray
    })
  }
}
