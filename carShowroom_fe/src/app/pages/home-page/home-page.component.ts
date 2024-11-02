import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { pageResponse } from './PageResponse';
import { CommonModule, NgIf } from '@angular/common';
import { ShowRoom } from './showroom';
import { Router, RouterOutlet } from '@angular/router';
import { ShowroomFormComponent } from '../showroom-form/showroom-form.component';
import { FormsModule } from '@angular/forms';
import { Car } from './car';
import { pageResponseForCars } from './PageResponseForCars';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [CommonModule, RouterOutlet, ShowroomFormComponent,FormsModule],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent implements OnInit {
  showrooms: ShowRoom[] = [];
  selectedShowRoom: ShowRoom | undefined;
  saveMode: boolean = false;
  isUpdate:boolean = false;
  searchId: string = ''; // Variable to hold the search term
  currentPage: number = 0; // Track the current page
  pageSize: number = 5; // Set a default page size
  totalPages: number = 0; 
  cars: Car[] = [];
  currentCarPage: number = 0; // Current page for cars
  totalCarPages: number = 0; // Total pages for cars
  carMakerSearch:String | null = null;
  carShowroomNameSearch :String | null = null;
  public token: string | null = null; 

  constructor(private http:HttpClient, private router:Router) {
 
  }

  ngOnInit(): void {
    this.token = localStorage.getItem('token')
    this.loadShowroom(0,5);
  }

  loadShowroom(pageNumber: number, pageSize: number): void {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.token}`); // Use the public token property
    let pageRequest = {
      pageNumber: pageNumber,
      pageSize: pageSize,
    }
    this.http.post<pageResponse>("http://localhost:8080/showroom/pageable",pageRequest,{headers}).subscribe(
      (response) => {
        this.showrooms = response.content
        this.totalPages = response.totalPages;
      }
     )
  }
  changePage(page: number): void {
    if (page >= 0 && page < this.totalPages) {
      this.currentPage = page; // Update current page
      this.loadShowroom(this.currentPage, this.pageSize); // Load the new page
    }
  }
  selectShowroom(showroom: ShowRoom): void {
    console.log('Selected showroom:', showroom); // Log the selected showroom
    this.selectedShowRoom = showroom; // Set the selected showroom
    this.loadCarsForShowroom(showroom.id); // Load cars for the selected showroom
  }

  loadCarsForShowroom(showroomId: Number): void {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.token}`); // Use the public token property
    let carRequest = {
      showroomId: showroomId,
      pageNumber: this.currentCarPage,
      pageSize: this.pageSize,
      maker:this.carMakerSearch,
      carShowroomName:this.carShowroomNameSearch
    };
    this.http.post<pageResponseForCars>('http://localhost:8080/car/pageable', carRequest,{headers}).subscribe(

      (response) => {
        this.cars = response.content;
        this.totalCarPages = response.totalPages; // Assuming your API returns total pages for cars
      },
      (error) => {
        console.error('Error loading cars:', error);
      }
    );
  }
  changeCarPage(page: number): void {
    if (page >= 0 && page < this.totalCarPages) {
      this.currentCarPage = page; // Update current car page
      this.loadCarsForShowroom(this.selectedShowRoom?.id!); // Load cars for the selected showroom
    }
  }
  flip(){
    this.saveMode = false;
    this.selectedShowRoom = undefined
    this.loadShowroom(0,5)
  }

  addShowroom(): void {
    this.selectedShowRoom = undefined; 
    this.saveMode = true;
    this.isUpdate = false; // Set to false to indicate adding a new showroom
  }

  editShowroom(showroom: ShowRoom): void {
    this.selectedShowRoom = showroom;
    this.saveMode = true;
    this.isUpdate = true;
  }


  deleteShowroom(id: Number): void {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.token}`); // Use the public token property
    this.http.delete(`http://localhost:8080/showroom/delete/${id}`,{headers}).subscribe(() => {
      this.selectedShowRoom = undefined; 
          this.loadShowroom(0,5)

    });
  }
  searchById() {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${this.token}`); // Use the public token property

    this.http.get<ShowRoom>(`http://localhost:8080/showroom/${this.searchId}`,{headers}).subscribe(
      (data) => {
        this.showrooms = [data];
      },
      (error) => {
        console.error('Error fetching showroom:', error);
      }
    );
  }
  searchCars(){
    if(this.selectedShowRoom){
      this.loadCarsForShowroom(this.selectedShowRoom  .id);

    }
  }
  cancelSearch() {
    this.searchId = '';
        this.loadShowroom(0,5)
  }
  returnToShowrooms(): void {
    this.selectedShowRoom = undefined; // Clear the selected showroom
    this.cars = []; // Clear the cars array
    this.currentCarPage = 0; // Reset current car page
    this.loadShowroom(this.currentPage, this.pageSize); // Load the showroom list again
}
}