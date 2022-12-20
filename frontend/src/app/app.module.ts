import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SigninComponent } from './components/auth/signin/signin.component';
import { SignupComponent } from './components/auth/signup/signup.component';
import { HeaderComponent } from './partials/header/header.component';
import { FooterComponent } from './partials/footer/footer.component';
import { NotFoundComponent } from './partials/not-found/not-found.component';
import { MatSidenavModule } from '@angular/material/sidenav';


//users components
import { EditUserDialogComponent } from './components/users/edit-user/edit-user.dialog.component';
import { DeleteUserDialogComponent } from './components/users/delete-user/delete-user.dialog.component';
import { AddUserDialogComponent } from './components/users/add-user/add-user.dialog.component';
import { ReactiveFormsModule } from "@angular/forms";
import { UsersComponent } from './components/users/users.component';
import { FormsModule } from '@angular/forms';

//publications components
//amdmin
import { AllPublicationsComponent } from './components/publications/all-publications/all-publications.component';
//evaluateur
import { ToCorrectPublicationsComponent } from './components/publications/to-correct-publications/to-correct-publications.component';
//auteur
import { AddPublicationDialogComponent } from './components/publications/my-publications/add-publication/add-publication.dialog.component';
import { DeletePublicationDialogComponent } from './components/publications/my-publications/delete-publication/delete-publication.dialog.component';
import { EditPublicationDialogComponent } from './components/publications/my-publications/edit-publication/edit-publication.dialog.component';


//material importations
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import {MatSelectModule} from '@angular/material/select';
import { MatNativeDateModule } from '@angular/material/core';
import { MyPublicatoinsComponent } from './components/publications/my-publications/my-publications.component';
import { CorrectionDetailComponent } from './components/publications/to-correct-publications/correction-detail/correction-detail.component';
import { DetailUserComponent } from './components/users/detail-user/detail-user.component';








@NgModule({
  declarations: [
    AppComponent,
    SigninComponent,
    SignupComponent,
    HeaderComponent,
    FooterComponent,
    NotFoundComponent,

    UsersComponent,
    EditUserDialogComponent,
    DeleteUserDialogComponent,
    AddUserDialogComponent,

    AllPublicationsComponent,
    AddPublicationDialogComponent,
    DeletePublicationDialogComponent,
    EditPublicationDialogComponent,
    MyPublicatoinsComponent,
    ToCorrectPublicationsComponent,
    CorrectionDetailComponent,
    DetailUserComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatButtonModule,
    MatInputModule,
    MatIconModule,
    MatSortModule,
    MatTableModule,
    MatToolbarModule,
    MatPaginatorModule,
    MatDialogModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatGridListModule,
    MatCardModule,
    MatSnackBarModule,
    MatDividerModule,
    MatListModule,
    MatMenuModule,
    MatSidenavModule,
    MatDividerModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    FormsModule,


  ],
  entryComponents: [
    AddUserDialogComponent,
    EditUserDialogComponent,
    DeleteUserDialogComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
