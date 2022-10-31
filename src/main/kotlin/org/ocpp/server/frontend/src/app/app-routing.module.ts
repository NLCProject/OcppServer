import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SmartHomeOverviewComponent} from './components/smart-home-overview/smart-home-overview.component';
import {SmartHomeDetailsOverviewComponent} from './components/smart-home-details-overview/smart-home-details-overview.component';
import {ConnectorDetailsComponent} from './components/connector-details/connector-details.component';
import {CommandDetailsComponent} from "./components/command-details/command-details.component";

const routes: Routes = [
  {
    path: 'connector/details/:id/:smartHomeId',
    component: ConnectorDetailsComponent
  }, {
    path: 'connector/details',
    component: ConnectorDetailsComponent
  }, {
    path: 'smart-home/details/:id',
    component: SmartHomeDetailsOverviewComponent
  }, {
    path: 'smart-home/details',
    component: SmartHomeDetailsOverviewComponent
  }, {
    path: 'smart-home/overview',
    component: SmartHomeOverviewComponent
  }, {
    path: 'command/details/:id/:smartHomeId',
    component: CommandDetailsComponent
  }, {
    path: '**',
    component: SmartHomeOverviewComponent
  }, {
    path: '',
    component: SmartHomeOverviewComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
