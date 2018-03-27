import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
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
    gunStart: number;
    gunEnd: number;

    constructor(private route: ActivatedRoute, private characteristicService: CharacteristicService) {
    }

    ngOnInit() {
        this.characteristicId = this.route.snapshot.paramMap.get('characteristicId');

        this.characteristicService.getCharacteristicById(Number(this.characteristicId))
            .subscribe(res => {
                console.log(res);
                this.model = res;
                this.gunStart = Number(this.model.validFor.validForStartDate);
                this.model.validFor.validForStartDate = moment(this.gunStart).format("DD.MM.YYYY");
                this.gunEnd = Number(this.model.validFor.validForEndDate);
                this.model.validFor.validForEndDate = moment(this.gunEnd).format("DD.MM.YYYY");
                console.log("dene1",this.model.validFor.validForStartDate);
                console.log("dene1",this.model.validFor.validForEndDate);
            });
    }

}
