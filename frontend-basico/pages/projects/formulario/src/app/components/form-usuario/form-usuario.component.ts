import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-form-usuario',
  templateUrl: './form-usuario.component.html',
  styleUrls: ['./form-usuario.component.css']
})
export class FormUsuarioComponent implements OnInit {
  formUsuario!: FormGroup;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.formUsuario = this.formBuilder.group({
      nome: ['', [Validators.required, Validators.pattern(/^\S{1,12}$/)]],
      senha: ['', [Validators.required, Validators.minLength(4), Validators.pattern(/^(?=.*[A-Z])(?=.*\W).*$/)]],
      email: ['', [Validators.required, Validators.email]],
      nomeCompleto: this.formBuilder.group({
        primeiroNome: ['', Validators.required],
        sobrenome: ['', Validators.required]
      }),
      telefone: ['', [Validators.required, Validators.pattern(/^\d{10,11}$/)]], // Exemplo de padrão para telefone brasileiro
      endereco: ['', Validators.required],
      dataNascimento: ['', [Validators.required, this.validateAge]],
      genero: ['', Validators.required],
      profissao: ['', Validators.required],
    });
  }

  validateAge(control: any) {
    const birthday = new Date(control.value);
    const ageDifferenceMs = Date.now() - birthday.getTime();
    const ageDate = new Date(ageDifferenceMs);
    const userAge = Math.abs(ageDate.getUTCFullYear() - 1970);

    return userAge >= 18 ? null : { underage: true };
  }

  submitForm() {
    if (this.formUsuario.valid) {
      const formData = this.formUsuario.value;
      console.log(formData); // Aqui você pode fazer o que quiser com os dados
    } else {
      console.log('Formulário inválido');
    }
  }
}
