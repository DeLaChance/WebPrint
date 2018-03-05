webpackJsonp(["main"],{

/***/ "../../../../../src/$$_lazy_route_resource lazy recursive":
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncatched exception popping up in devtools
	return Promise.resolve().then(function() {
		throw new Error("Cannot find module '" + req + "'.");
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "../../../../../src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "../../../../../src/app/app.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/app.component.html":
/***/ (function(module, exports) {

module.exports = "<mat-toolbar color=\"primary\">\n  <mat-icon svgIcon=\"print\"></mat-icon>\n  <a mat-button [routerLink]=\"'/'\" routerLinkActive=\"selected-nav\">Webprint</a>\n</mat-toolbar>\n\n<router-outlet></router-outlet>\n"

/***/ }),

/***/ "../../../../../src/app/app.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_material__ = __webpack_require__("../../../material/esm5/material.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_platform_browser__ = __webpack_require__("../../../platform-browser/esm5/platform-browser.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var AppComponent = (function () {
    function AppComponent(iconRegistry, sanitizer) {
        this.title = 'WebPrinter';
        iconRegistry.addSvgIcon('print', sanitizer.bypassSecurityTrustResourceUrl('assets/img/ic_print_white_24px.svg'));
        iconRegistry.addSvgIcon('add', sanitizer.bypassSecurityTrustResourceUrl('assets/img/ic_add_white_24px.svg'));
    }
    AppComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-root',
            template: __webpack_require__("../../../../../src/app/app.component.html"),
            styles: [__webpack_require__("../../../../../src/app/app.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_material__["g" /* MatIconRegistry */], __WEBPACK_IMPORTED_MODULE_2__angular_platform_browser__["c" /* DomSanitizer */]])
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "../../../../../src/app/app.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__("../../../platform-browser/esm5/platform-browser.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_angular_datatables__ = __webpack_require__("../../../../angular-datatables/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_router__ = __webpack_require__("../../../router/esm5/router.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_ngx_file_drop__ = __webpack_require__("../../../../ngx-file-drop/ngx-file-drop.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_ngx_dropzone_wrapper__ = __webpack_require__("../../../../ngx-dropzone-wrapper/dist/ngx-dropzone-wrapper.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__app_component__ = __webpack_require__("../../../../../src/app/app.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__joblist_joblist_component__ = __webpack_require__("../../../../../src/app/joblist/joblist.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__jobactivity_jobactivity_component__ = __webpack_require__("../../../../../src/app/jobactivity/jobactivity.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_10__draganddrop_draganddrop_component__ = __webpack_require__("../../../../../src/app/draganddrop/draganddrop.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_11__overview_overview_component__ = __webpack_require__("../../../../../src/app/overview/overview.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_12__page_not_found_page_not_found_component__ = __webpack_require__("../../../../../src/app/page-not-found/page-not-found.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_13__angular_platform_browser_animations__ = __webpack_require__("../../../platform-browser/esm5/animations.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_14__rest_service__ = __webpack_require__("../../../../../src/app/rest.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_15__data_service__ = __webpack_require__("../../../../../src/app/data.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_16__stomp_ng2_stompjs__ = __webpack_require__("../../../../@stomp/ng2-stompjs/@stomp/ng2-stompjs.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_17__angular_material__ = __webpack_require__("../../../material/esm5/material.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_18__angular_material_progress_spinner__ = __webpack_require__("../../../material/esm5/progress-spinner.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_19__draganddrop_drag_and_drop_importer_component__ = __webpack_require__("../../../../../src/app/draganddrop/drag-and-drop-importer.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_20__jobactivity_date_to_ago_pipe__ = __webpack_require__("../../../../../src/app/jobactivity/date.to.ago.pipe.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};





















var appRoutes = [
    { path: 'active', component: __WEBPACK_IMPORTED_MODULE_9__jobactivity_jobactivity_component__["a" /* JobactivityComponent */] },
    { path: 'list', component: __WEBPACK_IMPORTED_MODULE_8__joblist_joblist_component__["a" /* JoblistComponent */] },
    { path: '', component: __WEBPACK_IMPORTED_MODULE_11__overview_overview_component__["a" /* OverviewComponent */] },
    { path: '**', component: __WEBPACK_IMPORTED_MODULE_12__page_not_found_page_not_found_component__["a" /* PageNotFoundComponent */] }
];
var stompConfig = {
    // Which server?
    url: 'ws://127.0.0.1:8181/stomp',
    // Headers
    // Typical keys: login, passcode, host
    headers: {},
    // How often to heartbeat?
    // Interval in milliseconds, set to 0 to disable
    heartbeat_in: 0,
    heartbeat_out: 20000,
    // Wait in milliseconds before attempting auto reconnect
    // Set to 0 to disable
    // Typical value 5000 (5 seconds)
    reconnect_delay: 5000,
    // Will log diagnostics on console
    debug: true
};
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_1__angular_core__["K" /* NgModule */])({
            declarations: [
                __WEBPACK_IMPORTED_MODULE_7__app_component__["a" /* AppComponent */],
                __WEBPACK_IMPORTED_MODULE_8__joblist_joblist_component__["a" /* JoblistComponent */],
                __WEBPACK_IMPORTED_MODULE_9__jobactivity_jobactivity_component__["a" /* JobactivityComponent */],
                __WEBPACK_IMPORTED_MODULE_10__draganddrop_draganddrop_component__["a" /* DraganddropComponent */],
                __WEBPACK_IMPORTED_MODULE_12__page_not_found_page_not_found_component__["a" /* PageNotFoundComponent */],
                __WEBPACK_IMPORTED_MODULE_11__overview_overview_component__["a" /* OverviewComponent */],
                __WEBPACK_IMPORTED_MODULE_19__draganddrop_drag_and_drop_importer_component__["a" /* DragAndDropImporterComponent */],
                __WEBPACK_IMPORTED_MODULE_20__jobactivity_date_to_ago_pipe__["a" /* DateToAgoPipe */]
            ],
            imports: [
                __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
                __WEBPACK_IMPORTED_MODULE_2_angular_datatables__["a" /* DataTablesModule */],
                __WEBPACK_IMPORTED_MODULE_3__angular_common_http__["b" /* HttpClientModule */],
                __WEBPACK_IMPORTED_MODULE_5_ngx_file_drop__["a" /* FileDropModule */],
                __WEBPACK_IMPORTED_MODULE_13__angular_platform_browser_animations__["a" /* BrowserAnimationsModule */],
                __WEBPACK_IMPORTED_MODULE_17__angular_material__["h" /* MatTableModule */],
                __WEBPACK_IMPORTED_MODULE_17__angular_material__["i" /* MatToolbarModule */],
                __WEBPACK_IMPORTED_MODULE_17__angular_material__["a" /* MatButtonModule */],
                __WEBPACK_IMPORTED_MODULE_17__angular_material__["f" /* MatIconModule */],
                __WEBPACK_IMPORTED_MODULE_17__angular_material__["e" /* MatDividerModule */],
                __WEBPACK_IMPORTED_MODULE_17__angular_material__["d" /* MatDialogModule */],
                __WEBPACK_IMPORTED_MODULE_6_ngx_dropzone_wrapper__["a" /* DropzoneModule */],
                __WEBPACK_IMPORTED_MODULE_17__angular_material__["b" /* MatCardModule */],
                __WEBPACK_IMPORTED_MODULE_18__angular_material_progress_spinner__["a" /* MatProgressSpinnerModule */],
                __WEBPACK_IMPORTED_MODULE_4__angular_router__["a" /* RouterModule */].forRoot(appRoutes, { enableTracing: true } // <-- debugging purposes only
                )
            ],
            providers: [__WEBPACK_IMPORTED_MODULE_14__rest_service__["a" /* RestService */], __WEBPACK_IMPORTED_MODULE_15__data_service__["a" /* DataService */],
                __WEBPACK_IMPORTED_MODULE_16__stomp_ng2_stompjs__["b" /* StompService */],
                {
                    provide: __WEBPACK_IMPORTED_MODULE_16__stomp_ng2_stompjs__["a" /* StompConfig */],
                    useValue: stompConfig
                }
            ],
            entryComponents: [
                __WEBPACK_IMPORTED_MODULE_19__draganddrop_drag_and_drop_importer_component__["a" /* DragAndDropImporterComponent */]
            ],
            bootstrap: [__WEBPACK_IMPORTED_MODULE_7__app_component__["a" /* AppComponent */]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "../../../../../src/app/data.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DataService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__printing_job__ = __webpack_require__("../../../../../src/app/printing-job.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__rest_service__ = __webpack_require__("../../../../../src/app/rest.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__stomp_ng2_stompjs__ = __webpack_require__("../../../../@stomp/ng2-stompjs/@stomp/ng2-stompjs.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_Rx__ = __webpack_require__("../../../../rxjs/_esm5/Rx.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__rxjs_operators__ = __webpack_require__("../../../../../src/app/rxjs-operators.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var DataService = (function () {
    function DataService(_stompService, restService) {
        var _this = this;
        this._stompService = _stompService;
        this.restService = restService;
        this.dataListeners = [];
        this.stomp_subscription = this._stompService.subscribe('notifications.printing-job');
        this.stomp_subscription.map(function (message) {
            return message.body;
        }).subscribe(function (msg_body) {
            var json = JSON.parse(msg_body);
            var printingJob = __WEBPACK_IMPORTED_MODULE_1__printing_job__["a" /* PrintingJob */].decodeJson(json);
            _this.updateSingle(printingJob);
        });
        var timer = __WEBPACK_IMPORTED_MODULE_4_rxjs_Rx__["a" /* Observable */].timer(0, 10000);
        timer.subscribe(function (t) {
            _this.restService.fetchPrintingJobs()
                .subscribe(function (data) { return _this.loadPrintingJobs(data); });
        });
    }
    DataService.prototype.addListener = function (listener) {
        this.dataListeners.push(listener);
    };
    DataService.prototype.callListeners = function () {
        var _this = this;
        this.dataListeners.forEach(function (listener) {
            listener.update(_this.printingJobs);
        });
    };
    DataService.prototype.updateSingle = function (printingJob) {
        for (var i = 0; i < this.printingJobs.length; i++) {
            if (__WEBPACK_IMPORTED_MODULE_1__printing_job__["a" /* PrintingJob */].equals(this.printingJobs[i], printingJob)) {
                this.printingJobs[i] = printingJob;
                this.callListeners();
                break;
            }
        }
    };
    DataService.prototype.loadPrintingJobs = function (rawPrintingJobs) {
        this.setPrintingJobs(this.unwrap(rawPrintingJobs));
        this.callListeners();
    };
    DataService.prototype.unwrap = function (rawPrintingJobs) {
        var printingJobs = [];
        rawPrintingJobs.jobs.forEach(function (rawPrintingJob) {
            var printingJob = __WEBPACK_IMPORTED_MODULE_1__printing_job__["a" /* PrintingJob */].decode(rawPrintingJob);
            printingJobs.push(printingJob);
        });
        return printingJobs;
    };
    DataService.prototype.setPrintingJobs = function (printingJobs) {
        this.printingJobs = printingJobs;
    };
    DataService.prototype.getPrintingJobs = function () {
        return this.printingJobs;
    };
    DataService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["C" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_3__stomp_ng2_stompjs__["b" /* StompService */], __WEBPACK_IMPORTED_MODULE_2__rest_service__["a" /* RestService */]])
    ], DataService);
    return DataService;
}());



/***/ }),

/***/ "../../../../../src/app/draganddrop/drag-and-drop-importer.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/draganddrop/drag-and-drop-importer.component.html":
/***/ (function(module, exports) {

module.exports = "<h2 mat-dialog-title>Add a printing job</h2>\n<mat-dialog-content>\n  <dropzone [config]=\"dropZoneConfig\" [message]=\"'Click or drag a file to add it\n    to the queue.'\"></dropzone>\n</mat-dialog-content>\n<mat-dialog-actions>\n  <button mat-button mat-dialog-close>Done</button>\n</mat-dialog-actions>\n"

/***/ }),

/***/ "../../../../../src/app/draganddrop/drag-and-drop-importer.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DragAndDropImporterComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var DragAndDropImporterComponent = (function () {
    function DragAndDropImporterComponent() {
        this.dropZoneConfig = {
            url: 'http://localhost:8181/api/job',
            maxFiles: 1,
            createImageThumbnails: false,
            autoReset: 2000
        };
    }
    DragAndDropImporterComponent.prototype.ngOnInit = function () {
    };
    DragAndDropImporterComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-drag-and-drop-importer',
            template: __webpack_require__("../../../../../src/app/draganddrop/drag-and-drop-importer.component.html"),
            styles: [__webpack_require__("../../../../../src/app/draganddrop/drag-and-drop-importer.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], DragAndDropImporterComponent);
    return DragAndDropImporterComponent;
}());



/***/ }),

/***/ "../../../../../src/app/draganddrop/draganddrop.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/draganddrop/draganddrop.component.html":
/***/ (function(module, exports) {

module.exports = "<button mat-fab class=\"br-corner\" (click)=\"addPrintingJob()\">\n  <mat-icon svgIcon=\"add\"></mat-icon>\n</button>\n"

/***/ }),

/***/ "../../../../../src/app/draganddrop/draganddrop.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DraganddropComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_material__ = __webpack_require__("../../../material/esm5/material.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__drag_and_drop_importer_component__ = __webpack_require__("../../../../../src/app/draganddrop/drag-and-drop-importer.component.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var DraganddropComponent = (function () {
    function DraganddropComponent(dialog) {
        this.dialog = dialog;
    }
    DraganddropComponent.prototype.ngOnInit = function () {
    };
    DraganddropComponent.prototype.addPrintingJob = function () {
        this.dialog.open(__WEBPACK_IMPORTED_MODULE_2__drag_and_drop_importer_component__["a" /* DragAndDropImporterComponent */], {
            minHeight: '160px',
            minWidth: '600px'
        });
    };
    DraganddropComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-draganddrop',
            template: __webpack_require__("../../../../../src/app/draganddrop/draganddrop.component.html"),
            styles: [__webpack_require__("../../../../../src/app/draganddrop/draganddrop.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_material__["c" /* MatDialog */]])
    ], DraganddropComponent);
    return DraganddropComponent;
}());



/***/ }),

/***/ "../../../../../src/app/jobactivity/date.to.ago.pipe.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return DateToAgoPipe; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};

var DateToAgoPipe = (function () {
    function DateToAgoPipe() {
    }
    DateToAgoPipe.prototype.transform = function (date) {
        var seconds = Math.floor((new Date().getTime() - date.getTime()) / 1000);
        var interval = Math.floor(seconds / 31536000);
        if (interval > 1) {
            return interval + " years";
        }
        interval = Math.floor(seconds / 2592000);
        if (interval > 1) {
            return interval + " months";
        }
        interval = Math.floor(seconds / 86400);
        if (interval > 1) {
            return interval + " days";
        }
        interval = Math.floor(seconds / 3600);
        if (interval > 1) {
            return interval + " hours";
        }
        interval = Math.floor(seconds / 60);
        if (interval > 1) {
            return interval + " minutes";
        }
        return Math.floor(seconds) + " seconds";
    };
    DateToAgoPipe = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["V" /* Pipe */])({
            name: "dateToAgo"
        })
    ], DateToAgoPipe);
    return DateToAgoPipe;
}());



/***/ }),

/***/ "../../../../../src/app/jobactivity/jobactivity.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/jobactivity/jobactivity.component.html":
/***/ (function(module, exports) {

module.exports = "<div *ngIf=\"activeJob; then thenBlock else elseBlock\"></div>\n<ng-template #thenBlock>\n  <mat-card>\n    <mat-card-header>\n      <mat-card-title>Active printing job</mat-card-title>\n      <mat-card-subtitle>{{ activeJob.name }}</mat-card-subtitle>\n    </mat-card-header>\n    <mat-card-content>\n      <p>\n        Created: {{ activeJob.created | dateToAgo }} ago\n        <br>\n        Running since: {{ activeJob.started | dateToAgo }} ago\n      </p>\n    </mat-card-content>\n    <mat-spinner [diameter]=\"20.0\"></mat-spinner>\n  </mat-card>\n</ng-template>\n<ng-template #elseBlock>\n  <mat-card>No printing job active at the moment</mat-card>\n</ng-template>\n"

/***/ }),

/***/ "../../../../../src/app/jobactivity/jobactivity.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return JobactivityComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__data_service__ = __webpack_require__("../../../../../src/app/data.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var JobactivityComponent = (function () {
    function JobactivityComponent(dataService) {
        this.dataService = dataService;
    }
    JobactivityComponent.prototype.ngOnInit = function () {
        this.dataService.addListener(this);
    };
    JobactivityComponent.prototype.update = function (printingJobs) {
        var _this = this;
        this.setActiveJob(null);
        printingJobs.forEach(function (printingJob) {
            if (printingJob.started !== null && printingJob.completed === null) {
                _this.setActiveJob(printingJob);
                return;
            }
        });
    };
    JobactivityComponent.prototype.setActiveJob = function (activeJob) {
        this.activeJob = activeJob;
    };
    JobactivityComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-jobactivity',
            template: __webpack_require__("../../../../../src/app/jobactivity/jobactivity.component.html"),
            styles: [__webpack_require__("../../../../../src/app/jobactivity/jobactivity.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__data_service__["a" /* DataService */]])
    ], JobactivityComponent);
    return JobactivityComponent;
}());



/***/ }),

/***/ "../../../../../src/app/joblist/joblist.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/joblist/joblist.component.html":
/***/ (function(module, exports) {

module.exports = "<mat-table #table [dataSource]=\"printingJobs\">\n\n  <!-- Index column -->\n  <ng-container matColumnDef=\"identifier\">\n    <mat-header-cell *matHeaderCellDef> Identifier </mat-header-cell>\n    <mat-cell *matCellDef=\"let element\"> {{ element.id }} </mat-cell>\n  </ng-container>\n\n  <!-- Name column -->\n  <ng-container matColumnDef=\"name\">\n    <mat-header-cell *matHeaderCellDef> Name </mat-header-cell>\n    <mat-cell *matCellDef=\"let element\"> {{ element.name }} </mat-cell>\n  </ng-container>\n\n  <!-- Created column -->\n  <ng-container matColumnDef=\"created\">\n    <mat-header-cell *matHeaderCellDef> Created </mat-header-cell>\n    <mat-cell *matCellDef=\"let element\"> {{ element.created }} </mat-cell>\n  </ng-container>\n\n  <!-- Started column -->\n  <ng-container matColumnDef=\"started\">\n    <mat-header-cell *matHeaderCellDef> Started </mat-header-cell>\n    <mat-cell *matCellDef=\"let element\"> {{ element.started }} </mat-cell>\n  </ng-container>\n\n  <!-- Completed column -->\n  <ng-container matColumnDef=\"completed\">\n    <mat-header-cell *matHeaderCellDef> Completed </mat-header-cell>\n    <mat-cell *matCellDef=\"let element\"> {{ element.completed }} </mat-cell>\n  </ng-container>\n\n  <mat-header-row *matHeaderRowDef=\"columnsToDisplay\"></mat-header-row>\n  <mat-row *matRowDef=\"let row; columns: columnsToDisplay\"></mat-row>\n\n</mat-table>\n"

/***/ }),

/***/ "../../../../../src/app/joblist/joblist.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return JoblistComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__rest_service__ = __webpack_require__("../../../../../src/app/rest.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__data_service__ = __webpack_require__("../../../../../src/app/data.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var JoblistComponent = (function () {
    // Methods
    function JoblistComponent(restService, dataService) {
        this.restService = restService;
        this.dataService = dataService;
        this.columnsToDisplay = ["identifier", "name", "created", "started", "completed"];
    }
    JoblistComponent.prototype.ngOnInit = function () {
        this.dataService.addListener(this);
    };
    JoblistComponent.prototype.update = function (printingJobs) {
        this.printingJobs = printingJobs;
    };
    JoblistComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-joblist',
            template: __webpack_require__("../../../../../src/app/joblist/joblist.component.html"),
            styles: [__webpack_require__("../../../../../src/app/joblist/joblist.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__rest_service__["a" /* RestService */],
            __WEBPACK_IMPORTED_MODULE_2__data_service__["a" /* DataService */]])
    ], JoblistComponent);
    return JoblistComponent;
}());



