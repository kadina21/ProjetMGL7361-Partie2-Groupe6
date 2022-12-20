import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Route, Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { Publication } from 'src/app/models/publication';
import { NotificationService } from 'src/app/services/notification.service';
import { PublicationService } from 'src/app/services/publication.service';
import { AddPublicationDialogComponent } from './add-publication/add-publication.dialog.component';
import { DeletePublicationDialogComponent } from './delete-publication/delete-publication.dialog.component';
import { EditPublicationDialogComponent } from './edit-publication/edit-publication.dialog.component';

@Component({
  selector: 'node-my-publicatoins',
  templateUrl: './my-publications.component.html',
  styleUrls: ['./my-publications.component.css']
})
export class MyPublicatoinsComponent implements OnInit {

  isPopupOpened = true;
  titre: any;
  dataPublications: Publication []=[];
  displayedColumns: string[] = ['id', 'titre',  'etat', 'categorie','points','comite', 'action'];
  public: any;
 constructor(public dialog: MatDialog,
             private router : Router,
             private publicationService: PublicationService,
             protected notificationService : NotificationService) {
             }

ngOnInit(): void {

   this.getPublications();
  }

 search(){
   if(this.titre==""){
     this.ngOnInit();

   }else{
     this.dataPublications=this.dataPublications.filter(res=>{
       return res.titre.toLocaleLowerCase().match(this.titre.toLocaleLowerCase());
     })
   }
 }


getPublications() : void{
  setTimeout(() => {
    this.publicationService.getPublications()
    .pipe(first())
    .subscribe((publications) => this.dataPublications = publications );
console.log(this.dataPublications)
  }, 300);


  }

getPublication(id : String): void{

  let link = ['home','detailPublication', id];
  this.router.navigate(link);
}

addPublication(){
    this.isPopupOpened = true;
        const dialogRef = this.dialog.open(AddPublicationDialogComponent, {});
    dialogRef.afterClosed().subscribe(result => {
      this.isPopupOpened = false;
      this.getPublications();

    });

  }

editPublication(id: string) {
   this.isPopupOpened = true;
     const dialogRef = this.dialog.open(EditPublicationDialogComponent, {data: id });
   dialogRef.afterClosed().subscribe(result => {
      this.isPopupOpened = false;
      this.getPublications();

    });
  }

  deletePublication(id: string) {
    this.isPopupOpened = true;
        const dialogRef = this.dialog.open(DeletePublicationDialogComponent, {data: id });
    dialogRef.afterClosed().subscribe(result => {
      this.isPopupOpened = false;
      this.getPublications();

    });
  }




}






