export class Address {
  id:number;
  street:string;
  city:string;
  state:string;
  constructor(id:number,street:string,city:string,state:string){
    this.id = id;
    this.city = city;
    this.state = state;
    this.street = street;
  }
}
