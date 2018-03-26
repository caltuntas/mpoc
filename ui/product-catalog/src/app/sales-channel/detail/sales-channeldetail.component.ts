import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common'; 
import { SalesChannelDetail } from './sales-channeldetail'; 
import {SalesChannelService} from '../sales-channel.service'
import {Observable} from "rxjs/Observable";
import {SalesChannel} from "./sales-channel"



@Component({
  selector: 'app-salesChannelDetail',
  templateUrl: './sales-channeldetail.component.html'
})
export class SalesChannelDetailComponent implements OnInit {
  
  public scDetail: SalesChannelDetail = {NewForm: false, SalesChannelInstance : {id: null, code: null, name: null, description: null}}
  
  constructor(route: ActivatedRoute, private scService: SalesChannelService) { 
    const id = route.snapshot.params.id;
    this.scDetail.NewForm = id == null || id == undefined;
    if(!this.scDetail.NewForm){
      this.scDetail.SalesChannelInstance.id = id;
    }
  }

  ngOnInit() {
    
    if(!this.scDetail.NewForm)
    {
      console.log("before");
      console.log(this.scDetail);
      this.scService.getSalesChannel(this.scDetail.SalesChannelInstance.id).subscribe(details => this.scDetail.SalesChannelInstance = details);
      console.log("after");
      console.log(this.scDetail);
    }
  }

  
  save(detail)
  {
    console.log("form submitted");
    console.log(this.scDetail);
      if(this.scDetail.NewForm)
      {
        this.scService.createSalesChannel(detail.SalesChannelInstance).subscribe();
        console.log("new service called");
      }else
      {
        this.scService.updateSalesChannel(detail.SalesChannelInstance).subscribe();
        console.log("update new service called");
      }
      
  }
  
}



