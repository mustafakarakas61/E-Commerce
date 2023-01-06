import React, { FC, ReactElement, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Alert, Col, Form, Row } from "antd";
import { KeyOutlined, UndoOutlined } from "@ant-design/icons";

import { selectSuccessMessage, selectUserResetPasswordErrors } from "../../../redux-toolkit/user/user-selector";
import { resetInputForm } from "../../../redux-toolkit/user/user-slice";
import ContentTitle from "../../../components/ContentTitle/ContentTitle";
import FormInput from "../../../components/FormInput/FormInput";
import IconButton from "../../../components/IconButton/IconButton";
import { updateUserPassword } from "../../../redux-toolkit/user/user-thunks";

const ChangePassword: FC = (): ReactElement => {
    const dispatch = useDispatch();
    const [form] = Form.useForm();
    const errors = useSelector(selectUserResetPasswordErrors);
    const successMessage = useSelector(selectSuccessMessage);
    const { passwordError, password2Error } = errors;

    useEffect(() => {
        dispatch(resetInputForm());
    }, []);

    useEffect(() => {
        if (successMessage) {
            form.resetFields();
        }
    }, [successMessage]);

    const onFormSubmit = (data: { password: string; password2: string }): void => {
        dispatch(updateUserPassword({ ...data }));
    };

    return (
        <>
            <ContentTitle title={"Şifreyi değiştir"} titleLevel={4} icon={<KeyOutlined />} />
            <Form onFinish={onFormSubmit} form={form}>
                <Row>
                    <Col span={12}>
                        {successMessage && (
                            <Alert type="success" message={successMessage} style={{ marginBottom: 16 }} />
                        )}
                        <FormInput
                            title={"Bir şifre giriniz"}
                            titleSpan={10}
                            wrapperSpan={14}
                            name={"password"}
                            error={passwordError}
                            placeholder={"Şifre"}
                            inputPassword
                        />
                        <FormInput
                            title={"Şifreyi onaylayınız"}
                            titleSpan={10}
                            wrapperSpan={14}
                            name={"password2"}
                            error={password2Error}
                            placeholder={"Şifre"}
                            inputPassword
                        />
                        <IconButton title={"Değiştir"} icon={<UndoOutlined />} />
                    </Col>
                </Row>
            </Form>
        </>
    );
};

export default ChangePassword;
