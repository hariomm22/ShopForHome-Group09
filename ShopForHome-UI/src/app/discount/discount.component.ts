import { ToastrService } from 'ngx-toastr';
import { DiscountService } from './../discount.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { variable } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-discount',
  templateUrl: './discount.component.html',
  styleUrls: ['./discount.component.css']
})
export class DiscountComponent implements OnInit {

  constructor(private discountService:DiscountService,private formBuilder:FormBuilder,private toast:ToastrService) { }
  discountCoupon:FormGroup;
  listOfCoupon:any;
  ngOnInit(): void {
    this.discountCoupon=this.formBuilder.group({
      couponCode:['',Validators.required],
      discountPer:['',Validators.required]
    })
    this.getCoupon()
  }



  createCoupon(){
    this.discountService.addCoupon(this.discountCoupon.value).subscribe((response)=>{
      console.log("hariom "+response)
      location.reload();
      this.toast.success("Coupon added successfully");   
    },(error)=>{
      console.log(error);
      location.reload();
      this.toast.success("Coupon added successfully"); 
     
    })
  }




  deleteCoupon(id:number){
      let flag=confirm("Are Sure..!")
      if(flag){
      this.discountService.deleteCoupon(id).subscribe((response)=>{
      console.log(response);
      location.reload(); 
      this.toast.success("Coupon Deleted successfully"); 
    },(error)=>{
      console.log(error);
      location.reload();
      this.toast.success("Coupon Deleted successfully"); 
      
    })
  }
  }
  getCoupon(){
    this.discountService.getAllCoupon().subscribe((response)=>{
      console.log(response);
      this.listOfCoupon=response; 
    },(error)=>{
      console.log(error);      
    })
  }

  isFormValid(){
    if(this.discountCoupon.valid){
      return false;
    }
    return true;
  }

}
