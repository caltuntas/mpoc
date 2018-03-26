import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs/Observable";
import { CharacteristicService } from '../characteristic.service';


@Component({
  selector: 'app-characteristic-list',
  templateUrl: './characteristic-list.component.html'
})
export class CharacteristicListComponent implements OnInit {

  options = {
    dom: "Bfrtip",
    ajax: (data, callback, settings) => {
      this.characteristicListService.getAllCharacteristics()
        .map((data: any) => (data.data || data))
        .catch(this.handleError)
        .subscribe((data) => {
          callback({
            aaData: data.slice(0, 100)
          })
        })
    },
    columns: [
      { "data": "id" },
      { "data": "name" },
      { "data": "description" },
      {
        "orderable": false,
        "defaultContent": "<div class='btn-group dropdown show'><button class='btn btn-info btn-sm dropdown-toggle'"
          + " data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"
          + "<i class='fa fa-gear fa-lg'></i></button><ul class='dropdown-menu  ng-star-inserted'>"
          + "<li><a (click)='(null)'>Detail</a></li>"
          + "<li><a (click)='(null)'>Edit</a></li>"
          + "<li><a (click)='(null)'>Delete</a></li>"
          + "</ul></div>"
      }
    ]
  };

  ngOnInit() {
  }

  constructor(private characteristicListService: CharacteristicService) { }

  private handleError(error: any) {
    let errMsg = (error.message) ? error.message :
      error.status ? `${error.status} - ${error.statusText}` : 'Server error';
    console.error(errMsg); // log to console instead
    return Observable.throw(errMsg);
  }


}

