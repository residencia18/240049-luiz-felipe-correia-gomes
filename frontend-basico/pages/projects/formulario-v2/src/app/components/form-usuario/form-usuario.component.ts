import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FormInteractionsService } from '../../services/form-interactions.service';
import { ConfirmationDialogComponent } from '../confirmation-dialog/confirmation-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { UserForm } from '../../model/user-form';

@Component({
  selector: 'app-form-usuario',
  templateUrl: './form-usuario.component.html',
  styleUrls: ['./form-usuario.component.css']
})
export class FormUsuarioComponent implements OnInit {
  formUsuario!: FormGroup;

  submitPage = true;
  submittedPage = false;


  constructor(
    private formBuilder: FormBuilder,
    private formInteractionsService: FormInteractionsService,
    private dialog: MatDialog) { }

  ngOnInit(): void {
    this.formUsuario = this.formBuilder.group({
      usuario: ['', [Validators.required, Validators.pattern(/^\S{1,12}$/)]],
      senha: ['', [Validators.required, Validators.minLength(4), Validators.pattern(/^(?=.*[A-Z])(?=.*\W).*$/)]],
      email: ['', [Validators.required, Validators.email]],
      nomeCompleto: ['', [Validators.required, Validators.pattern(/^[a-zA-Z\s]+$/)]],
      telefone: ['', [Validators.required, Validators.pattern(/^\d{10,11}$/)]], // Exemplo de padrão para telefone brasileiro
      endereco: ['', Validators.required],
      dataNascimento: ['', [Validators.required, this.validateAge]],
      genero: ['', Validators.required],
      profissao: ['', Validators.required],
    });

    // Assinando os Observables valuesChanges e statusChanges para monitorar as alterações
    this.formUsuario.valueChanges.subscribe(changes => {
      this.formInteractionsService.updateFormChanges({ values: changes });
    });

    this.formUsuario.statusChanges.subscribe(status => {
      this.formInteractionsService.updateFormChanges({ status: status });
    });
  }

  validateAge(control: any) {
    const birthday = new Date(control.value);
    const ageDifferenceMs = Date.now() - birthday.getTime();
    const ageDate = new Date(ageDifferenceMs);
    const userAge = Math.abs(ageDate.getUTCFullYear() - 1970);

    return userAge >= 18 ? null : { underage: true };
  }

  openConfirmationDialog(userForm: UserForm): void {
    const dialogRef = this.dialog.open(ConfirmationDialogComponent, {
      width: '250px',
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.submitForm(userForm);
      }
    });
  }

  submitForm(userForm: UserForm) {
    if (this.formUsuario.valid) {
      const formData = this.formUsuario.value;
      console.log('Formulário enviado:', formData);
      this.switchPages(); 
    } else {
      console.log('Formulário inválido');
    }
  }
  
  switchPages(): void {
    this.submitPage = !this.submitPage;
    this.submittedPage = !this.submittedPage;
  }
  
  resetForm(): void {
    this.formUsuario.reset();
  }
  
  return(): void {
    this.switchPages();
    this.resetForm();
  }
}
