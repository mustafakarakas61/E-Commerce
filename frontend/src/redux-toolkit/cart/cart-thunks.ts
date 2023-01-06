import { createAsyncThunk } from "@reduxjs/toolkit";

import { ProductResponse } from "../../types/types";
import RequestService from "../../utils/request-service";
import { USERS_CART } from "../../constants/urlConstants";

export const fetchCart = createAsyncThunk<Array<ProductResponse>, Array<number>>("cart/fetchCart", async (productIds) => {
    const response = await RequestService.post(USERS_CART, productIds);
    return response.data;
});
