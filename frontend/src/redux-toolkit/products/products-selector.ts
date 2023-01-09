import { ProductsState } from "./products-slice";
import { LoadingStatus, ProductResponse } from "../../types/types";
import { RootState } from "../../store";

export const selectProductsState = (state: RootState): ProductsState => state.products;
export const selectProducts = (state: RootState): Array<ProductResponse> => selectProductsState(state).products;
export const selectPagesCount = (state: RootState): number => selectProductsState(state).pagesCount;
export const selectTotalElements = (state: RootState): number => selectProductsState(state).totalElements;
export const selectFilename = (state: RootState): string => selectProductsState(state).filename;
export const selectIsProductsLoading = (state: RootState): boolean => selectProductsState(state).loadingState === LoadingStatus.LOADING;
