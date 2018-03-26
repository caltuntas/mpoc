import { Component, OnInit } from '@angular/core';
import { CreateOfferingModel } from "../model/create-category-model";
import { OfferingService } from "../category-service";
import { Router } from "@angular/router";
import { CategoryService } from '../category.service';
import { Category } from './../model/category.model';
export class CategoryCreateModel {
  public name: string;
  descriptipn: string;
  validForStartDate: Date;
  validForEndDate: Date
}

@Component({
  selector: 'app-category-create',
  templateUrl: './category-create.component.html',
  styleUrls: ['./category-create.component.css']
})
export class OfferingCreateComponent implements OnInit {

  model: Category;

  constructor(private router: Router, private categoryService: CategoryService) {
    this.model = new CreateOfferingModel();
  }

  ngOnInit() {
  }

  public onSubmit() {
    console.log("form submitted");
    console.log(this.model);
    this.categoryService.create(this.model).subscribe(data => {
      this.router.navigate(['/category/category-list']);
    });
  }

}
