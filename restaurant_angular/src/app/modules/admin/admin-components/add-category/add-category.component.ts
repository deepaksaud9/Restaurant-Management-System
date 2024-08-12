import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.scss']
})
export class AddCategoryComponent implements OnInit {

  categoryForm: FormGroup;

  constructor(
    private service: AdminService,
    private fb: FormBuilder,
  ) { }

  ngOnInit(): void {
    this.categoryForm = this.fb.group({
      name:[null, Validators.required],
      description:[null, Validators.required],
    })
  }

  postCategory(){
    console.log(this.categoryForm.value)
  }

}
