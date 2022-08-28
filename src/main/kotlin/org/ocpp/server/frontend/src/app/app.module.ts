import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import {FlexLayoutModule} from '@angular/flex-layout';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ToolbarComponent } from './toolbar/toolbar.component';
import {HttpClientModule, HttpClient} from '@angular/common/http';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatTabsModule} from '@angular/material/tabs';
import {MatListModule} from '@angular/material/list';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatMenuModule} from '@angular/material/menu';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatDividerModule} from '@angular/material/divider';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatSelectModule} from '@angular/material/select';
import {MatOptionModule} from '@angular/material/core';
import { SmartHomeOverviewComponent } from './components/smart-home-overview/smart-home-overview.component';
import { SmartHomeDetailsComponent } from './components/smart-home-details/smart-home-details.component';
import { GenericListWrapperComponent } from './generics/generic-list-wrapper/generic-list-wrapper.component';
import { GenericListComponent } from './generics/generic-list/generic-list.component';
import { GenericDetailsSaveButtonComponent } from './generics/generic-details-save-button/generic-details-save-button.component';
import { GenericDetailsReturnButtonComponent } from './generics/generic-details-return-button/generic-details-return-button.component';
import { SmartHomeDetailsOverviewComponent } from './components/smart-home-details-overview/smart-home-details-overview.component';
import { ConnectorDetailsComponent } from './components/connector-details/connector-details.component';
import { PromptComponent } from './shared/prompt/prompt.component';
import { SportsclubIconComponent } from './components/sportsclub-icon/sportsclub-icon.component';
import { ConnectorOverviewComponent } from './components/connector-overview/connector-overview.component';
import { SmartHomeCommandsComponents } from './components/smart-home-commands/smart-home-commands.components';
import { CommandOverviewComponent } from './components/smart-home-commands/command-overview/command-overview.component';

export function HttpLoaderFactory(http: HttpClient): TranslateHttpLoader {
  return new TranslateHttpLoader(http);
}

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    SmartHomeOverviewComponent,
    SmartHomeDetailsComponent,
    GenericListWrapperComponent,
    GenericListComponent,
    GenericDetailsSaveButtonComponent,
    GenericDetailsReturnButtonComponent,
    SmartHomeDetailsOverviewComponent,
    ConnectorDetailsComponent,
    PromptComponent,
    SportsclubIconComponent,
    ConnectorOverviewComponent,
    SmartHomeCommandsComponents,
    CommandOverviewComponent
  ],
  imports: [
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    InfiniteScrollModule,
    FlexLayoutModule,
    FormsModule,
    HttpClientModule,
    MatButtonModule,
    MatDialogModule,
    MatDividerModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatOptionModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    ReactiveFormsModule,
    TranslateModule.forRoot({
      defaultLanguage: 'de',
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
  ],
  exports: [
    ToolbarComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
