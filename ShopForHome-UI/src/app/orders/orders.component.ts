import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';
import {jsPDF} from "jspdf";
import { ViewChild,ElementRef } from '@angular/core';


@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})


export class OrdersComponent implements OnInit {

  @ViewChild('content',{static:false}) el!:ElementRef;
  listOrderedProduct:any[]
  constructor(private api:ApiService) { }

  ngOnInit(): void {
    this.loadData()
  }

   loadData(){
    this.api.allorders().subscribe({
      next:resp=>{
        this.listOrderedProduct=resp       
      }
    })


   

  }

  makePDF(){
    let pdf=new jsPDF('p','pt','a3');
    pdf.html(this.el.nativeElement,{
      callback:(pdf)=>{
        pdf.save("sales report");
      }
    });
  }

}
