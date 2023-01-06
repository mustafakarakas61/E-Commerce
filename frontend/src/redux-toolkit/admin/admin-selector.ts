import { BaseUserResponse, LoadingStatus, ProductErrors, UserResponse } from "../../types/types";
import { RootState } from "../../store";
import { AdminState } from "./admin-slice";

export const selectAdminState = (state: RootState): AdminState => state.admin;
export const selectAdminStateUsers = (state: RootState): Array<BaseUserResponse> => selectAdminState(state).users;
export const selectAdminStateUser = (state: RootState): Partial<UserResponse> => selectAdminState(state).user;
export const selectPagesCount = (state: RootState): number => selectAdminState(state).pagesCount;
export const selectTotalElements = (state: RootState): number => selectAdminState(state).totalElements;
export const selectAdminStateErrors = (state: RootState): Partial<ProductErrors> => selectAdminState(state).errors;
export const selectIsProductAdded = (state: RootState): boolean => selectAdminState(state).isProductAdded;
export const selectIsProductEdited = (state: RootState): boolean => selectAdminState(state).isProductEdited;
export const selectIsProductDeleted = (state: RootState): boolean => selectAdminState(state).isProductDeleted;
export const selectIsAdminStateLoading = (state: RootState): boolean => selectAdminState(state).loadingState === LoadingStatus.LOADING;
