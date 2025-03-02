import { NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [RouterLink, NgFor, NgbPaginationModule],
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit{

  // new properties for pagination
  thePageNumber: number = 1;
  thePageSize: number = 5;
  theTotalElements: number = 0;

  products!: any[];

  ngOnInit(): void {
    this.listProducts();
  }

  listProducts(){
    this.products = [
      {
        id: 1,
        name: "product 1",
        imageUrl: "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg",
        unitPrice: 298
      },
      {
        id: 2,
        name: "product 1",
        imageUrl: "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg",
        unitPrice: 298
      },
      {
        id: 3,
        name: "product 1",
        imageUrl: "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg",
        unitPrice: 298
      },
      {
        id: 4,
        name: "product 1",
        imageUrl: "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg",
        unitPrice: 298
      },
      {
        id: 5,
        name: "product 1",
        imageUrl: "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_1280.jpg",
        unitPrice: 298
      }
    ]
  }

  updatePageSize(pageSize: any) {
    // todo
  }

  addToCart(val: any) {
    
  }

}
