import { createSlice, PayloadAction } from "@reduxjs/toolkit";

import { BaseUserResponse, LoadingStatus, ProductErrors, UserResponse } from "../../types/types";
import {
    addProduct,
    deleteProduct,
    fetchAllUsers,
    fetchAllUsersByQuery,
    fetchUserInfo,
    fetchUserInfoByQuery,
    updateProduct
} from "./admin-thunks";

export interface AdminState {
    users: Array<BaseUserResponse>;
    user: Partial<UserResponse>;
    errors: Partial<ProductErrors>;
    pagesCount: number;
    totalElements: number;
    isProductAdded: boolean;
    isProductEdited: boolean;
    isProductDeleted: boolean;
    loadingState: LoadingStatus;
}

export const initialState: AdminState = {
    users: [],
    user: {},
    errors: {},
    pagesCount: 1,
    totalElements: 0,
    isProductAdded: false,
    isProductEdited: false,
    isProductDeleted: false,
    loadingState: LoadingStatus.LOADING
};

export const adminSlice = createSlice({
    name: "admin",
    initialState,
    reducers: {
        setAdminLoadingState(state, action: PayloadAction<LoadingStatus>) {
            state.loadingState = action.payload;
        },
        resetAdminState(state, action: PayloadAction<LoadingStatus>) {
            state.users = [];
            state.user = {};
            state.errors = {};
            state.isProductAdded = false;
            state.isProductEdited = false;
            state.isProductDeleted = false;
            state.pagesCount = 1;
            state.totalElements = 0;
            state.loadingState = action.payload;
        }
    },
    extraReducers: (builder) => {
        builder.addCase(addProduct.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(addProduct.fulfilled, (state) => {
            state.isProductAdded = true;
            state.errors = {};
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(addProduct.rejected, (state, action) => {
            state.isProductAdded = false;
            state.errors = action.payload!;
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(updateProduct.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(updateProduct.fulfilled, (state) => {
            state.isProductEdited = true;
            state.errors = {};
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(updateProduct.rejected, (state, action) => {
            state.isProductEdited = false;
            state.errors = action.payload!;
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(deleteProduct.fulfilled, (state) => {
            state.isProductDeleted = true;
            state.errors = {};
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(fetchAllUsers.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(fetchAllUsers.fulfilled, (state, action) => {
            state.users = action.payload.items;
            state.pagesCount = action.payload.pagesCount;
            state.totalElements = action.payload.totalElements;
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(fetchUserInfo.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(fetchUserInfo.fulfilled, (state, action) => {
            state.user = action.payload;
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(fetchUserInfoByQuery.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(fetchUserInfoByQuery.fulfilled, (state, action) => {
            state.user = action.payload;
            state.loadingState = LoadingStatus.LOADED;
        });
        builder.addCase(fetchAllUsersByQuery.pending, (state) => {
            state.loadingState = LoadingStatus.LOADING;
        });
        builder.addCase(fetchAllUsersByQuery.fulfilled, (state, action) => {
            state.users = action.payload;
            state.loadingState = LoadingStatus.LOADED;
        });
    }
});

export const { setAdminLoadingState, resetAdminState } = adminSlice.actions;
export default adminSlice.reducer;
