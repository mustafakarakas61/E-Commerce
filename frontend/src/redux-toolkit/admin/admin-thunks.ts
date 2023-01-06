import { createAsyncThunk } from "@reduxjs/toolkit";

import { BaseUserResponse, HeaderResponse, ProductErrors, UserResponse } from "../../types/types";
import RequestService from "../../utils/request-service";
import {
    ADMIN_ADD,
    ADMIN_DELETE,
    ADMIN_EDIT,
    ADMIN_GRAPHQL_USER,
    ADMIN_GRAPHQL_USER_ALL,
    ADMIN_USER,
    ADMIN_USER_ALL
} from "../../constants/urlConstants";
import { setProduct } from "../product/product-slice";
import { removeProductById } from "../products/products-slice";
import { userByQuery, usersByQuery } from "../../utils/graphql-query/users-query";

export const addProduct = createAsyncThunk<{}, FormData, { rejectValue: ProductErrors }>(
    "admin/addProduct",
    async (data, thunkApi) => {
        try {
            const response = await RequestService.post(ADMIN_ADD, data, true, "multipart/form-data");
            return response.data;
        } catch (error) {
            return thunkApi.rejectWithValue(error.response.data);
        }
    }
);

export const updateProduct = createAsyncThunk<{}, FormData, { rejectValue: ProductErrors }>(
    "admin/updateProduct",
    async (data, thunkApi) => {
        try {
            const response = await RequestService.post(ADMIN_EDIT, data, true, "multipart/form-data");
            thunkApi.dispatch(setProduct(response.data));
            return response.data;
        } catch (error) {
            return thunkApi.rejectWithValue(error.response.data);
        }
    }
);

export const deleteProduct = createAsyncThunk<{}, number>("admin/deleteProduct", async (productId, thunkApi) => {
    const response = await RequestService.delete(`${ADMIN_DELETE}/${productId}`, true);
    thunkApi.dispatch(removeProductById(productId));
    return response.data;
});

export const fetchAllUsers = createAsyncThunk<HeaderResponse<BaseUserResponse>, number>("admin/fetchAllUsers", async (page) => {
    const response = await RequestService.get(`${ADMIN_USER_ALL}?page=${page}`, true);
    return {
        items: response.data,
        pagesCount: parseInt(response.headers["page-total-count"]),
        totalElements: parseInt(response.headers["page-total-elements"])
    };
});

export const fetchUserInfo = createAsyncThunk<UserResponse, string>("admin/fetchUserInfo", async (userId) => {
    const response = await RequestService.get(`${ADMIN_USER}/${userId}`, true);
    return response.data;
});

//GraphQL thunks
export const fetchUserInfoByQuery = createAsyncThunk<UserResponse, string>("admin/fetchUserInfoByQuery", async (userId) => {
    const response = await RequestService.post(ADMIN_GRAPHQL_USER, { query: userByQuery(userId) }, true);
    return response.data;
});

export const fetchAllUsersByQuery = createAsyncThunk<Array<BaseUserResponse>>("admin/fetchAllUsersByQuery", async () => {
    const response = await RequestService.post(ADMIN_GRAPHQL_USER_ALL, { query: usersByQuery }, true);
    return response.data;
});
