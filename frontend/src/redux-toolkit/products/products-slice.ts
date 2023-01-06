import { createSlice, PayloadAction } from "@reduxjs/toolkit";

import { LoadingStatus, ProductResponse } from "../../types/types";
import {
    fetchProducts,
    fetchProductsByFilterParams,
    fetchProductsByIds,
    fetchProductsByIdsQuery,
    fetchProductsByInputText,
    fetchProductsByQuery
} from "./products-thunks";

export interface ProductsState {
    products: Array<ProductResponse>;
    pagesCount: number;
    totalElements: number;
    loadingState: LoadingStatus;
}

export const initialState: ProductsState = {
    products: [],
    pagesCount: 1,
    totalElements: 0,
    loadingState: LoadingStatus.LOADING
};

export const productsSlice = createSlice({
    name: "products",
    initialState,
    reducers: {
        setProducts(state, action: PayloadAction<Array<ProductResponse>>) {
            state.products = action.payload;
            state.loadingState = LoadingStatus.LOADED;
        },
        removeProductById(state, action: PayloadAction<number>) {
            state.products = state.products.filter((product) => product.id !== action.payload);
            state.loadingState = LoadingStatus.LOADED;
        },
        resetProductsState: () => initialState
    },
    extraReducers: (builder) => {
        builder.addCase(fetchProducts.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(fetchProducts.fulfilled, (state, action) => {
            state.products = action.payload.items;
            state.pagesCount = action.payload.pagesCount;
            state.totalElements = action.payload.totalElements;
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(fetchProductsByIds.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(fetchProductsByIds.fulfilled, (state, action) => {
            state.products = action.payload;
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(fetchProductsByFilterParams.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(fetchProductsByFilterParams.fulfilled, (state, action) => {
            state.products = action.payload.items;
            state.pagesCount = action.payload.pagesCount;
            state.totalElements = action.payload.totalElements;
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(fetchProductsByInputText.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(fetchProductsByInputText.fulfilled, (state, action) => {
            state.products = action.payload.items;
            state.pagesCount = action.payload.pagesCount;
            state.totalElements = action.payload.totalElements;
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(fetchProductsByQuery.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(fetchProductsByQuery.fulfilled, (state, action) => {
            state.products = action.payload;
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(fetchProductsByIdsQuery.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(fetchProductsByIdsQuery.fulfilled, (state, action) => {
            state.products = action.payload;
            state.loadingState = LoadingStatus.LOADED;
        });
    }
});

export const { setProducts, removeProductById, resetProductsState } = productsSlice.actions;
export default productsSlice.reducer;
