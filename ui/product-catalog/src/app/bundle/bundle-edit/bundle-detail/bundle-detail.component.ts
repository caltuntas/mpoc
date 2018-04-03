import { Component, OnInit, Input } from "@angular/core";
import { BundleModel } from "../../model/bundle-model";
import { BundleService } from "../../bundle.service";
import { SegmentService } from "../../../segment/segment.service";
import { DocumentService } from "../../../document/document.service";
import { SalesChannelService } from "../../../sales-channel/sales-channel.service";
import { CategoryService } from "../../../category/category.service";
import { CatalogService } from "../../../catalog/catalog.service";
import { Catalog } from "../../../catalog/model/catalog.model";
import { Category } from "../../../category/category.model";
import { SalesChannel } from "../../../sales-channel/detail/sales-channel";
import { Segment } from "../../../segment/detail/segment";
import { Document } from "../../../document/detail/document";

@Component({
  selector: "app-bundle-detail",
  templateUrl: "./bundle-detail.component.html"
})
export class BundleDetailComponent implements OnInit {
 
  @Input() model: BundleModel;


  catalogs: Array<Catalog> = [];
  termValues;
  categoryLeaves: Array<Category> = [];
  salesChannelList: SalesChannel[];
  segmentList: Segment[];
  documentList: Document[];

  constructor(
    private bundleService: BundleService,
    private catalogService: CatalogService,
    private categoryService: CategoryService,
    private saleChannelService: SalesChannelService,
    private segmentService: SegmentService,
    private documentService: DocumentService
  ) {}

  ngOnInit() {
    //loading fields of offering for editing
    if (!this.model.isNewbundle) {
      this.bundleService.getOffering(this.model.id).subscribe(offering => {
        this.model = offering;

        if (this.model.catalogId) {
          jQuery("#catalogSelect")
            .val(this.model.catalogId)
            .trigger("change");
        }

        if (this.model.categoryId) {
          jQuery("#categorySelect")
            .val(this.model.categoryId)
            .trigger("change");
        }

        if (this.model.segments && this.model.segments.length > 0) {
          jQuery("#segmentSelect")
            .val(this.model.segments)
            .trigger("change");
        }

        if (this.model.salesChannels && this.model.salesChannels.length > 0) {
          jQuery("#salesChannelSelect")
            .val(this.model.salesChannels)
            .trigger("change");
        }

        if (this.model.documents && this.model.documents.length > 0) {
          jQuery("#documentSelect")
            .val(this.model.documents)
            .trigger("change");
        }
      });
    }

  
    this.loadCatalogs();
    this.loadCategories();
    this.loadSalesChannels();
    this.loadSegments();
  }

  

  loadCatalogs() {
    this.catalogService.getCatalogs().subscribe(catalogs => {
      this.catalogs = catalogs;
    });
  }

  loadCategories() {
    this.categoryService.getLeavesFullPathNames().subscribe(categoryLeaves => {
      this.categoryLeaves = categoryLeaves;
    });
  }

  loadSalesChannels() {
    this.saleChannelService
      .getSalesChannels()
      .subscribe(data => (this.salesChannelList = data));
  }

  loadSegments() {
    this.segmentService
      .getSegments()
      .subscribe(data => (this.segmentList = data));
  }

  loadDocuments() {
    this.documentService
      .getDocuments()
      .subscribe(data => (this.documentList = data));
  }


}
