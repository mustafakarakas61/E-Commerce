import React, { FC, ReactElement } from "react";
import { FacebookOutlined, LinkedinOutlined, TwitterOutlined } from "@ant-design/icons";
import { Col, Row, Typography } from "antd";

import "./Footer.scss";

const Footer: FC = (): ReactElement => {
    return (
        <div className={"footer-wrapper"}>
            <Row >
                <Col span={12}>
                    <Typography.Title level={3}>BringToMe Ticaret</Typography.Title>
                    <Typography.Text>(536) 650-11-39</Typography.Text>
                    <Typography.Text className={"mt-12"}>12:00-20:00 arası müsaittir, haftasonu telefonlarımız açıktır </Typography.Text>
                </Col>
                <Col span={12} >
                    <div className={"footer-wrapper-social"}>
                        <Typography.Title level={3}>Sosyal Ağlar</Typography.Title>
                        <a href="https://tr.linkedin.com/in/doga-kupa-9a00341b9/tr">
                            <LinkedinOutlined />
                        </a>
                        <a href="#">
                            <FacebookOutlined />
                        </a>
                        <a href="#">
                            <TwitterOutlined />
                        </a>
                    </div>
                </Col>
            </Row>
        </div>
    );
};

export default Footer;
