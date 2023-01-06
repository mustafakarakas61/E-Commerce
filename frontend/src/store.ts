import { configureStore } from "@reduxjs/toolkit";

import productsSlice, { ProductsState } from "./redux-toolkit/products/products-slice";
import productSlice, { ProductState } from "./redux-toolkit/product/product-slice";
import userSlice, { UserState } from "./redux-toolkit/user/user-slice";
import ordersSlice, { OrdersState } from "./redux-toolkit/orders/orders-slice";
import orderSlice, { OrderState } from "./redux-toolkit/order/order-slice";
import cartSlice, { CartState } from "./redux-toolkit/cart/cart-slice";
import authSlice, { AuthState } from "./redux-toolkit/auth/auth-slice";
import adminSlice, { AdminState } from "./redux-toolkit/admin/admin-slice";

export interface RootState {
    admin: AdminState;
    auth: AuthState;
    cart: CartState;
    order: OrderState;
    orders: OrdersState;
    product: ProductState;
    products: ProductsState;
    user: UserState;
}

export const storeReducer = {
    admin: adminSlice,
    auth: authSlice,
    cart: cartSlice,
    order: orderSlice,
    orders: ordersSlice,
    product: productSlice,
    products: productsSlice,
    user: userSlice,
};

export const store = configureStore({
    reducer: storeReducer
});
