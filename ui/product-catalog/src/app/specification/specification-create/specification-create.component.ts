import { Component, OnInit } from '@angular/core';
import { specificationCreateModel } from './specificationCreateModel';

@Component({
  selector: 'app-specification-create',
  templateUrl: './specification-create.component.html'
})
export class SpecificationCreateComponent implements OnInit {

  isMiddleDivVisible: boolean = false;


  constructor() {
  
  }


  ngOnInit() {
    
 
  }

  submitted = false;

  onSubmit() {
    this.submitted = true;
    console.log('submitted')
  }

}
