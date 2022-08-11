import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DiscountService {

  constructor(private http:HttpClient) { }
  api_URL="http://localhost:8081/api/discount";

  addCoupon(Discount:any){
    return this.http.post<JSON>(this.api_URL,Discount);
  }

  deleteCoupon(id:number){
    return this.http.delete(this.api_URL+"/"+id);
  }

  getAllCoupon(){
    return this.http.get(this.api_URL);
  }

  getCoupon(id:number){
    return this.http.get(this.api_URL+"/"+id);
  }

  getByCouponCode(code:string){
    return this.http.get(this.api_URL+"/coupon/"+code);
  }
}
