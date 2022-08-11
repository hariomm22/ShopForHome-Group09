import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
api_URL:string="http://localhost:8080/api/";

  constructor(private http:HttpClient) { }

  validate(data:any){
    return this.http.post<any>(this.api_URL+"customers/validate",data);
  }

  validateadmin(data:any){
    return this.http.post<any>(this.api_URL+"admin/validate",data);
  }

  //categories
  savecategory(data:any){
    return this.http.post<any>(this.api_URL+"categories",data);
  }

  listcategories(){
    return this.http.get<any[]>(this.api_URL+"categories");
  }

  deletecategory(id:number){
    return this.http.delete<any>(this.api_URL+"categories/"+id);
  }

  //customer
  register(data:any){
    return this.http.post<any>(this.api_URL+"customers",data);
  }

  listcustomers(){
    return this.http.get<any[]>(this.api_URL+"customers")
  }
  
  getcustomerdetails(id:any){
    return this.http.get<any>(this.api_URL+"customers/"+id)
  }

  //products
  saveproduct(data:any){
    new HttpHeaders().set("Accept", "application/json");
    return this.http.post<any>(this.api_URL+"products",data)
  }

  listproducts(){
    return this.http.get<any[]>(this.api_URL+"products")
  }

  catproducts(catid:number){
    return this.http.get<any[]>(this.api_URL+"products/cats?catid="+catid)
  }

  searchproducts(search:string){
    return this.http.get<any[]>(this.api_URL+"products?search="+search)
  }

  deleteproduct(id:number){
    return this.http.delete<any>(this.api_URL+"products/"+id)
  }

  //wishlist
  addtowishlist(data:any){
    return this.http.post<any>(this.api_URL+"wishlist",data)
  }

  getwishlist(id:any){
    return this.http.get<any[]>(this.api_URL+"wishlist?custid="+id)
  }

  deletefromwishlist(id:any){
    return this.http.delete<any>(this.api_URL+"wishlist/"+id)
  }

  //cart
  addtocart(data:any){
    return this.http.post<any>(this.api_URL+"cart",data)
  }

  getcart(id:any){
    return this.http.get<any[]>(this.api_URL+"cart?custid="+id)
  }

  deletefromcart(id:any){
    return this.http.delete<any>(this.api_URL+"cart/"+id)
  }

  updateqty(cartid:number,qty:number){
    return this.http.get<any>(this.api_URL+"cart/updateqty?cartid="+cartid+"&qty="+qty);
  }

  //orders
  placeorder(data:any){
    return this.http.post<any>(this.api_URL+"orders",data);
  }

  orderhistory(custid:any){
    return this.http.get<any[]>(this.api_URL+"orders?custid="+custid);
  }

  allorders(){
    return this.http.get<any[]>(this.api_URL+"orders");
  }


  uploadFile(file:File){
    const formData = new FormData(); 
    formData.append("file",file);
    return this.http.post(this.api_URL+"products/bulk",formData);
  }

}
