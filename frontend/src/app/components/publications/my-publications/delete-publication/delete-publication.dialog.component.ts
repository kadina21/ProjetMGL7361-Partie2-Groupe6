import { Component, Inject ,OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { NotificationService } from 'src/app/services/notification.service';
import { PublicationService } from 'src/app/services/publication.service';

@Component({
  selector: 'node-delete-publication',
  templateUrl: './delete-publication.dialog.component.html',
  styleUrls: ['./delete-publication.dialog.component.css']
})
export class DeletePublicationDialogComponent implements OnInit {

  errorMessage: any;
  publication: any= {};



   constructor(private publicationService: PublicationService,
               protected notificationService : NotificationService,
               public dialogRef: MatDialogRef<DeletePublicationDialogComponent >,
               @Inject(MAT_DIALOG_DATA) public id: any
               ) { }

   ngOnInit( ): void {
     this.publicationService.getPub(this.id)
     .subscribe(user=>this.publication=user)
   }


   onNoClick(): void {
     this.dialogRef.close();
    }

   confirmDelete(){
     this.publicationService.deletePub(this.id)
     .subscribe(() => {

         this.notificationService.warn('! Deleted successfully');

       }, (err) => {
         this.errorMessage=err;


     });

   }

 }
