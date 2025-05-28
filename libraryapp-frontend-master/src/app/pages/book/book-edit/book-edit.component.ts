import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { BookService } from '../../../services/book.service';
import { Book } from '../../../model/book';
import { switchMap } from 'rxjs';
import { PublisherService } from '../../../services/publisher.service';
import { CategoryService } from '../../../services/category.service';
import { Publisher } from '../../../model/publisher';
import { MatSelectModule } from '@angular/material/select';
import { Category } from '../../../model/category';

@Component({
  selector: 'app-book-edit',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    RouterLink,
    MatSelectModule
  ],
  templateUrl: './book-edit.component.html',
  styleUrls: ['./book-edit.component.css']
})
  export class BookEditComponent implements OnInit {
    form: FormGroup;
    id: number;
    isEdit: boolean;

    constructor(
      private route: ActivatedRoute,
      private bookService: BookService,
      private publisherService: PublisherService,
      private categoryService: CategoryService,
      private router: Router
    ) {}

    publishers: Publisher[] = [];
    categories: Category[] = [];

  ngOnInit(): void {
    this.form = new FormGroup({
      idBook: new FormControl(),
      title: new FormControl(''),
      subtitle: new FormControl(''),
       description: new FormControl(''),
      publisher: new FormControl(), 
      category: new FormControl()   
    });

this.publisherService.findAll().subscribe(data => {
  console.log('Publishers recibidos:', data);
  this.publishers = data;
});

this.categoryService.findAll().subscribe(data => {
  console.log('Categories recibidas:', data);
  this.categories = data;
});
    this.route.params.subscribe(data => {
      this.id = data['id'];
      this.isEdit = this.id != null;
      this.initForm();
    });
  }

  initForm() {
    if (this.isEdit) {
      this.bookService.findById(this.id).subscribe(data => {
      this.form = new FormGroup({
        idBook: new FormControl(),
        title: new FormControl(''),
        subtitle: new FormControl(''),
        description: new FormControl(''),
        publisher: new FormControl(), 
        category: new FormControl()  
      });

      });
    }
  }

  operate() {
    const book: Book = {
      idBook: this.form.value['idBook'],
      title: this.form.value['title'],
      subtitle: this.form.value['subtitle'],
      description: this.form.value['description'],
      publisher: this.form.value['publisher'],
      category: this.form.value['category']
    };


    if (this.isEdit) {
      this.bookService.update(this.id, book).subscribe(() => {
        this.bookService.findAll().subscribe(data => {
          this.bookService.setBookChange(data);
          this.bookService.setMessageChange('Book editado');
        });
      });
    } else {
      this.bookService.save(book)
        .pipe(switchMap(() => this.bookService.findAll()))
        .subscribe(data => {
          this.bookService.setBookChange(data);
          this.bookService.setMessageChange('Book registrado');
        });
    }

    this.router.navigate(['pages/book']);
  }
}
