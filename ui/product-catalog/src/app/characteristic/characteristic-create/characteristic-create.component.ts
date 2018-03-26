import { Component, OnInit, Input } from '@angular/core';
import { CharacteristicCreateModel} from '../model/CharacateristicCreateModel';
import { CharacteristicService } from '../characteristic.service';
import {moment} from 'ngx-bootstrap/chronos/test/chain';

@Component({
  selector: 'app-characteristic-create',
  templateUrl: './characteristic-create.component.html'
})

export class CharacteristicCreateComponent implements OnInit {

  model: CharacteristicCreateModel;

  constructor(private characteristicService: CharacteristicService) {
    this.model = new CharacteristicCreateModel();
  }

  ngOnInit() {

  }

  public onSubmit() {
      //this.model.validForEndDate = moment().format("YYYY-MM-DD");
      //this.model.validForStartDate = moment().format("YYYY-MM-DD");
      this.characteristicService.createCharacteristic(this.model).subscribe();
  }
}
