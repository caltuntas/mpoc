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
  parents: Array<Category>;
  constructor(private route: ActivatedRoute, private router: Router, private service: CategoryService) {
    this.model = new Category();
  }

  ngOnInit() {
    if (this.route.snapshot.params.id) {
      this.service.get(this.route.snapshot.params.id)
        .subscribe(data => this.model = data);
    }
    this.service.getAll().subscribe(data => {
      this.parents = <Array<Category>>data;
    });
  }

  //onChange($event) { }
  onSubmit() {
    this.model.parentId = jQuery("#parentId").val();
    if (this.route.snapshot.params.id == null) {
      this.service.create(this.model).subscribe();
    } else {
      this.service.update(this.route.snapshot.params.id, this.model).subscribe();
    }
    this.router.navigate(['/category']);
  }

}

