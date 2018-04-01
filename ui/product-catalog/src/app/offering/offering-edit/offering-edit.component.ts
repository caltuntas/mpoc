import {Component, OnInit} from '@angular/core';
import {Catalog} from "../../catalog/model/catalog.model";
import {ActivatedRoute, Router} from "@angular/router";
import {OfferingService} from "../offering.service";
import {CatalogService} from "../../catalog/catalog.service";
import {CharacteristicService} from "../../characteristic/characteristic.service";
import {specificationService} from "../../specification/specification.service";
import {ProdSpecCharValueUseListModel} from "../../characteristic/model/prod-spec-char-value-use-list.model";
import {CategoryService} from '../../category/category.service';
import {Category} from '../../category/category.model';
import {SalesChannel} from '../../sales-channel/detail/sales-channel';
import {SalesChannelService} from '../../sales-channel/sales-channel.service';
import {Segment} from '../../segment/detail/segment';
import {SegmentService} from '../../segment/segment.service';
import {Document} from '../../document/detail/document';
import {DocumentService} from '../../document/document.service';
import {OfferingEditModel} from "../model/offering-edit-model";

@Component({
    selector: 'app-offering-edit',
    templateUrl: './offering-edit.component.html',
    styleUrls: ['./offering-edit.component.css'],
})
export class OfferingEditComponent implements OnInit {

    model: OfferingEditModel;
    isNewOffering: boolean = true;
    spesifications: Array<specificationListModel> = [];
    catalogs: Array<Catalog> = [];
    charValueUseList: Array<ProdSpecCharValueUseListModel> = [];
    categoryLeaves: Array<Category> = [];
    salesChannels: SalesChannel[];
    selectedSalesChannels: SalesChannel[];
    segments: Segment[];
    selectedSegments: Segment[];
    documents: Document[];
    selectedDocuments: Document[];

    constructor(private router: Router,
                private route: ActivatedRoute,
                private offeringService: OfferingService,
                private catalogService: CatalogService,
                private charService: CharacteristicService,
                private specService: specificationService,
                private categoryService: CategoryService,
                private saleChannelService: SalesChannelService,
                private segmentService: SegmentService,
                private documentService: DocumentService) {
        this.model = new OfferingEditModel();
        const idParam = route.snapshot.params.offeringId;
        if (idParam) {
            this.model.id = idParam;
            this.isNewOffering = false;
        }
    }

    ngOnInit() {

        //loading fields of offering for editing
        if (!this.isNewOffering) {
            this.offeringService.getOffering(this.model.id).subscribe((offering) => {
                this.model = offering;

                if (this.model.productSpecificationId) {
                    jQuery("#specSelect").val(this.model.productSpecificationId).trigger('change');
                }

                if (this.model.catalogId) {
                    jQuery("#catalogSelect").val(this.model.catalogId).trigger('change');
                }

                if (this.model.categoryId) {
                    jQuery("#categorySelect").val(this.model.categoryId).trigger('change');
                }

            })
        }

        this.loadSpecs();
        this.loadCatalogs();
        this.loadCategories();
        this.loadSalesChannels();
        this.loadSegments();
        this.loadDocuments();
    }

    ngAfterViewInit() {
        var self = this;

        //Spesification Select
        jQuery('#specSelect').on('select2:select', function (e) {
            var data = e.params.data;
            console.log(data.id);
            self.model.productSpecificationId = jQuery("#specSelect").val();
            self.loadCharValueUses(data.id);
        });
        //Spesification Select

        //Catalog Select
        jQuery('#catalogSelect').on('select2:select', function (e) {
            var data = e.params.data;
            console.log(data.id);
            self.model.catalogId = jQuery("#catalogSelect").val();
        });
        //Catalog Select

        //Wizard Events
        jQuery('#offeringWizard').on('actionclicked.fu.wizard', function (event, data) {

            if (data.direction === 'next') {

                if (!self.validateStep(data.step)) {
                    event.preventDefault();
                }
            }

            console.log(data.direction);
            console.log(data.step);
            console.log(data);
        });
        //Wizard Events
    }

    validateStep(step): boolean {

        let isValid = false;
        switch (step) {
            case 1:
                isValid = !!(this.model.name && this.model.description);
                break;
            case 2:
                isValid = !!(this.model.productSpecificationId);
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                isValid = true;
                break;

        }
        return isValid;
    }


    loadSpecs() {
        this.specService.getSpecifications().subscribe((specs) => {
            this.spesifications = specs;
        });
    }

    loadCatalogs() {
        this.catalogService.getCatalogs().subscribe((catalogs) => {
            this.catalogs = catalogs;
        });
    }

    loadCharValueUses(specId) {
        this.charService.getSpecCharValueUses(specId).subscribe((charValuUseList) => {
            this.charValueUseList = charValuUseList;
        })
    }

    loadCategories() {
        this.categoryService.getLeavesFullPathNames().subscribe((categoryLeaves) => {
            this.categoryLeaves = categoryLeaves;
        });
    }

    loadSalesChannels() {
        this.selectedSalesChannels = this.model.salesChannels;
        this.saleChannelService.getSalesChannels().subscribe(data => this.salesChannels = data);
    }

    loadSegments() {
        this.selectedSegments = this.model.segments;
        this.segmentService.getSegments().subscribe(data => this.segments = data);
    }

    loadDocuments() {
        this.selectedDocuments = this.model.documents;
        this.documentService.getDocuments().subscribe(data => this.documents = data);
    }

    onWizardComplete(data) {
        console.log('fuel-ux wizard complete', data)
        this.model.salesChannels = this.selectedSalesChannels;
        this.model.segments = this.selectedSegments;
        this.model.documents = this.selectedDocuments;
        this.model.categoryId = jQuery("#categories").val();


        console.log(this.isNewOffering);
        if (this.isNewOffering) {
            this.offeringService.createOffering(this.model).subscribe(data => {
                this.router.navigate(['/offering/offering-list']);
            });
        } else {
            this.offeringService.updateOffering(this.model).subscribe(data => {
                this.router.navigate(['/offering/offering-list']);
            });
        }
    }

    compareIdValues(t1: any, t2: any): boolean {
        return t1 && t2 ? t1.id === t2.id : false;
    }

}

