import React, { FC, ReactElement } from "react";
import { Link } from "react-router-dom";
import { Col, Row } from "antd";

import { MENU } from "../../../constants/routeConstants";
import "./HomePageTheme.css";

const HomePageTheme: FC = (): ReactElement => {
    return (
        <div className={"page-theme"}>
            <Row gutter={32}>
                <Col span={12}>
                    <Link to={{ pathname: MENU, state: { id: "Elektronik" } }}>
                        <img src="https://i.ibb.co/jZvW29Q/teknoloji.jpg" alt={"Elektronik"} />
                    </Link>
                </Col>
                <Col span={12}>
                    <Link to={{ pathname: MENU, state: { id: "Ev ve Yaşam" } }}>
                        <img src="https://i.ibb.co/7Vx3mvD/evveyasam.jpg" alt={"Ev ve Yaşam"} />
                    </Link>
                </Col>
            </Row>
        </div>
    );
};

export default HomePageTheme;
