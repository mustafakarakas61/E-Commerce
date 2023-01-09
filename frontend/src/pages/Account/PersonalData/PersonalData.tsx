import React, { FC, ReactElement, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Button, Col, Form, Row } from "antd";
import { CheckOutlined, EditOutlined, EyeInvisibleOutlined, ProfileOutlined } from "@ant-design/icons";

import { selectUserEditErrors, selectUserFromUserState } from "../../../redux-toolkit/user/user-selector";
import ContentTitle from "../../../components/ContentTitle/ContentTitle";
import AccountDataItem from "../../../components/AccountDataItem/AccountDataItem";
import FormInput from "../../../components/FormInput/FormInput";
import IconButton from "../../../components/IconButton/IconButton";
import { updateUserInfo } from "../../../redux-toolkit/user/user-thunks";
import { resetInputForm } from "../../../redux-toolkit/user/user-slice";

interface PersonalData {
    email: string;
    firstName: string;
    lastName: string;
    city: string;
    address: string;
    phoneNumber: string;
    postIndex: string;
}

const PersonalData: FC = (): ReactElement => {
    const dispatch = useDispatch();
    const [form] = Form.useForm();
    const usersData = useSelector(selectUserFromUserState);
    const errors = useSelector(selectUserEditErrors);
    const [showUserData, setShowUserData] = useState<boolean>(false);
    const { firstNameError, lastNameError } = errors;

    const onClickShowUserData = (): void => {
        setShowUserData((prevState) => !prevState);
    };

    useEffect(() => {
        dispatch(resetInputForm());

        if (usersData) {
            form.setFieldsValue(usersData);
        }
    }, []);

    const onFormSubmit = (data: PersonalData): void => {
        dispatch(updateUserInfo({ id: usersData?.id, ...data }));
    };

    return (
        <>
            <ContentTitle title={"Hesap Bilgileri"} titleLevel={4} icon={<ProfileOutlined />} />
            <Row>
                <Col span={12}>
                    <AccountDataItem title={"Email"} text={usersData?.email} />
                    <AccountDataItem title={"Ad"} text={usersData?.firstName} />
                    <AccountDataItem title={"Soyad"} text={usersData?.lastName} />
                    <AccountDataItem title={"Şehir"} text={usersData?.city} />
                    <AccountDataItem title={"Adres"} text={usersData?.address} />
                    <AccountDataItem title={"Telefon"} text={usersData?.phoneNumber} />
                    <AccountDataItem title={"Posta kodu"} text={usersData?.postIndex} />
                    <Button
                        type={"primary"}
                        onClick={onClickShowUserData}
                        icon={showUserData ? <EyeInvisibleOutlined /> : <EditOutlined />}
                    >
                        {showUserData ? "Gizle" : "Düzenle"}
                    </Button>
                </Col>
                <Col span={12}>
                    {showUserData && (
                        <Form onFinish={onFormSubmit} form={form}>

                            <FormInput
                                title={"Email:"}
                                titleSpan={6}
                                wrapperSpan={18}
                                disabled={true}
                                name={"email"}
                                error={firstNameError}
                                placeholder={"Email"}
                            />
                            <FormInput
                                title={"Ad:"}
                                titleSpan={6}
                                wrapperSpan={18}
                                name={"firstName"}
                                error={firstNameError}
                                placeholder={"Ad"}
                            />
                            <FormInput
                                title={"Soyad:"}
                                titleSpan={6}
                                wrapperSpan={18}
                                name={"lastName"}
                                error={lastNameError}
                                placeholder={"Soyad"}
                            />
                            <FormInput
                                title={"Şehir:"}
                                titleSpan={6}
                                wrapperSpan={18}
                                name={"city"}
                                placeholder={"Şehir"}
                            />
                            <FormInput
                                title={"Adres:"}
                                titleSpan={6}
                                wrapperSpan={18}
                                name={"address"}
                                placeholder={"Adres"}
                            />
                            <FormInput
                                title={"Telefon:"}
                                titleSpan={6}
                                wrapperSpan={18}
                                name={"phoneNumber"}
                                placeholder={"Telefon"}
                            />
                            <FormInput
                                title={"Posta kodu:"}
                                titleSpan={6}
                                wrapperSpan={18}
                                name={"postIndex"}
                                placeholder={"Posta kodu"}
                            />
                            <IconButton title={"Kaydet"} icon={<CheckOutlined />} />
                        </Form>
                    )}
                </Col>
            </Row>
        </>
    );
};

export default PersonalData;
