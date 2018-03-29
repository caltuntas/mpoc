import {Component, OnInit, Input} from '@angular/core';
import {CharacteristicCreateModel} from '../model/characateristicCreateModel';
import {CharacteristicService} from '../characteristic.service';
import {moment} from 'ngx-bootstrap/chronos/test/chain';
import {Router} from '@angular/router';

@Component({
    selector: 'app-characteristic-create',
    templateUrl: './characteristic-create.component.html'
})

export class CharacteristicCreateComponent implements OnInit {

    model: CharacteristicCreateModel;
    showCharacteristicField: boolean;

    constructor(private router: Router, private characteristicService: CharacteristicService) {
        this.model = new CharacteristicCreateModel();
        this.model.valueType = 3;
        this.showCharacteristicField = false;
    }

    onChange($event) {

        if ($event.target.value == 1) {
            this.showCharacteristicField = true;
        }
        else {
            this.showCharacteristicField = false;
        }
    }


    ngOnInit() {
    }

    public onSubmit() {
        this.model.charValueString = jQuery('#charValueString').val();
        this.model.validForStartDate = Date.now().toString();
        this.characteristicService.createCharacteristic(this.model).subscribe(data => {
            console.log("deneme",this.model);
            this.router.navigate(['/characteristic/characteristic-list']);
        });
    }
}
