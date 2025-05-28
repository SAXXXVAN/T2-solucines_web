import { Component, OnInit, ViewChild } from '@angular/core';
import { BookService } from '../../services/book.service';
import { Book } from '../../model/book';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatIconModule } from '@angular/material/icon';
import {
  MatFormFieldModule
} from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { switchMap } from 'rxjs/operators';
import { MatSnackBarModule, MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatSnackBarModule
  ]
})
export class BookComponent implements OnInit {
  dataSource: MatTableDataSource<Book>;
  
  columnsDefinitions = [
    { def: 'idBook', label: 'idBook', hide: true },
    { def: 'title', label: 'title', hide: false },
    { def: 'subtitle', label: 'subtitle', hide: false },
     { def: 'description', label: 'description', hide: false },
    { def: 'actions', label: 'actions', hide: false },
    
  ];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private bookService: BookService,
    private snackBar: MatSnackBar

  ){}
  //publisherService = inject(PublisherService);

  ngOnInit(): void {
    // this.publisherService.findAll().subscribe(data => console.log(data));
    // this.publisherService.findAll().subscribe(data => this.publishers = data);
    this.bookService.findAll().subscribe((data) => {
        this.createTable(data)
    });

  
   this.bookService.getMessageChange().subscribe(data => {
  this.snackBar.open(data, 'INFO', {
    duration: 2000,
    horizontalPosition: 'right',
    verticalPosition: 'bottom',
  });
});

}


  //Metodo de creación de la tabla
  createTable(data){
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
  }

  getDisplayedColumns() {
    return this.columnsDefinitions.filter((cd) => !cd.hide).map((cd) => cd.def);
  }

  applyFilter(e: any) {
    this.dataSource.filter = e.target.value.trim();
  }


  //metodo para eliminar
  delete(id: number){
    this.bookService.delete(id)
    .pipe(switchMap(()=> this.bookService.findAll()))
    .subscribe(data=>{
      this.bookService.setBookChange(data);
      this.bookService.setMessageChange('Publisher Delete');
    }) 
  }
}
