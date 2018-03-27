import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../category.service';
import { Router } from '@angular/router';
import { Category } from '../model/category.model';

@Component({
  selector: 'app-category-create',
  templateUrl: './category-create.component.html'
})
export class CategoryCreateComponent implements OnInit {
  model: Category;

  constructor(private router: Router, private service: CategoryService) {
    this.model = new Category();
  }

  ngOnInit() {}

  onChange($event) {  }
  onSubmit() {
    // this.service.create(this.model).subscribe(data => {
    //   console.log('\n', data, '\n\n');
    //   this.router.navigate(['/category/category-list']);
    // });
    
    this.service.create(this.model).subscribe();
    this.router.navigate(['/category/category-list']);
  }

}

