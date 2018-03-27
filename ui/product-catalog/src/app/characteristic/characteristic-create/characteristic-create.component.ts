import { Component, OnInit, Input } from '@angular/core';
import { CharacteristicCreateModel} from '../model/CharacateristicCreateModel';
import { CharacteristicService } from '../characteristic.service';
import {moment} from 'ngx-bootstrap/chronos/test/chain';
import {Router} from "@angular/router";

@Component({
  selector: 'app-characteristic-create',
  templateUrl: './characteristic-create.component.html'
})

export class CharacteristicCreateComponent implements OnInit {

  model: CharacteristicCreateModel;
  showCharacteristicField: boolean;
  //selectedType = 3;

  constructor(private router: Router, private characteristicService: CharacteristicService) {
    this.model = new CharacteristicCreateModel();
    this.showCharacteristicField = false;
  }

   onChange($event) {
      //this.selectedType = newValue;

      if ($event.target.value == 1)
      {
        this.showCharacteristicField = true;
      }
      else {
        this.showCharacteristicField = false;
      }
   }


  ngOnInit() {

  }

  public onSubmit() {
      //this.model.validForEndDate = moment().format("YYYY-MM-DD");
      //this.model.validForStartDate = moment().format("YYYY-MM-DD");
      this.characteristicService.createCharacteristic(this.model).subscribe(data => {
        this.router.navigate(['/characteristic/characteristic-list']);
    } );
  }
}
