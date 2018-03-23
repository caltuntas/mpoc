import { Component, OnInit } from '@angular/core';
import { CharacteristicService } from '../characteristic.service';


@Component({
  selector: 'app-characteristic-list',
  templateUrl: './characteristic-list.component.html'
})
export class CharacteristicListComponent implements OnInit {


  constructor(service: CharacteristicService) {
    service.getCharacteristics();
  }

  ngOnInit() {
  }

  public options = {
    "ajax": '/assets/api/tables/datatables.standard.json',
    "iDisplayLength": 10,
    "columns": [
     { "data": "id" },
      { "data": "name" },
      { "data": "phone" },
      { "data": "company" },
      { "data": "zip" },
      { "data": "city" },
      { "data": "date" },
      {"orderable":false,
        "defaultContent":"<div class='btn-group dropdown show'><button class='btn btn-info btn-sm dropdown-toggle'"
        +" data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"
        +"<i class='fa fa-gear fa-lg'></i></button><ul class='dropdown-menu  ng-star-inserted'>"
        +"<li><a (click)='(null)'>Detail</a></li>"
        +"<li><a (click)='(null)'>Edit</a></li>"
        +"<li><a (click)='(null)'>Delete</a></li>"
        +"</ul></div>"
    }
 

        
    ],
    "order": [[1, 'asc']]
  }

  public lastColumn(d) {

    return "";
  }

  


}

