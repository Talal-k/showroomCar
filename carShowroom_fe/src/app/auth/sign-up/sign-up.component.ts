import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { UserSignUp } from '../../signUpForm';
import { NgIf } from '@angular/common';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [ReactiveFormsModule,NgIf  ],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {
  signUpForm: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient, private router: Router) {
    this.signUpForm = this.fb.group({
      fullName: ['', [Validators.required]],
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }
  
    // Create HTTP headers

  
  onsubmit() {
    if (this.signUpForm.valid) {
      const userSignUp:UserSignUp = this.signUpForm.value;
      this.http.post<string>('http://localhost:8080/user/register', userSignUp,{responseType:'text'as 'json'}
      )

        .subscribe(
          (response) => {
            console.log(JSON.stringify(response));
            
            localStorage.setItem('token',response)    
            this.router.navigate(['sign-in']);

          }
        ),
     (error: any) => {
          console.error('Registration error:', error);
        }
    }
  }

  navigateToSignIn(){
    this.router.navigate(["sign-in"])
  }

}
