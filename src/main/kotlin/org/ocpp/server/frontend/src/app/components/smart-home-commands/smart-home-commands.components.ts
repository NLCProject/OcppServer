import {Component, Input, OnInit} from '@angular/core';
import {SmartHomeModel} from "../../models/SmartHomeModel";

@Component({
  selector: 'app-smart-home-commands',
  templateUrl: './smart-home-commands.component.html',
  styleUrls: ['./smart-home-commands.component.scss']
})
export class SmartHomeCommandsComponents implements OnInit {

  constructor() { }

  @Input()
  public id: string = '';

  @Input()
  public loading: boolean = false;

  @Input()
  public model: SmartHomeModel | undefined;

  ngOnInit(): void {
  }

}
