import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'confirmation-dialog',
  template: `
    <h2 mat-dialog-title>Confirmação de Cadastro</h2>
    <mat-dialog-content>Deseja prosseguir com o cadastro?</mat-dialog-content>
    <mat-dialog-actions>
      <button mat-button (click)="onNoClick()">Cancelar</button>
      <button mat-button [mat-dialog-close]="true" cdkFocusInitial>Confirmar</button>
    </mat-dialog-actions>
  `
})
export class ConfirmationDialogComponent {
  constructor(public dialogRef: MatDialogRef<ConfirmationDialogComponent>) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}