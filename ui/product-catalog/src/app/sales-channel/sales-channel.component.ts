import { Component, OnInit } from '@angular/core';
import {SalesChannelService} from './sales-channel.service'
import {Observable} from "rxjs/Observable";
import {SalesChannel} from "./detail/sales-channel"

@Component({
  selector: 'app-salesChannel',
  templateUrl: './sales-channel.component.html',
  styleUrls: ['./sales-channel.component.css']
})
export class SalesChannelComponent implements OnInit {

  public scs:  Array<SalesChannel> = [];
  public options;
  scso: Observable<SalesChannel[]>;
  
  constructor(private scService: SalesChannelService) { }

  ngOnInit() {
    this.getSalesChannels();
    
  }
  
  getSalesChannels() {
        this.scso = this.scService.getSalesChannels();//.publishLast().refCount();
          
          this.scso.subscribe((scs) => {
            this.scs = scs;
            this.options = {
                "data": scs,
                "iDisplayLength": 15,
                "columns": [
                     { "data": "id" },
                      { "data": "code" },
                      { "data": "name" },
                      {"orderable":false,
                        "render": function (data, type, full, meta) {return " <a routerlink='/detail/"+full.id+"' href='#/sales-channel/detail/"+full.id+"' class='btn btn-primary'><i class='fa fa-edit'></i> </a><a (click)='(null)' class='btn btn-danger'><i class='fa fa-remove'></i> </a>";}
                        }
                ],
                "order": [[1, 'asc']]
            }
        });
    }
  
  
//  public options = {
//    //"ajax": {"url": 'http://localhost:8080/saleschannel/getallsaleschannels', "dataSrc": "tableData"},//'/assets/api/tables/salechannels.json',
//    "data" : this.scs,
//    "iDisplayLength": 15,
//    "columns": [
//     { "data": "id" },
//      { "data": "code" },
//      { "data": "description" },
//      {"orderable":false,
//        "render": function (data, type, full, meta) {return " <a routerlink='/detail/"+full.id+"' href='#/sales-channel/detail/"+full.id+"' class='btn btn-primary'><i class='fa fa-edit'></i> </a><a (click)='(null)' class='btn btn-danger'><i class='fa fa-remove'></i> </a>";}
//        }
// 
//    ],
//    "order": [[1, 'asc']]
//  }

  public lastColumn(d) {

    return "";
  }

}
  