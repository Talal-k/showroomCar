import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ShowRoom } from '../home-page/showroom';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-showroom-form',
  standalone: true,
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './showroom-form.component.html',
  styleUrl: './showroom-form.component.css'
})
export class ShowroomFormComponent implements OnInit {
  @Input() showroom: ShowRoom | undefined; // Input to receive the selected showroom
  showroomForm: FormGroup; // Form group for the form controls
  @Output() flip =  new EventEmitter();
  @Input() isUpdate:boolean = false;
  public token: string | null = null; 
  constructor(private fb: FormBuilder, private router: Router, private http: HttpClient) {
    this.showroomForm = this.fb.group({
      name: ['', Validators.required],
      commercialRegistrationNumber: ['', Validators.required],
      managerName: ['', Validators.required],
      contactNumber: ['', [Validators.required, Validators.pattern('^[0-9]+$')]],
      address: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    // If showroom data is passed, patch the form with this data
    console.log(this.isUpdate);
    this.token = localStorage.getItem('token')
    if (this.showroom) {
      this.showroomForm.patchValue(this.showroom);
    }
  }

  onSubmit(): void {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.token}`); // Use the public token property

    if (this.showroomForm.valid) {
    if(this.isUpdate){
      this.token = localStorage.getItem('token')
      const updatedShowroom: ShowRoom = { ...this.showroom, ...this.showroomForm.value };
      this.http.put("http://localhost:8080/showroom/update", updatedShowroom,{headers}).subscribe(
        res =>{
        this.flip.emit()
        }
      )
    }else{
      this.token = localStorage.getItem('token')
      this.http.post("http://localhost:8080/showroom/add",this.showroomForm.value,{headers})
      .subscribe(
        res => {
          this.flip.emit()
        }
      )
    }
    }
  }

  onCancel(): void {
    this.flip.emit()
  }
}