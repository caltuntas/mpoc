import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common'; 
import { SalesChannelDetail } from './sales-channeldetail'; 



@Component({
  selector: 'app-salesChannelDetail',
  templateUrl: './sales-channeldetail.component.html'
})
export class SalesChannelDetailComponent implements OnInit {
  
  scDetail: SalesChannelDetail = {NewForm: false, SalesChannelInstance : null}
  
  constructor(route: ActivatedRoute) { 
    const id = route.snapshot.params.id;
    this.scDetail.NewForm = id == null || id == undefined;
    //alert(id);
  }

  ngOnInit() {
    //alert('2: '+this.scDetail.NewForm);
  }

  
}



