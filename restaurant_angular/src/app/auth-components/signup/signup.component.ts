import { Component, OnInit } from '@angular/core';
import { Validators } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { AuthService } from 'src/app/auth-services/auth-service/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {

  isSpinning: boolean;
  validateForm: FormGroup;

  confirmationValidator = (control: FormControl) : {[s: string]: boolean } => {
    if(!control.value){
      return {required : true}
    } else if (control.value !== this.validateForm.controls['password'].value){
      return{ confirm: true, error: true}
    }
    return {}
  }

  constructor(
            private service: AuthService,
            private fb: FormBuilder,
            private notification: NzNotificationService
              ){}

  ngOnInit(){
      this.validateForm = this.fb.group({
      email:["",Validators.required],
      password:["",Validators.required],
      checkPassword:["",[Validators.required, this.confirmationValidator]],
      name:["",Validators.required],
            })
            }

            register(){
              console.log(this.validateForm.value)
              this.service.signup(this.validateForm.value).subscribe((res) => {
                console.log(res);
                if(res != null){
                  this.notification.success("SUCCESS","YOU ARE REGISTERED SUCCESSFULLY",{ nzDuration: 5000})
                }else{
                  this.notification.error("ERROR","REGISTER UNSUCCESSFULL",{ nzDuration: 5000})
                
                }
              })  
            }
}