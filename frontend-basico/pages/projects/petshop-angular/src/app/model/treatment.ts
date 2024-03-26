export interface Treatment {
  originalValues(treatment: Treatment, originalValues: any): unknown;
  client: string;
  animal: string;
  service: string;
  date: string;
  observations: string;
  key?: string;
  editing?: boolean;
}
