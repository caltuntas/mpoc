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
            });
    }

}