/***/ }),

/***/ "../../../../../src/app/overview/overview.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/overview/overview.component.html":
/***/ (function(module, exports) {

module.exports = "<mat-divider></mat-divider>\n<app-jobactivity></app-jobactivity>\n<mat-divider></mat-divider>\n<app-joblist></app-joblist>\n<mat-divider></mat-divider>\n<app-draganddrop></app-draganddrop>\n"

/***/ }),

/***/ "../../../../../src/app/overview/overview.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return OverviewComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var OverviewComponent = (function () {
    function OverviewComponent() {
    }
    OverviewComponent.prototype.ngOnInit = function () {
    };
    OverviewComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-overview',
            template: __webpack_require__("../../../../../src/app/overview/overview.component.html"),
            styles: [__webpack_require__("../../../../../src/app/overview/overview.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], OverviewComponent);
    return OverviewComponent;
}());



/***/ }),

/***/ "../../../../../src/app/page-not-found/page-not-found.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/page-not-found/page-not-found.component.html":
/***/ (function(module, exports) {

module.exports = "<h1>\n  Sorry, this page does not exist.\n</h1>\n"

/***/ }),

/***/ "../../../../../src/app/page-not-found/page-not-found.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return PageNotFoundComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};

var PageNotFoundComponent = (function () {
    function PageNotFoundComponent() {
    }
    PageNotFoundComponent.prototype.ngOnInit = function () {
    };
    PageNotFoundComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["n" /* Component */])({
            selector: 'app-page-not-found',
            template: __webpack_require__("../../../../../src/app/page-not-found/page-not-found.component.html"),
            styles: [__webpack_require__("../../../../../src/app/page-not-found/page-not-found.component.css")]
        }),
        __metadata("design:paramtypes", [])
    ], PageNotFoundComponent);
    return PageNotFoundComponent;
}());



