import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'formatDate',
})
export class FormatDatePipe implements PipeTransform {

  transform(value: any): string  {
    // Verifica se o valor é uma string
    if (typeof value !== 'string') {
      return 'Invalid Date';
    }

    // Divide a string da data nos elementos separados
    const dateElements = value.split('-');

    // Verifica se a string da data está no formato esperado
    if (dateElements.length !== 3) {
      return 'Invalid Date Format';
    }

    // Extrai os elementos da data
    const year = dateElements[0];
    const month = dateElements[1];
    const day = dateElements[2];

    // Formata a data para dd/mm/aaaa
    return `${day}/${month}/${year}`;
  }

}
