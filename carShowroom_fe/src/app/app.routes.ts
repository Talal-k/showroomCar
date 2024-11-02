import { provideRouter, RouterModule, Routes } from '@angular/router';
import { SignInComponent } from './auth/sign-in/sign-in.component';
import { SignUpComponent } from './auth/sign-up/sign-up.component';
import { NgModule } from '@angular/core';
import { HomePageComponent } from './pages/home-page/home-page.component';


export const routes: Routes = [

    { path: 'sign-in', component: SignInComponent },
    { path: 'sign-up', component: SignUpComponent },
    { path: 'home', component: HomePageComponent },
    { path: '', redirectTo: '/sign-in', pathMatch: 'full' },
    // { path: '**', redirectTo: '/sign-in' }

]
export const appRouting = [provideRouter(routes)];
