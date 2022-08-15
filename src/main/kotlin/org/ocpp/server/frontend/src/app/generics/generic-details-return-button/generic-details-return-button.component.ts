import {Component, Input, NgZone} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-generic-details-return-button',
  templateUrl: './generic-details-return-button.component.html',
  styleUrls: ['./generic-details-return-button.component.scss']
})
export class GenericDetailsReturnButtonComponent {

  constructor(
    private router: Router,
    private ngZone: NgZone
  ) { }

  @Input()
  public titleI18nKey: string = '';

  @Input()
  public parentRouterPath: string = '';

  @Input()
  public tabIndex: number | undefined;

  public returnToRoute(): void {
    if (this.tabIndex) {
      this.ngZone.run(() => this.router.navigate([`/${this.parentRouterPath}`, { index: this.tabIndex }]));
    } else {
      this.ngZone.run(() => this.router.navigate([`/${this.parentRouterPath}`]));
    }
  }
}
