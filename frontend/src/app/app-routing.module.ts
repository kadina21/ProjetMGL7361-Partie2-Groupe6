import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SigninComponent } from './components/auth/signin/signin.component';
import { SignupComponent } from './components/auth/signup/signup.component';
import { NotFoundComponent } from './partials/not-found/not-found.component';
import { UsersComponent } from './components/users/users.component';
import { AuthGuard } from './components/auth/auth.guard';
import { adminGuard } from './components/auth/adminGuard';
import { MyPublicatoinsComponent } from './components/publications/my-publications/my-publications.component';
import { ToCorrectPublicationsComponent } from './components/publications/to-correct-publications/to-correct-publications.component';
import { evaluateurGuard } from './components/auth/evaluateurGuard';
import { CorrectionDetailComponent } from './components/publications/to-correct-publications/correction-detail/correction-detail.component';






const routes: Routes = [

  {
    path:'home', canActivate: [AuthGuard] ,
    children :[
      {path: 'users', component: UsersComponent, canActivate: [adminGuard] },
      {path: 'articles', component: MyPublicatoinsComponent,  },
      {path: 'correctlist', component : ToCorrectPublicationsComponent, canActivate : [evaluateurGuard]},
      {path: 'correctdetail', component : CorrectionDetailComponent, canActivate : [evaluateurGuard]}

    ]
  },

  {path: 'signup', component: SignupComponent},
  {path: 'signin', component: SigninComponent},
  {path: '**', component: NotFoundComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
