import { Component, OnInit, OnChanges, OnDestroy, ViewChild, ElementRef } from '@angular/core';
import { JsonApiService } from "../core/api/json-api.service";
import { FadeInTop } from "../../shared/animations/fade-in-top.decorator";
//import { Color } from 'ng2-charts';
import { BrowserModule } from '@angular/platform-browser';
import { HomeService } from "./home.service";
import { Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { BaseChartDirective } from 'ng2-charts';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit, OnDestroy {

  private chartjsData: any[];


  public dataOfferingsSegment: number[];
  public labelsOfferingsSegment: string[];
  public datasetsOfferingsSegment: any[];




  @ViewChild(BaseChartDirective) chart: BaseChartDirective;

  private updateChart() {
    this.chart.ngOnChanges({});
  }

  constructor(private router: Router, private homeService: HomeService) {


    this.dataOfferingsSegment = [23, 43];
    this.labelsOfferingsSegment = ["EBN", "CBN"];




    this.homeService.getChartsData().subscribe((data) => {
      this.chartjsData = data;


      var asd = this.chartjsData.filter(function getName(element, index, array) { return (element.name == "dataOfferingsSegment"); });

      this.dataOfferingsSegment = asd[0].data;
      this.labelsOfferingsSegment = asd[0].labels;


      this.datasetsOfferingsSegment = [
        {
          labels: this.labelsOfferingsSegment,
          data: this.dataOfferingsSegment,
          backgroundColor: this.backgroundColorOfferings,
          hoverBackgroundColor: this.hoverBackgroundColorOfferings
        }];







    });

  }



  ngOnChanges() { }

  ngOnInit() { }

  ngOnDestroy() { }

  //name: string;

  doughnutChartType: string = 'doughnut';
  barChartType: string = 'bar';

  // asd = this.homeService.getChartsData()
  //   .catch(this.handleError)
  //   .subscribe((data) => {})

  donutdemo = [{"value":70,"label":"foo"},{"value":15,"label":"bar"},{"value":10,"label":"baz"},{"value":5,"label":"A really really long label"}];
  donutLabels = ['foo', 'bar', 'baz', 'A really really long label'];

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

  public doit(ctx) {
    //   Chart.types.Doughnut.prototype.draw.apply(this, arguments);

       var width = this.canvas.nativeElement.clientWidth,
           height = this.canvas.nativeElement.clientHeight;

       var fontSize = (height / 250).toFixed(2);
       ctx.font = fontSize + "em Verdana";
       ctx.textBaseline = "middle";
       ctx.fillStyle = "blue";

       var text = "Pass Rate 82%",
           textX = Math.round((width - ctx.measureText(text).width) / 2),
           textY = height -10;

       ctx.fillText(text, textX, textY);
       ctx.restore();
   }


  dataOfferings: number[] = [51, 498];
  labelsOfferings: string[] = ['Satışa Kapalı', 'Satışa Açık'];

  datasetsOfferings: any[] = [
    {
      labels: this.labelsOfferings,
      data: this.dataOfferings,
      backgroundColor: this.backgroundColorOfferings,
      hoverBackgroundColor: this.hoverBackgroundColorOfferings
    }];




  public barChartLegend: boolean = true;

  public barChartData: any[] = [
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' },
    { data: [28, 48, 40, 19, 86, 27, 90], label: 'Series B' },
    { data: [17, 67, 37, 27, 57, 37, 47], label: 'Series C' }
  ];
  public barChartLabels: string[] = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'];

  public barChartDataSetsSingleLine: any[] = [
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' },
  ];
  barChartLabelsSingleLine: string[] = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'];

  public lineChartDataSet: Array<any> = [
    { data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A' },
    { data: [28, 48, 40, 19, 86, 27, 90], label: 'Series B' }
  ];

  public lineChartLabels: Array<any> = ['January', 'February', 'March', 'April', 'May', 'June', 'July'];
  public lineChartType: string = 'line';

  public chartClicked(e: any): void {
    console.log(e);
  }
  public chartHovered(e: any): void {
    console.log(e);
  }
  public randomize(): void {
    let data = [
      Math.round(Math.random() * 100),
      59,
      80,
      (Math.random() * 100),
      56,
      (Math.random() * 100),
      40];
    let clone = JSON.parse(JSON.stringify(this.barChartData));
    clone[0].data = data;
    this.barChartData = clone;
  }

  // dataOfferingsBar: number[] = [51, 498];
  // labelsOfferingsBar: string[] = ['Satışa Kapalı', 'Satışa Açık'];
  // typeOfferingsBar: string = 'bar';

  // bar_demo: [{ "x": "2011 Q1", "y": 0 }, { "x": "2011 Q2", "y": 1 }, { "x": "2011 Q3", "y": 2 }, { "x": "2011 Q4", "y": 3 }, { "x": "2012 Q1", "y": 4 }, { "x": "2012 Q2", "y": 5 }, { "x": "2012 Q3", "y": 6 }, { "x": "2012 Q4", "y": 7 }, { "x": "2013 Q1", "y": 8 }];

  // datasetsOfferingsBar: any[] = [
  //   {
  //     data: this.bar_demo,
  //     backgroundColor: this.backgroundColorOfferings,
  //     hoverBackgroundColor: this.hoverBackgroundColorOfferings
  //   }];

  // barColorsDemo(row, series, type) {
  //   if (type === 'bar') {
  //     var red = Math.ceil(150 * row.y / 8);
  //     return 'rgb(' + red + ',0,0)';
  //   } else {
  //     return '#000';
  //   }
  // }
  //colorsUndefined: Array<Color>;
  //colorsEmpty: Array<Color> = [];
  // colorsOverride: Array<Color> = [{
  //   backgroundColor: 'green',
  //   hoverBackgroundColor: 'purple'
  // }];
  // colorsEmptyObject: Array<Color> = [{}];

  private handleError(error: any) {
    let errMsg = (error.message) ? error.message :
      error.status ? `${error.status} - ${error.statusText}` : 'Server error';
    console.error(errMsg); // log to console instead
    return Observable.throw(errMsg);
  }

}
