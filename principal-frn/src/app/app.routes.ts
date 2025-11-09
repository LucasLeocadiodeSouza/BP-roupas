import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { ProductsList } from './pages/products-list/products-list';
import { Product } from './pages/product/product';

export const routes: Routes = [
    {
        path: "home",
        component: Home
    },
    {
        path: "products-list",
        component: ProductsList
    },
    {
        path: "product",
        component: Product
    },
    {
        path: "",
        component: Home
    }
];
