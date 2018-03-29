import { Component, OnInit } from '@angular/core';
import { CreateOfferingModel } from "../model/create-offering-model";
import { OfferingSpecModel } from "../model/offering-spec-model";
import { Catalog } from "../../catalog/model/catalog.model";
import { Router } from "@angular/router";
import { OfferingService } from "../offering.service";
import { CatalogService } from "../../catalog/catalog.service";
import { CharacteristicService } from "../../characteristic/characteristic.service";
import { specificationService } from "../../specification/specification.service";
import { Observable } from "rxjs/Observable";
import { ProdSpecCharValueUseListModel } from "../../characteristic/model/prod-spec-char-value-use-list.model";
import { CategoryService } from '../../category/category.service';
import { Category } from '../../category/category.model';
import { SalesChannel } from '../../sales-channel/detail/sales-channel';
import { SalesChannelService } from '../../sales-channel/sales-channel.service';
import { Segment } from '../../segment/detail/segment';
import { SegmentService } from '../../segment/segment.service';
import { Document } from '../../document/detail/document';
import { DocumentService } from '../../document/document.service';

@Component({
    selector: 'app-offering-edit',
    templateUrl: './offering-edit.component.html',
    styleUrls: ['./offering-edit.component.css'],
})
export class OfferingEditComponent implements OnInit {

    model: CreateOfferingModel;
    spesifications: Array<specificationListModel>=[];
    catalogs: Array<Catalog> = [];
    charValueUseList: Array<ProdSpecCharValueUseListModel> = [];
    categoryLeaves: Array<Category> = [];
    salesChannels: SalesChannel[];
    selectedSalesChannels: SalesChannel[] = [new SalesChannel(),new SalesChannel(),new SalesChannel()];
    segments: Segment[];
    selectedSegments: Segment[] = [new Segment(),new Segment(),new Segment()];
    documents: Document[];
    selectedDocuments: Document[] = [new Document(),new Document(),new Document()];
    constructor(private router: Router,
        private offeringService: OfferingService,
        private catalogService: CatalogService,
        private charService: CharacteristicService,
        private specService: specificationService,
        private categoryService: CategoryService,
        private saleChannelService: SalesChannelService,
        private segmentService: SegmentService,
        private documentService: DocumentService) {
        this.model = new CreateOfferingModel();
    }

    ngOnInit() {
        this.loadSpecs();
        this.loadCatalogs();
        this.loadCategories();
        this.loadSalesChannels();
        this.loadSegments();
        this.loadDocuments();

        let specId = jQuery("#specSelect").val()
        if(specId){
            this.loadCharValueUses(specId);
        }
      
    }

    ngAfterViewInit() {
        var self = this;
        jQuery('#specSelect').on('select2:select', function (e) {
            var data = e.params.data;
            console.log(data.id);
            self.loadCharValueUses(data.id);
        });
    }

    loadSpecs() {
        this.specService.getSpecifications().subscribe((specs) => {
            this.spesifications = specs;
        })
    }

    loadCatalogs() {
        this.catalogService.getCatalogs().subscribe((catalogs) => {
            this.catalogs = catalogs;
        })
    }

    loadCharValueUses(specId) {
        this.charService.getSpecCharValueUses(specId).subscribe((charValuUseList) => {
            this.charValueUseList = charValuUseList;
        })
    }

    loadCategories(){
        this.categoryService.getLeavesFullPathNames().subscribe((categoryLeaves) => {
            this.categoryLeaves = categoryLeaves;
        })
    }
  
    loadSalesChannels(){
        this.selectedSalesChannels[0].id = 30;
        this.selectedSalesChannels[1].id = 21;
        this.selectedSalesChannels[2].id = 23;
        this.saleChannelService.getSalesChannels().subscribe(data => this.salesChannels = data); 
    }
  
    loadSegments(){
        this.selectedSegments[0].id = 3;
        this.segmentService.getSegments().subscribe(data => this.segments = data); 
    }
  
    loadDocuments(){
        this.selectedDocuments[0].id = 1;
        this.documentService.getDocuments().subscribe(data => this.documents = data); 
    }

    public onSubmit() {
        this.model.productSpecificationId = jQuery("#specSelect").val();
        this.model.catalogId = jQuery("#catalogs").val();
        this.offeringService.createOffering(this.model).subscribe(data => {
            this.router.navigate(['/offering/offering-list']);
        });
        this.model.categoryId = jQuery("#categories").val();
    }

    onWizardComplete(data) {
        console.log('fuel-ux wizard complete', data)
    }

    compareIdValues(t1: any, t2: any): boolean {
      return t1 && t2 ? t1.id === t2.id : false ;
    } 
  
}

