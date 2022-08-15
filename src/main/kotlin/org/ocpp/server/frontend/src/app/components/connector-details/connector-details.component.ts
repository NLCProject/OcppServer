import {Component, NgZone, OnInit} from '@angular/core';
import {RouterUtilService} from '../../services/router-util.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TranslationService} from '../../services/translation.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ConnectorService} from '../../services/connector.service';
import {ConnectorModel} from '../../models/ConnectorModel';

@Component({
  selector: 'app-connector-details',
  templateUrl: './connector-details.component.html',
  styleUrls: ['./connector-details.component.scss']
})
export class ConnectorDetailsComponent extends RouterUtilService implements OnInit {

  constructor(
    router: Router,
    ngZone: NgZone,
    private service: ConnectorService,
    private translationService: TranslationService,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder
  ) {
    super(router, ngZone, 'smart-home', 1);
  }

  public id: string = '';
  public smartHomeId: string = '';
  public model: ConnectorModel = new ConnectorModel();
  public formGroup: FormGroup | undefined;
  public loading = false;
  public isVisible = false;

  ngOnInit(): void {
    this.createForm();
    this.getId();
  }

  public updateModel(): void {
    this.loading = true;
    const controls = this.formGroup!!.controls;
    this.model.connectorName = controls!!.name.value;

    this.service.save(this.model!!).subscribe(
      response => {
        this.translationService.showSnackbar('Updated');
        this.loading = false;
        this.reloadSmartHomeDetailView(response.id);
      },
      error => {
        this.translationService.showSnackbarOnError(error);
        this.loading = false;
      }
    );
  }

  private setValues(model: ConnectorModel): void {
    this.formGroup!!.setValue({
      name: model.connectorName
    });

    this.loading = false;
  }

  private createForm(): void {
    this.formGroup = this.formBuilder.group(
      {
        name: [null, Validators.compose([Validators.required])]
      }
    );

    this.formGroup.valueChanges.subscribe(() => {
      this.isVisible = true;
    });

    this.loading = false;
  }

  private getId(): void {
    this.activatedRoute.params.subscribe(params => {
      const smartHomeKey = 'smartHomeId';
      this.smartHomeId = params[smartHomeKey];

      const key = 'id';
      this.id = params[key];
      if (this.id?.length > 0) {
        this.loadData();
      }
    });
  }

  private loadData(): void {
    this.loading = true;
    this.service.findById(this.id).subscribe(
      response => {
        this.model = response;
        this.setValues(response);
      },
      error => {
        this.translationService.showSnackbarOnError(error);
        this.loading = false;
      }
    );
  }
}
