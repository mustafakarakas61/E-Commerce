import React, { FC, ReactElement, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {Button, Col, Layout, Pagination, RadioChangeEvent, Row, Typography} from "antd";
import { CheckboxValueType } from "antd/lib/checkbox/Group";
import { useLocation } from "react-router-dom";

import MenuCheckboxSection from "./MenuSection/MenuCheckboxSection";
import { selectIsProductsLoading, selectProducts } from "../../redux-toolkit/products/products-selector";
import { FilterParamsType } from "../../types/types";
import { fetchProductsByFilterParams, fetchProductsByInputText } from "../../redux-toolkit/products/products-thunks";
import { resetProductsState } from "../../redux-toolkit/products/products-slice";
import MenuRadioSection from "./MenuSection/MenuRadioSection";
import MenuSorter from "./MenuSorter/MenuSorter";
import ProductCard from "../../components/ProductCard/ProductCard";
import SelectSearchData from "../../components/SelectSearchData/SelectSearchData";
import InputSearch from "../../components/InputSearch/InputSearch";
import Spinner from "../../components/Spinner/Spinner";
import { MAX_PAGE_VALUE, usePagination } from "../../hooks/usePagination";
import { types, productTypes, price } from "./MenuData";
import { useSearch } from "../../hooks/useSearch";
import "./Menu.css";

export enum CheckboxCategoryFilter {
    PRODUCTTYPES = "PRODUCTTYPES",
    TYPES = "TYPES"
}

const Menu: FC = (): ReactElement => {
    const dispatch = useDispatch();
    const products = useSelector(selectProducts);
    const isProductsLoading = useSelector(selectIsProductsLoading);
    const location = useLocation<{ id: string }>();
    const [filterParams, setFilterParams] = useState<FilterParamsType>({
        types: [],
        productTypes: [],
        page:0,
        perPage:5,
        prices: [1, 999]
    });
    const [sortByPrice, setSortByPrice] = useState<boolean>(false);
    const { currentPage, totalElements, handleChangePagination, resetPagination } = usePagination();


    const { searchValue, searchTypeValue, resetFields, form, onSearch, handleChangeSelect } = useSearch();

    useEffect(() => {
        const productData = location.state.id;
        if (productData === "all") {
            dispatch(fetchProductsByFilterParams({ ...filterParams, sortByPrice, currentPage: 0 }));
        } else {
            dispatch(
                fetchProductsByFilterParams({
                    ...filterParams,
                    productTypes: [...filterParams.productTypes, productData],
                    sortByPrice,
                    currentPage: 0
                })
            );
            setFilterParams((prevState) => ({ ...prevState, producers: [...prevState.productTypes, productData] }));
        }
        window.scrollTo(0, 0);

        return () => {
            dispatch(resetProductsState());
        };
    }, []);

    useEffect(() => {
        resetPagination();
    }, [filterParams, sortByPrice]);

    const onChangeCheckbox = (checkedValues: CheckboxValueType[], category: CheckboxCategoryFilter): void => {
        if (CheckboxCategoryFilter.PRODUCTTYPES === category) {
            setFilterParams((prevState) => {
                const filter = { ...prevState, productTypes: [...(checkedValues as string[])] };
                dispatch(fetchProductsByFilterParams({ ...filter, sortByPrice, currentPage: 0 }));
                return filter;
            });
        } else if (CheckboxCategoryFilter.TYPES === category) {
            setFilterParams((prevState) => {
                const filter = { ...prevState, types: [...(checkedValues as string[])] };
                dispatch(fetchProductsByFilterParams({ ...filter, sortByPrice, currentPage: 0 }));
                return filter;
            });
        }
        resetFields();
    };

    const changePagination = (page: number, pageSize: number): void => {
         if (searchValue) {
             filterParams.perPage = pageSize
             filterParams.page = page
             dispatch( fetchProductsByInputText({ searchType: searchTypeValue, text: searchValue, currentPage: page - 1 }));
         } else {
             filterParams.perPage = pageSize
             filterParams.page = page
             dispatch(fetchProductsByFilterParams({ ...filterParams, sortByPrice, currentPage: page - 1 }));
         }
         handleChangePagination(page, pageSize);
    };

    const onChangeRadio = (event: RadioChangeEvent): void => {
        setFilterParams((prevState) => {
            const filter = { ...prevState, prices: event.target.value };
            dispatch(fetchProductsByFilterParams({ ...filter, sortByPrice, currentPage: 0 }));
            return filter;
        });
        resetFields();
    };

    const handleChangeSortPrice = (event: RadioChangeEvent): void => {
        dispatch(fetchProductsByFilterParams({ ...filterParams, sortByPrice: event.target.value, currentPage: 0 }));
        setSortByPrice(event.target.value);
        resetFields();
    };


    return (
        <Layout>
            <Layout.Content className={"login-content"}>
                <Typography.Title level={2}>Ürünler</Typography.Title>
                <Row gutter={32}>
                    <Col span={6}>
                        <MenuCheckboxSection
                            title={"Kategori"}
                            onChange={onChangeCheckbox}
                            data={types}
                            category={CheckboxCategoryFilter.TYPES}
                            selectedValues={filterParams.types}
                        />
                        <MenuRadioSection title={"Fiyat"} onChange={onChangeRadio} data={price} />
                    </Col>
                    <Col span={18}>
                        <Row>
                            <Col span={9}>
                                <SelectSearchData handleChangeSelect={handleChangeSelect} />
                            </Col>
                            <Col span={10}>
                                <InputSearch onSearch={onSearch} form={form} />
                            </Col>
                        </Row>
                        <Row style={{ marginTop: 16, marginBottom: 16 }}>
                            <Col span={16}>
                                <Pagination
                                    current={currentPage}
                                    itemRender={(page, type, originalElement) => {
                                        if (type === 'page') {
                                            return (
                                                <a href={`/menu`}>
                                                    {page}
                                                </a>
                                            );
                                        }
                                        return originalElement;
                                    }}
                                    total={totalElements}
                                    defaultCurrent={0}
                                    defaultPageSize={5}
                                    pageSizeOptions={[5,10,15]}
                                    showSizeChanger={true}
                                    onChange={changePagination}
                                />

                            </Col>
                            <Col span={8}>
                                <MenuSorter onChange={handleChangeSortPrice} sortByPrice={sortByPrice} />
                            </Col>
                        </Row>
                        <Row gutter={[32, 32]}>
                            {isProductsLoading ? (
                                <Spinner />
                            ) : (
                                products.map((product) => (
                                    <ProductCard key={product.id} product={product} colSpan={8} />
                                ))
                            )}
                        </Row>
                        <Row style={{ marginTop: 16, marginBottom: 16 }}>
                            <Pagination
                                current={currentPage}
                                pageSize={MAX_PAGE_VALUE}
                                total={totalElements}
                                showSizeChanger={false}
                                onChange={changePagination}
                            />
                        </Row>
                    </Col>
                </Row>
            </Layout.Content>
        </Layout>
    );
};

export default Menu;
