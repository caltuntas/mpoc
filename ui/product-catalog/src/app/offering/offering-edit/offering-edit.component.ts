import {Component, OnInit} from '@angular/core';
import {CreateOfferingModel} from "../model/create-offering-model";
import {OfferingSpecModel} from "../model/offering-spec-model";
import {Catalog} from "../../catalog/model/catalog.model";
import {Router} from "@angular/router";
import {OfferingService} from "../offering.service";
import {CatalogService} from "../../catalog/catalog.service";
import {CharacteristicService} from "../../characteristic/characteristic.service";
import {specificationService} from "../../specification/specification.service";
import {Observable} from "rxjs/Observable";
import {ProdSpecCharValueUseListModel} from "../../characteristic/model/prod-spec-char-value-use-list.model";
import {CategoryService} from '../../category/category.service';
import {Category} from '../../category/category.model';
import {SalesChannel} from '../../sales-channel/detail/sales-channel';
import {SalesChannelService} from '../../sales-channel/sales-channel.service';
import {Segment} from '../../segment/detail/segment';
import {SegmentService} from '../../segment/segment.service';
import {Document} from '../../document/detail/document';
import {DocumentService} from '../../document/document.service';

@Component({
    selector: 'app-offering-edit',
    templateUrl: './offering-edit.component.html',
    styleUrls: ['./offering-edit.component.css'],
})
export class OfferingEditComponent implements OnInit {

    model: CreateOfferingModel;
    isCurrentStepValid: boolean = true;
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
        if (specId) {
            this.loadCharValueUses(specId);
        }
    }

    ngAfterViewInit() {
        var self = this;
        jQuery('#specSelect').on('select2:select', function (e) {
            var data = e.params.data;
            console.log(data.id);
            self.model.productSpecificationId = jQuery("#specSelect").val();
            self.loadCharValueUses(data.id);
        });

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
                isValid = !!(this.model.name && this.model.description)
                break;
            case 2:
                isValid = !!(this.model.productSpecificationId)
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

    loadCategories() {
        this.categoryService.getLeavesFullPathNames().subscribe((categoryLeaves) => {
            this.categoryLeaves = categoryLeaves;
        })
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

    public

    onSubmit() {

    }

    onWizardComplete(data) {
        console.log('fuel-ux wizard complete', data)
        this.model.catalogId = jQuery("#catalogs").val();

        this.model.salesChannels = this.selectedSalesChannels;
        this.model.segments = this.selectedSegments;
        this.model.documents = this.selectedDocuments;

        this.model.categoryId = jQuery("#categories").val();
        this.offeringService.createOffering(this.model).subscribe(data => {
            this.router.navigate(['/offering/offering-list']);
        });

    }

    compareIdValues(t1: any, t2: any): boolean {
        return t1 && t2 ? t1.id === t2.id : false;
    }

}

