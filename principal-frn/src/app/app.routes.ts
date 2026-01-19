import { Routes } from '@angular/router';
import { Home } from './pages/home/home';
import { ProductsList } from './pages/products-list/products-list';
import { Product } from './pages/product/product';
import { OrderCheckout } from './pages/order-checkout/order-checkout';
import { Main } from './layout/main/main';
import { Checkout } from './layout/checkout/checkout';
import { AccountAccess } from './layout/account-access/account-access';
import { Profile } from './pages/profile/profile';
import { ProfileList } from './pages/profile-list/profile-list';
import { ProfileHist } from './pages/profile-hist/profile-hist';
import { ProfileAddress } from './pages/profile-address/profile-address';
import { NewAddress } from './components/new-address/new-address';
import { ProfileOrder } from './pages/profile-order/profile-order';
import { ProfileOrdersShipped } from './pages/profile-orders-shipped/profile-orders-shipped';
import { ProfileOrdersDelivered } from './pages/profile-orders-delivered/profile-orders-delivered';
import { Insert } from './layout/insert/insert';
import { InsertProduct } from './pages/insert-product/insert-product';
import { InsertComment } from './pages/insert-comment/insert-comment';
import { CreateUser } from './pages/create-user/create-user';
import { Login } from './pages/login/login';
import { ProductsFilterPrice } from './pages/products-filter-price/products-filter-price';
import { ProductsBestSellings } from './pages/products-best-sellings/products-best-sellings';
import { ProductsDiscount } from './pages/products-discount/products-discount';
import { ProfileListProd } from './pages/profile-list-prod/profile-list-prod';

export const routes: Routes = [
    {
        path: '',
        component: Main,
        children: [
          {
              path: "",
              component: Home
          },
          {
              path: "home",
              component: Home
          },
          {
              path: "products-list",
              component: ProductsList
          },
          {
              path: "products-filterPrice",
              component: ProductsFilterPrice
          },
          {
              path: "products-best-selling",
              component: ProductsBestSellings
          },
          {
              path: "products-discount",
              component: ProductsDiscount
          },
          {
              path: "product",
              component: Product
          },
          {
              path: "product/insert-comment",
              component: InsertComment
          }
        ]
    },
    {
        path: 'checkout',
        component: Checkout,
        children: [
           {
            path: '',
            component: OrderCheckout
           }
        ]
    },
    {
        path: 'insert',
        component: Insert,
        children: [
           {
            path: '',
            component: InsertProduct
           },
           {
            path: 'create-user',
            component: CreateUser
           },
           {
            path: 'login',
            component: Login
           }
        ]
    },
    {
        path: 'account',
        component: AccountAccess,
        children: [
           {
            path: '',
            component: Profile
           },
           {
            path: 'your-list',
            component: ProfileList
           },
           {
            path: 'your-list/list',
            component: ProfileListProd
           },
           {
            path: 'history',
            component: ProfileHist
           },
           {
            path: 'address',
            component: ProfileAddress
           },
           {
            path: 'new-address',
            component: NewAddress
           },
           {
            path: 'orders',
            component: ProfileOrder
           },
           {
            path: 'orders-shipped',
            component: ProfileOrdersShipped
           },
           {
            path: 'orders-delivered',
            component: ProfileOrdersDelivered
           }
        ]
    }
];
