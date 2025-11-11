import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { ProductsList } from './pages/products-list/products-list';
import { Product } from './pages/product/product';
import { OrderCheckout } from './pages/order-checkout/order-checkout';
import { Main } from './layout/main/main';
import { Checkout } from './layout/checkout/checkout';

export const routes: Routes = [
    {
        path: '',
        component: Main,
        children: [
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
        ]
    },
     {
         path: 'checkout',
         component: Checkout,
         children: [
            { path: '', 
              component: OrderCheckout 
            },
         ]
     }
];
