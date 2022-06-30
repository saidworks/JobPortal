import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Address } from '../models/address.model';
import { AddressService } from '../services/address.service';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.scss']
})
export class AddressComponent implements OnInit {

  addressForm!:FormGroup;
  address = new Address(1,"","","");
  public addresses!: Address[];
  public editAddress!: Address;
  public deleteAddress!: Address;
  errorMessage = '';
  hasAddress:boolean = false;

  constructor(private addressService:AddressService) { }

  ngOnInit(): void {
    this.addressForm = new FormGroup({
      street:new FormControl(null,[Validators.required,Validators.minLength(3)]),
      state:new FormControl(null,[Validators.required,Validators.minLength(3)]),
      city: new FormControl(null,[Validators.required,Validators.minLength(3)])
    })
    // console.log("I am here");
    this.getAddress();
  }

  onSubmit(){
    // console.log("I am here in submit")
      this.address.street = this.addressForm.value.street;
      this.address.city = this.addressForm.value.city;
      this.address.city = this.addressForm.value.state;
      // console.log(this.addressForm);
      this.addAddress();

  }

  addAddress(){
        this.addressService.addAddress(this.address).subscribe({
        next:(address:Address)=>{
          console.log(address);
          this.hasAddress = true;
          window.location.reload();
        },
        error:(err:HttpErrorResponse)=>{
          this.errorMessage = err.message;
        }
      })
  }

  public getAddress():void{

        this.addressService.getAddress().subscribe(
          (response: Address)=>{
            this.address = response;
            if(this.address){
              this.hasAddress = true;
            }
          },(error: HttpErrorResponse) =>{
            console.log(error.message);
          }
        )
  }

  public updateAddress(address:Address):void{
    console.log(address);
    this.addressService.updateAddress(address).subscribe({
      next:(address:Address)=>{
        console.log(address);
      },
      error:(err:HttpErrorResponse) =>{
       console.log(err.message);
      }
    })
  }

  public removeAddress(address:Address):void{
    this.addressService.deleteAddress(address.id).subscribe({
      next:(response:void)=>{
        console.log("address deleted")
      },
      error:(err:HttpErrorResponse)=>{
       console.log(err.message);
      }
    });
  }

  public onOpenModal(address: Address, mode: string): void {
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    if (mode === 'add') {
      button.setAttribute('data-target', '#addAddressModal');
    }
    if (mode === 'edit') {
      console.log(address);
      this.editAddress = address;
      button.setAttribute('data-target', '#updateAddressModal');
    }
    if (mode === 'delete') {
      this.deleteAddress = address;
      button.setAttribute('data-target', '#deleteAddressModal');
    }
    container?.appendChild(button);
    button.click();
  }

}
