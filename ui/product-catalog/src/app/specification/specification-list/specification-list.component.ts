import { Component, OnInit } from '@angular/core';
import { SpecificationService } from '../specification.service';

@Component({
  selector: 'app-specification-list',
  templateUrl: './specification-list.component.html'
})
export class SpecificationListComponent implements OnInit {


  constructor(service: SpecificationService) {
    service.getSpecifications();
  }

  ngOnInit() {
  }

  public options = {
    "ajax": '/assets/api/tables/datatables.standard.json',
    "iDisplayLength": 15,
    "columns": [
     { "data": "id" },
      { "data": "name" },
      { "data": "phone" },
      { "data": "company" },
      { "data": "zip" },
      { "data": "city" },
      { "data": "date" },
      {"orderable":false,
        "defaultContent":"   <a (click)='(null)' class='btn btn-primary'><i class='fa fa-edit'></i> Edit</a>"}
 
    ],
    "order": [[1, 'asc']]
  }

  public lastColumn(d) {

    return "";
  }




}

