import { Component, OnInit } from '@angular/core';
import { Category } from '../../_models/category';
import { RouterLink } from '@angular/router';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-category-menu',
  standalone: true,
  imports: [RouterLink, NgFor],
  templateUrl: './category-menu.component.html',
  styleUrl: './category-menu.component.css'
})
export class CategoryMenuComponent implements OnInit{
  
  categories!: Category[];

  ngOnInit(): void {
    this.listProductCategories();
  }


  listProductCategories() {

    this.categories = [
      {
        id: 1,
        categoryName: "cat 1"
      },
      {
        id: 2,
        categoryName: "cat 2"
      },
      {
        id: 3,
        categoryName: "cat 3"
      }
    ]

  };

}
