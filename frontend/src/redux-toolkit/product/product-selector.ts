import {FullProductResponse, LoadingStatus, ReviewResponse} from "../../types/types";
import {RootState} from "../../store";
import {ProductState} from "./product-slice";

export const selectProductState = (state: RootState): ProductState => state.product;
export const selectProduct = (state: RootState): Partial<FullProductResponse> => state.product.product;
export const selectReviews = (state: RootState): Array<ReviewResponse> => state.product.reviews;
export const selectProductErrorMessage = (state: RootState): string => state.product.errorMessage;

export const selectLoadingStatus = (state: RootState): LoadingStatus => selectProductState(state).loadingState;
export const selectIsProductLoading = (state: RootState): boolean => selectLoadingStatus(state) === LoadingStatus.LOADING;
export const selectIsProductLoaded = (state: RootState): boolean => selectLoadingStatus(state) === LoadingStatus.LOADED;
export const selectProductError = (state: RootState): boolean => selectLoadingStatus(state) === LoadingStatus.ERROR;
