import { Component, OnInit, Input } from '@angular/core';
import { CharacateristicCreateModel } from './CharacateristicCreateModel';

@Component({
  selector: 'app-characteristic-create',
  templateUrl: './characteristic-create.component.html'
})

export class CharacteristicCreateComponent implements OnInit {

  
  characteristic = new CharacateristicCreateModel() ;
  valueTypes: string[] = ["String","List","Integer","Date"];

  ngOnInit() {
  }
  save(characteristic) {
    console.log(characteristic);
  }

}
