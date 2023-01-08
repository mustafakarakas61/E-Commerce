import React, { FC, ReactElement } from "react";
import { FacebookOutlined, LinkedinOutlined, TwitterOutlined } from "@ant-design/icons";
import { Col, Row, Typography } from "antd";

import "./Footer.scss";

const Footer: FC = (): ReactElement => {
    return (
        <div className={"footer-wrapper"}>
            <Row >
                <Col span={12}>
                    <Typography.Title level={3}>Bring To Me Incorporated</Typography.Title>
                    <Typography.Text>(850) 661-34-61</Typography.Text>
                </Col>
                <Col span={12} >
                    <div className={"footer-wrapper-social"}>
                        <Typography.Title level={3}>Sosyal Ağlar</Typography.Title>
                        <a href="https://www.linkedin.com/in/bring-to-me-incorporated-297352261/">
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
