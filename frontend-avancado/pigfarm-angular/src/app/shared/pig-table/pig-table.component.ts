import { Component, Input, OnInit } from '@angular/core';
import { AvatarModule, TableModule } from '@coreui/angular';
import { UtilsPipesModule } from '../utils-pipes/utils-pipes.module';
import { IPig } from 'src/app/model/pig/pig.interface';
import { PigRestService } from 'src/app/services/rest/pig-rest.service';

@Component({
  selector: 'pig-table',
  templateUrl: './pig-table.component.html',
})
export class PigTableComponent implements OnInit {
  @Input() pigRef: string = '';

  pigSelected!: IPig;
  avatar: string = './assets/img/avatars/pig.png';

  constructor(
    private pigsRestService: PigRestService,
  ) {}

  ngOnInit(): void {
    if (this.pigRef) {
      this.pigsRestService.getPigByID(this.pigRef).subscribe((pig: IPig) => {
        this.pigSelected = pig;
      });
    }
  }
}
