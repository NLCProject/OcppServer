import {Component, Input, NgZone, OnInit} from '@angular/core';
import {RouterUtilService} from '../../services/router-util.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {SmartHomeModel} from '../../models/SmartHomeModel';
import {SmartHomeService} from '../../services/smart-home.service';
import { TranslationService } from 'src/app/services/translation.service';
import { MatDialog } from '@angular/material/dialog';
import {PromptComponent} from '../../shared/prompt/prompt.component';

@Component({
  selector: 'app-smart-home-details',
  templateUrl: './smart-home-details.component.html',
  styleUrls: ['./smart-home-details.component.scss']
})
export class SmartHomeDetailsComponent extends RouterUtilService implements OnInit {

  constructor(
    router: Router,
    ngZone: NgZone,
    private service: SmartHomeService,
    private translationService: TranslationService,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private dialog: MatDialog
  ) {
    super(router, ngZone, 'smart-home', 0);
  }

  @Input()
  public id: string = '';

  @Input()
  public model: SmartHomeModel | undefined;

  public formGroup: FormGroup | undefined;
  public loading = false;
  public isVisible = false;

  ngOnInit(): void {
    this.createForm();
    this.setValues();
  }

  public updateModel(): void {
    this.loading = true;
    const newModel = !this.model;
    if (newModel) {
      this.model = new SmartHomeModel();
    }
    const controls = this.formGroup?.controls;
    this.model!!.name = controls!!.name.value;

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

  public delete(): void {
    const promptDialog = this.dialog.open(PromptComponent, {
      panelClass: 'mat-dialog-container-small',
      data: { translationKey: 'Want To Delete', showAccept: true }
    });

    promptDialog.afterClosed().subscribe(result => {
      if (result) {
        this.loading = true;
        this.service.delete(this.model!!.id).subscribe(
          () => {
            this.translationService.showSnackbar('Deleted');
            this.returnToOverview();
          },
          error => {
            this.translationService.showSnackbarOnError(error);
            this.loading = false;
          }
        );
      }
    });
  }

  private setValues(): void {
    this.formGroup!!.setValue({
      name: this.model!!.name
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
}
