import {Injectable} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {Observable} from 'rxjs';
import {MatSnackBar} from '@angular/material/snack-bar';
import {HttpErrorResponse} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TranslationService {

  constructor(
    private snackBar: MatSnackBar,
    private translateService: TranslateService
  ) { }

  public showSnackbar(message: string): void {
    this.getTranslation(message).subscribe((translation: string) => {
      this.snackBar.open(translation, '', {
        duration: 2000,
      });
    });
  }

  public showSnackbarOnError(response: HttpErrorResponse): void {
    if (response.error.i18nKey?.length > 0) {
      this.getTranslation(response.error.i18nKey).subscribe((translation: string) => {
        const message = response.error.message?.length > 0 ? translation + ': ' + response.error.message : translation;

        if (response.error.internalError) {
          this.snackBar.open(message, 'Got It!');
        } else {
          this.snackBar.open(message, '', {
            duration: 2000,
          });
        }
      });
    } else {
      this.getTranslation('Connection Error').subscribe((translation: string) => {
        this.snackBar.open(translation + ' (' + response.status + ')', 'Got It!');
      });
    }
  }

  public loadDefaultLanguage(): void {
    this.translateService.use('de');
  }

  public getTranslation(key: string): Observable<string> {
    return this.translateService.get(key);
  }
}
