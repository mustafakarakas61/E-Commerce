import React, { FC, ReactElement, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Button, Col, Form, notification, Row, Upload } from "antd";
import { PlusSquareFilled, PlusSquareOutlined, UploadOutlined } from "@ant-design/icons";
import { UploadChangeParam } from "antd/lib/upload/interface";

import {
    selectAdminStateErrors,
    selectIsAdminStateLoading,
    selectIsProductAdded
} from "../../../redux-toolkit/admin/admin-selector";
import { resetAdminState, setAdminLoadingState } from "../../../redux-toolkit/admin/admin-slice";
import { LoadingStatus } from "../../../types/types";
import { addProduct } from "../../../redux-toolkit/admin/admin-thunks";
import ContentTitle from "../../../components/ContentTitle/ContentTitle";
import AddFormInput from "./AddFormInput";
import AddFormSelect from "./AddFormSelect";
import IconButton from "../../../components/IconButton/IconButton";

type AddProductData = {
    productTitle: string;
    producer: string;
    year: string;
    city: string;
    type: string;
    productType: string;
    colors: string;
    price: string;
};

const AddProduct: FC = (): ReactElement => {
    const dispatch = useDispatch();
    const isProductAdded = useSelector(selectIsProductAdded);
    const ispProductLoading = useSelector(selectIsAdminStateLoading);
    const productErrors = useSelector(selectAdminStateErrors);
    const [file, setFile] = React.useState<string>("");

    useEffect(() => {
        dispatch(setAdminLoadingState(LoadingStatus.LOADED));

        return () => {
            dispatch(resetAdminState(LoadingStatus.LOADING));
        };
    }, []);

    useEffect(() => {
        if (isProductAdded) {
            window.scrollTo(0, 0);
            notification.success({
                message: "Product added",
                description: "Product successfully added!"
            });
            dispatch(resetAdminState(LoadingStatus.SUCCESS));
        }
    }, [isProductAdded]);

    const onFormSubmit = (data: AddProductData): void => {
        const bodyFormData: FormData = new FormData();
        // @ts-ignore
        bodyFormData.append("file", { file });
        bodyFormData.append(
            "product",
            new Blob([JSON.stringify({ ...data, productRating: 0 })], { type: "application/json" })
        );

        dispatch(addProduct(bodyFormData));
    };

    const handleUpload = ({ file }: UploadChangeParam<any>): void => {
        setFile(file);
    };

    return (
        <>
            <ContentTitle title={"Add product"} titleLevel={4} icon={<PlusSquareOutlined />} />
            <Form onFinish={onFormSubmit}>
                <Row gutter={32}>
                    <Col span={12}>
                        <AddFormInput
                            title={"Ürün başlığı"}
                            name={"productTitle"}
                            error={productErrors.productTitleError}
                            placeholder={"Ürün başlığını giriniz"}
                            disabled={ispProductLoading}
                        />
                        <AddFormInput
                            title={"Üretim yılı"}
                            name={"year"}
                            error={productErrors.yearError}
                            placeholder={"Üretim yılını giriniz"}
                            disabled={ispProductLoading}
                        />
                        <AddFormSelect
                            title={"Ürün Kategorisi"}
                            name={"type"}
                            error={productErrors.typeError}
                            placeholder={"Elektrikli ev aletleri"}
                            disabled={ispProductLoading}
                            values={['Elektrikli ev aletleri', 'Elektronik', 'Ev ve Yaşam', 'Giyim', 'Hediyelik', 'Kitaplar', 'Kozmetik', 'Spor']}
                        />
                        <AddFormSelect
                            title={"Ürün tipi"}
                            name={"productType"}
                            error={productErrors.productTypeError}
                            placeholder={"Ayakkabı"}
                            disabled={ispProductLoading}
                            values={["Ayakkabı", "Bilgisayar", "Buzdolabı", "Çamaşır makinesi"]}
                        />
                        <AddFormInput
                            title={"Renkler"}
                            name={"colors"}
                            error={productErrors.colorsError}
                            placeholder={"Ürüne ait renk seçenekleri"}
                            disabled={ispProductLoading}
                        />
                        <AddFormInput
                            title={"Fiyat"}
                            name={"price"}
                            error={productErrors.priceError}
                            placeholder={"Ürünün fiyatını giriniz"}
                            disabled={ispProductLoading}
                        />
                    </Col>
                    <Col span={12}>
                        <AddFormInput
                            title={"Üretici"}
                            name={"producer"}
                            error={productErrors.producerError}
                            placeholder={"Ürünün üretici firma ismini giriniz"}
                            disabled={ispProductLoading}
                        />
                        <AddFormInput
                            title={"Manufacturer city"}
                            name={"city"}
                            error={productErrors.cityError}
                            placeholder={"Şehir giriniz"}
                            disabled={ispProductLoading}
                        />
                        <Upload name={"file"} onChange={handleUpload} beforeUpload={() => false}>
                            <Button icon={<UploadOutlined />} style={{ marginTop: 22 }}>
                                Yüklemek için tıklayınız
                            </Button>
                        </Upload>
                    </Col>
                </Row>
                <IconButton title={"Ekle"} icon={<PlusSquareFilled />} />
            </Form>
        </>
    );
};

export default AddProduct;
