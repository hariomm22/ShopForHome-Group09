import { ApiService } from './../api.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-csvupload',
  templateUrl: './csvupload.component.html',
  styleUrls: ['./csvupload.component.css']
})
export class CsvuploadComponent implements OnInit {
  selectedFile: any;

  constructor(private apiService:ApiService) { }

  ngOnInit(): void {
  }

  onUpload(){
    console.log(this.selectedFile);
    this.apiService.uploadFile(this.selectedFile).subscribe((response)=>{
      console.log("Hariom bhai "+response);
      alert("CSV Upload..!");
      
    },(error)=>{
      console.log(error);
      alert("Error>>")
      
    })
  }

  isFormValid(){}
  
  saveFile(event:any){
    this.selectedFile = event.target.files[0];
  }

}
