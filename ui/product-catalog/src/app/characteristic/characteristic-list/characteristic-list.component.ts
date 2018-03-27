import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import { Observable } from "rxjs/Observable";
import { CharacteristicService } from '../characteristic.service';
import {Router, ActivatedRoute} from '@angular/router';
import {DatatableComponent} from '../../shared/ui/datatable/datatable.component';


@Component({
  selector: 'app-characteristic-list',
  templateUrl: './characteristic-list.component.html'
})
export class CharacteristicListComponent implements OnInit {

    reRenderTable: boolean;

    @ViewChild(DatatableComponent) characteristicTable: DatatableComponent;

  options = {
    dom: "Bfrtip",
    ajax: (data, callback, settings) => {
      this.characteristicListService.getAllCharacteristics()
          //.catch(this.handleError)
          .subscribe((data) => {
              console.log("deneme",data);
              callback({
                  aaData: data
              });
          })
    },
    columns: [
        {"data": "id"},
        {"data": "name"},
        {"data": "description"},
        {"data": "validFor.validForStartDate"},
        {"data": "validFor.validForEndDate"},
        {
            render: (data, type, fullRow, meta) => {
                return `
                        <div class='btn-group dropdown show'><button class='btn btn-info btn-sm dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>
                            <i class='fa fa-gear fa-lg'></i></button>
                            <ul class='dropdown-menu  ng-star-inserted'>                                
                                <li><a class='sa-datatables-edit' characteristic-id='${fullRow.id}'>Edit</a></li>
                                <li><a class='sa-datatables-delete' characteristic-id='${fullRow.id}'>Delete</a></li>
                            </ul>
                        </div>`;
            }
        }]
  };

    ngAfterViewInit() {
        document.querySelector('body').addEventListener('click', (event) => {
            let target = <Element>event.target;

            if (target.tagName.toLowerCase() === 'a' && jQuery(target).hasClass('sa-datatables-edit')) {
                this.onEditCharacteristic(target.getAttribute('characteristic-id'));
            }
            if (target.tagName.toLowerCase() === 'a' && jQuery(target).hasClass('sa-datatables-delete')) {
                this.onDeleteCharacteristic(target.getAttribute('characteristic-id'));
            }
        });

    }


    onEditCharacteristic(characteristicId) {
        console.log("Edit characteristic:", characteristicId);
        this.router.navigate(['/characteristic/characteristic-edit/' + characteristicId]);
    }

    onDeleteCharacteristic(characteristicId) {
        console.log("Delete characteristic", characteristicId, "?");
        this.characteristicListService.deleteCharacteristic(characteristicId).subscribe((data) => {
            this.reloadOfferingListTable();
        });
    }

    reloadOfferingListTable() {
        this.reRenderTable = true;
        this.cdRef.detectChanges();
        this.reRenderTable = false;
    }

  ngOnInit() {
  }

  constructor(private router: Router, private characteristicListService: CharacteristicService, private cdRef: ChangeDetectorRef) { }

  /*private handleError(error: any) {
    let errMsg = (error.message) ? error.message :
      error.status ? `${error.status} - ${error.statusText}` : 'Server error';
    console.error(errMsg); // log to console instead
    return Observable.throw(errMsg);
  }*/


}

