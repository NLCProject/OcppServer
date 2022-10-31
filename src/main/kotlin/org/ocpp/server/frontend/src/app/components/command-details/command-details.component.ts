import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {CommandService} from '../../services/command.service';
import {ModbusCacheObject} from '../../models/ModbusCacheObject';
import {TranslationService} from '../../services/translation.service';
import {ModbusResponse} from "../../models/ModbusResponse";

@Component({
  selector: 'app-command-details',
  templateUrl: './command-details.component.html',
  styleUrls: ['./command-details.component.scss']
})
export class CommandDetailsComponent implements OnInit {

  constructor(
    private activatedRoute: ActivatedRoute,
    private service: CommandService,
    private translationService: TranslationService
  ) { }

  public smartHomeId = '';
  public commandId = '';
  public loading = true;
  public dto: ModbusCacheObject | null = null;
  public response: ModbusResponse | null = null;

  ngOnInit(): void {
    this.getId();
  }

  public runCommand(): void {
    this.loading = true;
    this.service.runCommand(this.smartHomeId, this.commandId).subscribe(
      response => {
        this.response = response;
        this.translationService.showSnackbar('Command Sent');
        this.loading = false;
      },
      error => {
        this.translationService.showSnackbarOnError(error);
        this.loading = false;
      }
    );
  }

  private getId(): void {
    this.activatedRoute.params.subscribe(params => {
      let key = 'smartHomeId';
      this.smartHomeId = params[key];

      key = 'id';
      this.commandId = params[key];

      this.loadData();
    });
  }

  private loadData(): void {
    this.loading = true;
    this.service.getCommandById(this.smartHomeId, this.commandId).subscribe(
      response => {
        this.dto = response;
        this.loading = false;
      },
      error => {
        this.translationService.showSnackbarOnError(error);
        this.loading = false;
      }
    );
  }
}
