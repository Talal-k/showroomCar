import { Component } from '@angular/core';
import { routes } from '../../app.routes';
import {  Router, RouterModule,  } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { UserSignIn } from '../../signInForm';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css'
})
export class SignInComponent {
  signInForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) {
    
    this.signInForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }
  onsubmit() {
    if (this.signInForm.valid) {
      const userSignIn:UserSignIn = this.signInForm.value;
      this.http.post<string>('http://localhost:8080/user/sign-in', userSignIn,{responseType:'text'as 'json'}
      )

        .subscribe(
          (response) => {
            console.log(JSON.stringify(response));
            
            localStorage.setItem('token',response)    
            this.router.navigate(['home']);

          }
        ),
     (error: any) => {
          console.error('Registration error:', error);
        }
    }
  }

  navigateToSignUp(){
    this.router.navigate(["sign-up"])
  }

}
