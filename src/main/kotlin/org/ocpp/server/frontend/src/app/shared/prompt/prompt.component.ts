import {Component, Inject} from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-prompt',
  templateUrl: './prompt.component.html',
  styleUrls: ['./prompt.component.scss']
})
export class PromptComponent {

  constructor(
    private dialogRef: MatDialogRef<PromptComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  public closeAndReturnAnswer(answer: boolean): void {
    this.dialogRef.close(answer);
  }
}
