import { Component,  OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import {first} from 'rxjs/operators';
import { User } from 'src/app/models/users';
import { UsersService } from 'src/app/services/users.service';
import { EditUserDialogComponent } from './edit-user/edit-user.dialog.component';
import { DeleteUserDialogComponent } from './delete-user/delete-user.dialog.component';
import { AddUserDialogComponent } from './add-user/add-user.dialog.component';
import { NotificationService } from 'src/app/services/notification.service';

@Component({
  selector: 'node-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  isPopupOpened = true;
  prenom:any;
  dataUsers:User []=[];
  displayedColumns: string[] = ['id', 'prenom', 'nom', 'emploi', 'domaine','typeUser','email', 'username', 'action'];
 constructor(public dialog: MatDialog,
             private usersService: UsersService,
             protected notificationService : NotificationService) {
             }

ngOnInit(): void {

   this.getUsers();
  }

 search(){
   if(this.prenom==""){
     this.ngOnInit();

   }else{
     this.dataUsers=this.dataUsers.filter(res=>{

      return res.prenom.toLocaleLowerCase().match(this.prenom.toLocaleLowerCase());
     })
   }
 }


getUsers() : void{
  setTimeout(() => {
    this.usersService.getUsers()
    .pipe(first())
    .subscribe(users => this.dataUsers = users );
console.log(this.dataUsers)

  }, 300);

  }

addUser(){
    this.isPopupOpened = true;
        const dialogRef = this.dialog.open(AddUserDialogComponent, {});
    dialogRef.afterClosed().subscribe(result => {
      this.isPopupOpened = false;
      this.getUsers();

    });

  }

editUser(id: string) {
    this.isPopupOpened = true;
     const dialogRef = this.dialog.open(EditUserDialogComponent, {data: id });
   dialogRef.afterClosed().subscribe(result => {
      this.isPopupOpened = false;
      this.getUsers();

    });
  }

  deleteUser(id: string) {
    this.isPopupOpened = true;
        const dialogRef = this.dialog.open(DeleteUserDialogComponent, {data: id });
    dialogRef.afterClosed().subscribe(result => {
      this.isPopupOpened = false;
      this.getUsers();

    });
  }




}














