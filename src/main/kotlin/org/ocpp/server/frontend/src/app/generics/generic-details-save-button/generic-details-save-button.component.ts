import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-generic-details-save-button',
  templateUrl: './generic-details-save-button.component.html',
  styleUrls: ['./generic-details-save-button.component.scss']
})
export class GenericDetailsSaveButtonComponent {

  constructor() { }

  @Input()
  public isVisible = false;

  @Input()
  public loading = false;

  @Input()
  public disabled = false;

  @Input()
  public routerPath: string = '';

  @Output()
  public updateModel = new EventEmitter<void>();
}
