import { createSlice, PayloadAction } from "@reduxjs/toolkit";

import { LoadingStatus, ProductResponse } from "../../types/types";
import { fetchCart } from "./cart-thunks";

export interface CartState {
    loadingState: LoadingStatus;
    totalPrice: number;
    cartItemsCount: number;
    products: Array<ProductResponse>;
}

export const initialState: CartState = {
    loadingState: LoadingStatus.LOADING,
    totalPrice: 0,
    cartItemsCount: 0,
    products: []
};

export const cartSlice = createSlice({
    name: "cart",
    initialState,
    reducers: {
        calculateCartPrice(state, action: PayloadAction<Array<ProductResponse>>) {
            state.totalPrice = calculatePrice(action.payload);
            state.loadingState = LoadingStatus.LOADED;
        },
        removeProductById(state, action: PayloadAction<number>) {
            const products = state.products.filter((product) => product.id !== action.payload);
            state.products = products;
            state.totalPrice = calculatePrice(products);
            state.loadingState = LoadingStatus.LOADED;
        },
        setCartItemsCount(state, action: PayloadAction<number>) {
            state.cartItemsCount = action.payload;
        },
        resetCartState(state) {
            state.loadingState = LoadingStatus.LOADING;
        }
    },
    extraReducers: (builder) => {
        builder.addCase(fetchCart.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(fetchCart.fulfilled, (state, action) => {
            state.totalPrice = calculatePrice(action.payload);
            state.cartItemsCount = action.payload.length;
            state.products = action.payload;
            state.loadingState = LoadingStatus.LOADED;
        });
    }
});

export const { calculateCartPrice, removeProductById, setCartItemsCount, resetCartState } = cartSlice.actions;
export default cartSlice.reducer;

const calculatePrice = (products: Array<ProductResponse>): number => {
    const productsFromLocalStorage: Map<number, number> = new Map(JSON.parse(<string>localStorage.getItem("products")));
    let total = 0;

    productsFromLocalStorage.forEach((value, key) => {
        const product = products.find((product) => product.id === key);

        if (product) {
            total += product.price * value;
        }
    });
    return total;
};
