import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CommandService} from "../../services/command.service";
import {ModbusCommandDto} from "../../models/ModbusCommandDto";
import {TranslationService} from "../../services/translation.service";

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
  public dto: ModbusCommandDto | null = null;

  ngOnInit(): void {
    this.getId();
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
