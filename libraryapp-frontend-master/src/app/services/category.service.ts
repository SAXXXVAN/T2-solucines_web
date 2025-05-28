// src/app/service/book.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Subject } from 'rxjs';

import { environment } from '../../environments/environment.development';
import { Category } from '../model/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private url: string = `${environment.HOST}/categories`;

  private categoryChange = new Subject<Category[]>();
  private messageChange = new Subject<string>();

  constructor(private http: HttpClient) {}

  findAll(){
    return this.http.get<Category[]>(this.url);
  }

  findById(id: number){
    return this.http.get<Category>(`${this.url}/${id}`);
  }

  save(category: Category){
    return this.http.post(this.url, category);
  }

  update(id: number, category: Category){
    return this.http.put(`${this.url}/${id}`, category);
  }

  delete(id: number){
    return this.http.delete(`${this.url}/${id}`);
  }

  // METODO 
  setCategoryChange(data: Category[]){
    this.categoryChange.next(data);

  }
  getBookChange(){
    return this.categoryChange.asObservable();
  }

  setMessageChange(data: string){
    this.messageChange.next(data);

  }
  getMessageChange(){
    return this.messageChange.asObservable();
  }






}
