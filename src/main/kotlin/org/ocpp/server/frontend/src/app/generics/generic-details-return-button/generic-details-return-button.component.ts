import {Component, Input, NgZone} from '@angular/core';
import { Location } from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: 'app-generic-details-return-button',
  templateUrl: './generic-details-return-button.component.html',
  styleUrls: ['./generic-details-return-button.component.scss']
})
export class GenericDetailsReturnButtonComponent {

  constructor(
    private router: Router,
    private ngZone: NgZone,
    private location: Location
  ) { }

  @Input()
  public titleI18nKey: string = '';

  @Input()
  public parentRouterPath: string = '';

  @Input()
  public tabIndex: number | undefined;

  @Input()
  public forceBack: boolean = false;

  public returnToRoute(): void {
    if (this.forceBack) {
      this.location.back();
      return;
    }

    if (this.tabIndex) {
      this.ngZone.run(() => this.router.navigate([`/${this.parentRouterPath}`, { index: this.tabIndex }]));
    } else {
      this.ngZone.run(() => this.router.navigate([`/${this.parentRouterPath}`]));
    }
  }
}
