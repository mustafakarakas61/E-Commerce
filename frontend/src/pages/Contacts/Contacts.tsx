import React, {FC, ReactElement, useEffect} from "react";
import { Col, Row, Typography } from "antd";
import { InfoCircleOutlined } from "@ant-design/icons";

import ContentWrapper from "../../components/ContentWrapper/ContentWrapper";
import ContentTitle from "../../components/ContentTitle/ContentTitle";

const Contacts: FC = (): ReactElement => {

    useEffect(() => {
        window.scrollTo(0, 0);
    }, []);
    
    return (
        <ContentWrapper>
            <ContentTitle icon={<InfoCircleOutlined />} title={"İletişim"} />
            <Row gutter={32}>
                <Col span={12}>
                    <div>
                        <Typography.Title >{"Ahmet Doğa Kupa"}</Typography.Title>
                        <Typography.Text strong>{"Telefon: "}</Typography.Text>
                        <Typography.Text>(551) 049-29-74</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text strong>{"E-mail: "}</Typography.Text>
                        <Typography.Text>2170656045@nku.edu.tr</Typography.Text>
                    </div>
                    <div style={{ marginTop: 16 }}>
                        <Typography.Text strong>Müsait Olma zamanları</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text>
                            11:00 - 12:00 zamanları arası telefonlar açıktır <br />
                        </Typography.Text>
                    </div>
                    <div>
                        <Typography.Title >{"Batuhan Yalçıntürk"}</Typography.Title>
                        <Typography.Text strong>{"Telefon: "}</Typography.Text>
                        <Typography.Text>(551) 052-87-69</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text strong>{"E-mail: "}</Typography.Text>
                        <Typography.Text>1190606046@nku.edu.tr</Typography.Text>
                    </div>
                    <div style={{ marginTop: 16 }}>
                        <Typography.Text strong>Müsait Olma zamanları</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text>
                            13:30 - 16:30 zamanları arası telefonlar açıktır <br />
                        </Typography.Text>
                    </div>


                    <div>
                        <Typography.Title >{"Mustafa Karakaş"}</Typography.Title>
                        <Typography.Text strong>{"Telefon: "}</Typography.Text>
                        <Typography.Text>(538) 279-63-69</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text strong>{"E-mail: "}</Typography.Text>
                        <Typography.Text>1190606048@nku.edu.tr</Typography.Text>
                    </div>
                    <div style={{ marginTop: 16 }}>
                        <Typography.Text strong>Müsait Olma zamanları</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text>
                            8:30 - 17:00 zamanları arası telefonlar açıktır <br />
                        </Typography.Text>
                    </div>

                    <div>
                        <Typography.Title >{"Rauf Osman Çayan"}</Typography.Title>
                        <Typography.Text strong>{"Telefon: "}</Typography.Text>
                        <Typography.Text>(507) 186-01-57</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text strong>{"E-mail: "}</Typography.Text>
                        <Typography.Text>1190606049@nku.edu.tr</Typography.Text>
                    </div>
                    <div style={{ marginTop: 16 }}>
                        <Typography.Text strong>Müsait Olma zamanları</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text>
                            10:00 - 18:00 zamanları arası telefonlar açıktır <br />
                        </Typography.Text>
                    </div>
                    <div style={{ marginTop: 16 }}>
                        <Typography.Text strong>Sipariş teslimi: </Typography.Text>
                        <Typography.Text>
                            Siparişleriniz kurye aracılığıyla ücreti verildikten en kısa zamanda siza ulaştırılıcaktır <br />
                        </Typography.Text>
                    </div>

                </Col>
            </Row>

        </ContentWrapper>
    );
};

export default Contacts;
