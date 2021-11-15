import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  favoriteColorControl = new FormControl('');
  
  

  constructor() { }

  ngOnInit(): void {
  }

}
