import {Inject, Injectable, NgZone} from '@angular/core';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export abstract class RouterUtilService {

  protected constructor(
    protected router: Router,
    private ngZone: NgZone,
    @Inject(String) public routerPath: string,
    @Inject(Number) protected index: number,
) { }

  public getRoute(): string {
    return this.routerPath;
  }

  public getIndex(): number {
    return this.index;
  }

  public returnToDetails(smartHomeId: string): void {
    this.ngZone.run(() => this.router.navigate([`/smart-home/details`, smartHomeId, { index: this.index }]));
  }

  public returnToOverview(): void {
    this.ngZone.run(() => this.router.navigate([`/smart-home/overview`]));
  }

  public routeToPath(path: string, parameter: string): void {
    this.router.navigate([`/${path}`, parameter]);
  }

  public reloadDetailView(id: string, smartHomeId: string): void {
    // Force reload of page
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate([`/${this.routerPath}/details`, id, smartHomeId]);
    });
  }

  public reloadSmartHomeDetailView(id: string): void {
    // Force reload of page
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() => {
      this.router.navigate([`/${this.routerPath}/details`, id]);
    });
  }
}
