import { Component, inject, ViewChild } from '@angular/core';
import { PublisherService } from '../../services/publisher.service';
import { Publisher } from '../../model/publisher';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatButtonModule } from '@angular/material/button';
import { RouterLink, RouterOutlet } from '@angular/router';
import { switchMap } from 'rxjs';
import { MatSnackBarModule, MatSnackBar } from '@angular/material/snack-bar';



@Component({
  selector: 'app-publisher',
  imports: [
    MatTableModule,
    MatIconModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    MatPaginatorModule,
    MatSortModule,
    MatButtonModule,
    RouterOutlet,
    RouterLink,
    MatSnackBarModule
    
  ],
  templateUrl: './publisher.component.html',
  styleUrl: './publisher.component.css',
})
export class PublisherComponent {
  //displayedColumns: string[] = ['idPublisher', 'name', 'address'];
  dataSource: MatTableDataSource<Publisher>;
  // publishers: Publisher[];
  columnsDefinitions = [
    { def: 'idPublisher', label: 'idPublisher', hide: true },
    { def: 'name', label: 'name', hide: false },
    { def: 'address', label: 'address', hide: false },
    { def: 'actions', label: 'actions', hide: false },
  ];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private publisherService: PublisherService,
    private snackBar: MatSnackBar

  ){}
  //publisherService = inject(PublisherService);

  ngOnInit(): void {
    // this.publisherService.findAll().subscribe(data => console.log(data));
    // this.publisherService.findAll().subscribe(data => this.publishers = data);
    this.publisherService.findAll().subscribe((data) => {
        this.createTable(data)
    });

  
   this.publisherService.getMessageChange().subscribe(data => {
  this.snackBar.open(data, 'INFO', {
    duration: 2000,
    horizontalPosition: 'right',
    verticalPosition: 'bottom',
  });
});

}

  
//


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
    this.publisherService.delete(id)
    .pipe(switchMap(()=> this.publisherService.findAll()))
    .subscribe(data=>{
      this.publisherService.setPublisherChange(data);
      this.publisherService.setMessageChange('Publisher Delete');
    }) 
  }
}
