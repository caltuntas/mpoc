import { Component, OnInit, OnDestroy } from '@angular/core';
import { JsonApiService } from "../core/api/json-api.service";
import { FadeInTop } from "../../shared/animations/fade-in-top.decorator";
//import { Color } from 'ng2-charts';
import { BrowserModule } from '@angular/platform-browser';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  public chartjsData: any;

  constructor(private jsonApiService: JsonApiService) { }

  ngOnInit() {
    // this.jsonApiService.fetch('/graphs/chartjs.json').subscribe((data) => {
    //   this.chartjsData = data;
    // })
  }

  ngOnDestroy() { }

  //name: string;

  dataOfferings: number[] = [51, 498];
  labelsOfferings: string[] = ['Satışa Kapalı', 'Satışa Açık'];
  
  dataOfferingsSegment: number[] = [145, 365];
  labelsOfferingsSegment: string[] =['Ebl', 'Cbl'];
  
  dataOfferingsSalesChannel: number[] = [78, 562, 1654];
  labelsOfferingsSalesChannel: string[] = ['Ivr', 'Online', 'Channel']; 

  typeOfferings: string = 'doughnut';

  backgroundColorOfferings : string[] = ["#FF6384","#36A2EB","#FFCE56"];
  hoverBackgroundColorOfferings : string[] = ["#FF6384","#36A2EB","#FFCE56"];

  //colorsUndefined: Array<Color>;
  //colorsEmpty: Array<Color> = [];
  // colorsOverride: Array<Color> = [{
  //   backgroundColor: 'green',
  //   hoverBackgroundColor: 'purple'
  // }];
  // colorsEmptyObject: Array<Color> = [{}];



  datasetsOfferings: any[] = [
    {
      labels: this.labelsOfferings,
      data: this.dataOfferings,
      backgroundColor: this.backgroundColorOfferings,
      hoverBackgroundColor: this.hoverBackgroundColorOfferings
    }];

  datasetsOfferingsSegment: any[] = [
    {
      labels: this.labelsOfferings,
      data: this.dataOfferingsSegment,
      backgroundColor: this.backgroundColorOfferings,
      hoverBackgroundColor: this.hoverBackgroundColorOfferings
    }];

  datasetsOfferingsSalesChannel: any[] = [
    {
      labels: this.labelsOfferings,
      data: this.dataOfferingsSalesChannel,
      backgroundColor: this.backgroundColorOfferings,
      hoverBackgroundColor: this.hoverBackgroundColorOfferings
    }];





}