/***/ }),

/***/ "../../../../../src/app/printing-job.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return PrintingJob; });
var PrintingJob = (function () {
    function PrintingJob() {
    }
    PrintingJob.decodeJson = function (json) {
        return {
            id: json.identifier.identifier,
            name: json.fileName,
            created: PrintingJob.toDate(json.createdTime),
            started: PrintingJob.toDate(json.startedTime),
            completed: PrintingJob.toDate(json.completedTime)
        };
    };
    PrintingJob.decode = function (rawPrintingJob) {
        return {
            id: rawPrintingJob.identifier.identifier,
            name: rawPrintingJob.fileName,
            created: PrintingJob.toDate(rawPrintingJob.createdTime),
            started: PrintingJob.toDate(rawPrintingJob.startedTime),
            completed: PrintingJob.toDate(rawPrintingJob.completedTime)
        };
    };
    PrintingJob.equals = function (p1, p2) {
        if (p1 && p2 && p1.id && p2.id) {
            return p1.id === p2.id;
        }
        return false;
    };
    PrintingJob.toString = function (p1) {
        return JSON.stringify(p1);
    };
    PrintingJob.toDate = function (timeStamp) {
        return timeStamp ? new Date(timeStamp) : null;
    };
    return PrintingJob;
}());



/***/ }),

