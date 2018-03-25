import { Component, OnInit } from '@angular/core';
import { CharacteristicService } from '../characteristic.service';
import { characteristicListModel } from '../model/characteristicListModel';


@Component({
  selector: 'app-characteristic-list',
  templateUrl: './characteristic-list.component.html',
  providers: [CharacteristicService]
})
export class CharacteristicListComponent implements OnInit {

  public _characteristics: characteristicListModel[];

  public options;

  constructor(private characteristicListService: CharacteristicService) { }

  ngOnInit() {
    this.getAllCharacteristics().subscribe(_ => {;});
  }


  getAllCharacteristics(): any {
    this.characteristicListService.getAllCharacteristics()
      .map((productSpecCharacteristics) => {
        this._characteristics = productSpecCharacteristics});
        this.options = {
          "data": this._characteristics,
          "iDisplayLength": 15,
          "columns": [
            // { "data": "userId" },
            // { "data": "id" },
            // { "data": "title" },
            // { "data": "body" }

            { "data" : "id" },
            { "data" : "name" },
            { "data" : "description" }


          ]
      };
  }


  //   getAllCharacteristics() {
  //     this.characteristicListService.getAllCharacteristics().subscribe((characteristicListModel) => {
  //         this._characteristics = characteristicListModel;
  //         this.options = {
  //             "data": this._characteristics,
  //             "iDisplayLength": 15,
  //             "columns": [
  //                 { "data": "id" },
  //                 { "data": "name" },
  //                 { "data": "description" },
  //                 {"orderable":false,
  //                     "defaultContent":"   <a (click)='(null)' class='btn btn-primary'><i class='fa fa-edit'></i> Edit</a>"}

  //             ],
  //             "order": [[1, 'asc']]
  //         }
  //     });
  // }

  // public options = {
  //   "ajax": '/assets/api/tables/datatables.standard.json',
  //   "iDisplayLength": 10,
  //   "columns": [
  //     { "data": "id" },
  //     { "data": "name" },
  //     { "data": "phone" },
  //     { "data": "company" },
  //     { "data": "zip" },
  //     { "data": "city" },
  //     { "data": "date" },
  //     {
  //       "orderable": false,
  //       "defaultContent": "<div class='btn-group dropdown show'><button class='btn btn-info btn-sm dropdown-toggle'"
  //         + " data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"
  //         + "<i class='fa fa-gear fa-lg'></i></button><ul class='dropdown-menu  ng-star-inserted'>"
  //         + "<li><a (click)='(null)'>Detail</a></li>"
  //         + "<li><a (click)='(null)'>Edit</a></li>"
  //         + "<li><a (click)='(null)'>Delete</a></li>"
  //         + "</ul></div>"
  //     }



  //   ],
  //   "order": [[1, 'asc']]
  // }

  public lastColumn(d) {

    return "";
  }




}

