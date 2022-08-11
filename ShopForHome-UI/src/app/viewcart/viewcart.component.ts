import { DiscountService } from './../discount.service';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-viewcart',
  templateUrl: './viewcart.component.html',
  styleUrls: ['./viewcart.component.css']
})
export class ViewcartComponent implements OnInit {
  total:number=0;
  productInCart:any[]
  value = '';
  constructor(private api:ApiService,private toast:ToastrService,
    private route:Router , private formBuilder:FormBuilder,private discountService:DiscountService) { }

  couponForm:FormGroup;
  ngOnInit(): void {
    this.loadData()
     this.couponForm = this.formBuilder.group({
      'coupon':['']
     })
  }
  discountPer=0;
  hello(){
    console.log(this.couponForm.value['coupon']);
    this.discountService.getByCouponCode(this.couponForm.value['coupon']).subscribe((response:any)=>{
      console.log(response);
      this.discountPer=response.discountPer;
    },(error)=>{
      console.log(error);
      
    })
  }

  removefromwishlist(id:number){
    this.api.deletefromcart(id).subscribe({
      next:resp=>{
        this.toast.success('Item deleted from cart')
        this.loadData()
      }
    })
  }

  placeorder(values:any){
    this.api.placeorder({customerid:sessionStorage.getItem('id')}).subscribe({
      next:resp=>{
        this.toast.success('Order placed successfully')
        this.route.navigate(['/history'])
      },
      error:err=>console.log(err)
    })
  }

  updateqty(cartid:number,qty:number){
    if(qty==0)
      this.toast.error('Cannot reduce quantity')
    else{
      this.api.updateqty(cartid,qty).subscribe({
        next:resp=>{
          this.toast.success('Quantity updated')
          this.loadData()
        },
        error:err=>console.log("error")
      });
    }
  }

  loadData(){
    this.api.getcart(sessionStorage.getItem('id')).subscribe({
      next:resp=>{
        this.productInCart=resp
        this.total=resp.reduce((sum,x)=>sum+x.qty*x.product.price,0)
      }
    })
  }

}