/***/ "../../../../../src/app/rest.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return RestService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var RestService = (function () {
    function RestService(http) {
        this.http = http;
        this.RX_BASE_URL = "http://localhost:8181";
    }
    RestService.prototype.fetchPrintingJobs = function () {
        return this.http.get(this.RX_BASE_URL + '/api/job');
    };
    RestService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["C" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */]])
    ], RestService);
    return RestService;
}());



/***/ }),

/***/ "../../../../../src/app/rxjs-operators.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0_rxjs_add_observable_throw__ = __webpack_require__("../../../../rxjs/_esm5/add/observable/throw.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1_rxjs_add_operator_catch__ = __webpack_require__("../../../../rxjs/_esm5/add/operator/catch.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_add_operator_debounceTime__ = __webpack_require__("../../../../rxjs/_esm5/add/operator/debounceTime.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_rxjs_add_operator_distinctUntilChanged__ = __webpack_require__("../../../../rxjs/_esm5/add/operator/distinctUntilChanged.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4_rxjs_add_operator_map__ = __webpack_require__("../../../../rxjs/_esm5/add/operator/map.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5_rxjs_add_operator_switchMap__ = __webpack_require__("../../../../rxjs/_esm5/add/operator/switchMap.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_rxjs_add_operator_toPromise__ = __webpack_require__("../../../../rxjs/_esm5/add/operator/toPromise.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6_rxjs_add_operator_toPromise___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_6_rxjs_add_operator_toPromise__);
// Statics

// Operators








/***/ }),

/***/ "../../../../../src/environments/environment.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return environment; });
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.
var environment = {
    production: false
};


/***/ }),

/***/ "../../../../../src/main.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__ = __webpack_require__("../../../platform-browser-dynamic/esm5/platform-browser-dynamic.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_app_module__ = __webpack_require__("../../../../../src/app/app.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__environments_environment__ = __webpack_require__("../../../../../src/environments/environment.ts");




if (__WEBPACK_IMPORTED_MODULE_3__environments_environment__["a" /* environment */].production) {
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_17" /* enableProdMode */])();
}
Object(__WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_2__app_app_module__["a" /* AppModule */])
    .catch(function (err) { return console.log(err); });


/***/ }),

/***/ 0:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__("../../../../../src/main.ts");


/***/ })

},[0]);
//# sourceMappingURL=main.bundle.js.map