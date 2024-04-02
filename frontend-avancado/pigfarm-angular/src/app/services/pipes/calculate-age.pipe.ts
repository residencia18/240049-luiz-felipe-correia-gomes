import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'calculateAge'
})
export class CalculateAgePipe implements PipeTransform {
  transform(value: string): string {
    const birthDate = new Date(value);
    const currentDate = new Date();
    const diffMonths = (currentDate.getFullYear() - birthDate.getFullYear()) * 12 + (currentDate.getMonth() - birthDate.getMonth());
    return `${diffMonths} months`;
  }
}
