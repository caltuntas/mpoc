import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { CategoryService } from './category.service';
import { Category } from './category.model';
import { OfferingSpecModel } from '../offering/model/offering-spec-model';

@Component({
  selector: 'app-category-create',
  templateUrl: './category-create.component.html'
})
export class CategoryCreateComponent implements OnInit {
  model: Category;
  id: string;
  gunStart: number;
  gunEnd: number;
  parents: Array<OfferingSpecModel> = [
    {"id": "1", "name": "isortagim", "description": "isortagim"},
    {"id": "2", "name": "vodafonenet", "description": "vodafonenet"},
    {"id": "3", "name": "ADSL", "description": "ADSL"}
  ];

  constructor(private route: ActivatedRoute, private router: Router, private service: CategoryService) {
    this.model = new Category();
    this.model.isRoot = false;
  }

  ngOnInit() {/*
    this.id = this.router.snapshot.paramMap.get('id');

    this.service.get(Number(this.id))
      .subscribe(res => {
        console.log(res);
        this.model = res;
      });*/
  }

  onChange($event) { }
  onSubmit() {
    // this.service.create(this.model).subscribe(data => {
    //   console.log('\n', data, '\n\n');
    //   this.router.navigate(['/category/category-list']);
    // });

    //this.service.create(this.model).subscribe();
    //this.router.navigate(['/category/category-list']);
    if (this.route.snapshot.params.id == null) {
      this.service.create(this.model).subscribe();
      console.log("new service called");
    } else {
      this.service.update(this.route.snapshot.params.id, this.model).subscribe();
      console.log("update new service called");
    }
    this.router.navigate(['/category']);
  }



}

