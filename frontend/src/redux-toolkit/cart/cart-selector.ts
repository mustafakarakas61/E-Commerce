import { LoadingStatus, ProductResponse } from "../../types/types";
import { RootState } from "../../store";
import { CartState } from "./cart-slice";

export const selectCartState = (state: RootState): CartState => state.cart;
export const selectTotalPrice = (state: RootState): number => selectCartState(state).totalPrice;
export const selectCartItemsCount = (state: RootState): number => selectCartState(state).cartItemsCount;
export const selectCartItems = (state: RootState): Array<ProductResponse> => selectCartState(state).products;
export const selectIsCartLoading = (state: RootState): boolean => selectCartState(state).loadingState === LoadingStatus.LOADING;
