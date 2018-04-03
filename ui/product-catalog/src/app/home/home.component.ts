import { Component, OnInit, OnChanges, OnDestroy, ViewChild, ElementRef } from '@angular/core';
import { JsonApiService } from "../core/api/json-api.service";
import { FadeInTop } from "../../shared/animations/fade-in-top.decorator";
//import { Color } from 'ng2-charts';
import { BrowserModule } from '@angular/platform-browser';
import { HomeService } from "./home.service";
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
//import { BaseChartDirective } from 'ng2-charts';
import { BaseChartDirective } from 'ng2-charts/ng2-charts';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

// class SharingService {
//   private data1: CustomType1;
//   getChartsData():() => Promise {
//      if(goog.isDef(this.data1)){
//          return Promise.resolve(data1);
//      }
//      return Net.fetch().then(data => {
//          this.data1 = data;
//          return data;
//      });
//   }
// }	


export class HomeComponent implements OnInit, OnDestroy {





  public barChartOptions: any = {
    scaleShowVerticalLines: false,
    responsive: true
  };


  public barChartType: string = 'bar';
  public barChartLegend: boolean = true;


  public offeringsOfSegmentsChartData: any[] = [
    { data: [65, 59, 80], label: 'Available' },
    { data: [28, 48, 40], label: 'Closed' }
  ];
  public offeringsOfSegmentsLabels: string[] = ['Mcare3', 'WEB', 'IVR'];


  public offeringsOfCategoriesChartData: any[] = [
    { data: [65, 59, 80, 65, 59, 80, 65, 59, 80], label: 'Available' },
    { data: [28, 48, 40, 28, 48, 40, 28, 48, 40], label: 'Closed' }
  ];
  public offeringsOfCategoriesLabels: string[] = ['THK', 'FTTH', 'TSOL', 'Fiber', 'ADSL', 'Adsl+Internet', 'Tel', 'OzelKampanyalar', 'Internet'];



  private chartjsData: any[];


  public offeringsSegmentServiceResponse: any;
  public offeringsSegmentData: number[];
  public offeringsSegmentLabels: string[];
  public offeringsSegmentDatasets: any[];



  public dailyOfferingsServiceResponse: any;
  public dailyOfferingsChartData: number[];
  public dailyOfferingsChartLabels: string[];
  public dailyOfferingsChartDataSets: any[];


  public lineChartType: string = 'line';





  public dailyOfferingsChartColors: Array<any> = [
    { // first color
      backgroundColor: 'rgba(255,0,0,0.6)',
      borderColor: 'rgba(255,0,0,0.2)',
      pointBackgroundColor: 'rgba(255,0,0,0.6)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(255,0,0,0.6)'
    }];





  public newBarChartColors: Array<any> = [
    { // first color
      backgroundColor: 'rgba(255,0,0,0.6)',
      borderColor: 'rgba(255,0,0,0.2)',
      pointBackgroundColor: 'rgba(255,0,0,0.6)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(255,0,0,0.6)'
    },
    { // first color
      backgroundColor: 'rgba(0,0,255,0.6)',
      borderColor: 'rgba(0,0,255,0.6)',
      pointBackgroundColor: 'rgba(0,0,255,0.6)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(0,0,255,0.6)'
    }];

  public newPieChartColors: Array<any> = [{
    backgroundColor: ['rgba(255,0,0,0.6)', 'rgba(0,0,255,0.6)', 'rgba(255,51,51,0.6)', 'rgba(255,77,77,0.6)'],
    // borderColor: ['rgba(0,0,0,1)', 'rgba(252,252,252,1)', 'rgba(252,252,252,1)'],       
    // hoverBackgroundColor: 'rgba(0,255,0,0.2)',
    pointHoverBackgroundColor: '#000'
  }];


  public morrisDonutChartColors: string[] =  ['rgba(255,0,0,0.6)', 'rgba(255,26,26,0.6)', 'rgba(255,51,51,0.6)', 'rgba(255,77,77,0.6)', 'rgba(255,100,100,0.6)'];



  barColorsDemo(row, series, type) {
    if (type === 'donut') {
      var red = Math.ceil(150 * row.y / 8);
      return 'rgb(' + red + ',0,0)';
    } else {
      return '#000';
    }
  }



  @ViewChild(BaseChartDirective) chart: BaseChartDirective;


  private updateChart() {
    this.chart.ngOnChanges({});
  }

