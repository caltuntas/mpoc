import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CharacteristicEditModel} from '../model/characteristicEditModel';
import {CharacteristicService} from '../characteristic.service';
import {moment} from 'ngx-bootstrap/chronos/test/chain';

@Component({
    selector: 'app-characteristic-edit',
    templateUrl: './characteristic-edit.component.html'
})
export class CharacteristicEditComponent implements OnInit {

    characteristicId: string;
    model: CharacteristicEditModel;
    showCharacteristicField: boolean;

    constructor(private route: ActivatedRoute, private router: Router, private characteristicService: CharacteristicService) {
    }

    ngOnInit() {
        this.characteristicId = this.route.snapshot.paramMap.get('characteristicId');

        this.characteristicService.getCharacteristicById(Number(this.characteristicId))
            .subscribe(res => {
                console.log(res);
                this.model = res;
                if (this.model.valueType == 1) {
                    this.showCharacteristicField = true;
                }
            });
    }

    onChange($event) {

        if ($event.target.value == 1) {
            this.showCharacteristicField = true;
        }
        else {
            this.showCharacteristicField = false;
        }
    }

    public onSubmit() {
        if(this.model.valueType == 1)
        {
            this.model.charValueString = jQuery('#charValueString').val();
        } else {
            this.model.charValueString = "";
        }
        this.characteristicService.updateCharacteristic(this.model).subscribe(data => {
            console.log("deneme",this.model);
            this.router.navigate(['/characteristic/characteristic-list']);
        });
    }

}
