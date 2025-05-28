// src/app/model/book.ts
import { Publisher } from './publisher';
import { Category } from './category';

export class Book {
  idBook: number;
  title: string;
  subtitle?: string;
  description?: string;
  publisher?: Publisher;
  category?: Category;
}