  constructor(private router: Router, private homeService: HomeService) {


    setTimeout(() => this.ngOnInit(), 1250);

    this.offeringsSegmentData = [12, 17];
    this.offeringsSegmentLabels = ["EBU", "CBU"];

    this.dailyOfferingsChartData = [23, 43, 23, 43, 23, 43, 54];
    this.dailyOfferingsChartLabels = ['27/03', '28/03', '29/03', '30/03', '31/03', '01/04', '01/04'];
    this.dailyOfferingsChartDataSets = [{ data: [23, 43, 23, 43, 23, 43, 54], label: "Offerings" }]


    console.log("constructor girildi");

    this.homeService.getChartsData().subscribe(
      (data) => {

        console.log("getChartsData girildi");

        this.chartjsData = data;

        this.offeringsSegmentServiceResponse = this.chartjsData.filter(function getName(element, index, array) { return (element.name == "dataOfferingsSegment"); });
        this.dailyOfferingsServiceResponse = this.chartjsData.filter(function getName(element, index, array) { return (element.name == "dailyOfferingsChartData"); });


        console.log("getChartsData çıkıldı");
        console.log(this.offeringsSegmentServiceResponse);
      },

      error => console.log("Error: ", error),
      () => { this.ngOnInit(); }

    );
    console.log("constructor'e çıkıldı");
  }



  ngOnChanges() {


    console.log("ngOnChanges'e girildi");
    console.log(this.chartjsData);
    console.log(this.dailyOfferingsServiceResponse);

    console.log(this.dailyOfferingsChartData);
    console.log(this.dailyOfferingsChartLabels);
    this.chart.chart.update();


  }

  ngOnInit() {



    console.log("ngOnInit'e girildi");
    this.offeringsSegmentData = this.offeringsSegmentServiceResponse[0].data;
    this.offeringsSegmentLabels = this.offeringsSegmentServiceResponse[0].labels;
    this.offeringsSegmentDatasets = [
      {
        labels: this.offeringsSegmentLabels,
        data: this.offeringsSegmentData,
        backgroundColor: this.backgroundColorOfferings,
        hoverBackgroundColor: this.hoverBackgroundColorOfferings
      }];


    this.dailyOfferingsChartData = this.dailyOfferingsServiceResponse[0].data;
    this.dailyOfferingsChartLabels = this.dailyOfferingsServiceResponse[0].datalabels;
    this.dailyOfferingsChartDataSets = [{ data: this.dailyOfferingsServiceResponse[0].data, labels: this.dailyOfferingsServiceResponse[0].labels }];

    this.chart.chart.update();
    console.log("ngOnInit'e çıkıldı");

  }

  ngOnDestroy() { }

  //name: string;

  doughnutChartType: string = 'doughnut';
  barChartType2: string = 'bar';

  // asd = this.homeService.getChartsData()
  //   .catch(this.handleError)
  //   .subscribe((data) => {})

  donutdemo = [{ "value": 70, "label": "Internet" }, { "value": 15, "label": "İşOrtağım" }];
  donutLabels = ['Internet', 'İşOrtağım'];

  backgroundColorOfferings: string[] = ["#FF6384", "#36A2EB", "#FFCE56"];
  hoverBackgroundColorOfferings: string[] = ["#FF6384", "#36A2EB", "#FFCE56"];

  dataOfferingsSalesChannel: number[] = [78, 562, 1654];
  labelsOfferingsSalesChannel: string[] = ['Ivr', 'Online', 'Channel'];


  @ViewChild('mycanvas') canvas: ElementRef;
  ctx = this.canvas;

  datasetsOfferingsSalesChannel: any[] = [
    {
      labels: this.dataOfferingsSalesChannel,
      data: this.dataOfferingsSalesChannel,
      backgroundColor: this.backgroundColorOfferings,
      hoverBackgroundColor: this.hoverBackgroundColorOfferings
    }];

  optionsOfferingsSalesChannel = {
    circumference: Math.PI,
    rotation: Math.PI,
    animation: {
      onComplete: function () {
        this.doit(this.ctx);
      }
    }
  }

  dataOfferings: number[] = [51, 498];
  labelsOfferings: string[] = ['Closed', 'Available'];

  datasetsOfferings: any[] = [
    {
      labels: this.labelsOfferings,
      data: this.dataOfferings,
      backgroundColor: this.backgroundColorOfferings,
      hoverBackgroundColor: this.hoverBackgroundColorOfferings
    }];

  public barChartLegend2: boolean = true;

  public barChartData2: any[] = [
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' },
    { data: [28, 48, 40, 19, 86, 27, 90], label: 'Series B' },
    { data: [17, 67, 37, 27, 57, 37, 47], label: 'Series C' }
  ];
  public barChartLabels2: string[] = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'];

  public barChartDataSetsSingleLine: any[] = [
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' },
  ];
  barChartLabelsSingleLine: string[] = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'];



  public chartClicked(e: any): void {
    console.log(e);
  }
  public chartHovered(e: any): void {
    console.log(e);
  }

  private handleError(error: any) {
    let errMsg = (error.message) ? error.message :
      error.status ? `${error.status} - ${error.statusText}` : 'Server error';
    console.error(errMsg); // log to console instead
    return Observable.throw(errMsg);
  }



}
