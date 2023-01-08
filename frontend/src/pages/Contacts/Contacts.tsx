import React, {FC, ReactElement, useEffect} from "react";
import {Button, Col, Form, Input, Row, Typography} from "antd";
import {InfoCircleOutlined, SendOutlined} from "@ant-design/icons";

import ContentWrapper from "../../components/ContentWrapper/ContentWrapper";
import ContentTitle from "../../components/ContentTitle/ContentTitle";

const EmailForm = () => {
    const [form] = Form.useForm();

    const handleSubmit = () => {
        // Form verilerini al
        const values = form.getFieldsValue();

        // E-mail yollama işlemleri burada yapılacak
        window.location.href = `mailto:${values.email}?subject=${values.subject}&body=${values.message}`;
    };

    return (
        <Form form={form}>
            <Form.Item name="email" label="E-mail" rules={[{ required: true }]}>
                <Input />
            </Form.Item>
            <Form.Item name="subject" label="Konu" rules={[{ required: true }]}>
                <Input />
            </Form.Item>
            <Form.Item name="message" label="Mesaj" rules={[{ required: true }]}>
                <Input.TextArea rows={4} />
            </Form.Item>
            <Form.Item>
                <Button type="primary" onClick={handleSubmit}>
                    {<SendOutlined />} Gönder
                </Button>
            </Form.Item>
        </Form>
    );
};

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
                        <Typography.Text>+90 (551) 049-29-74</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text strong>{"E-mail: "}</Typography.Text>
                        <Typography.Text>2170656045@nku.edu.tr</Typography.Text>
                    </div>
                    <div>
                        <Typography.Title >{"Batuhan Yalçıntürk"}</Typography.Title>
                        <Typography.Text strong>{"Telefon: "}</Typography.Text>
                        <Typography.Text>+90 (551) 052-87-69</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text strong>{"E-mail: "}</Typography.Text>
                        <Typography.Text>1190606046@nku.edu.tr</Typography.Text>
                    </div>

                    <div>
                        <Typography.Title >{"Mustafa Karakaş"}</Typography.Title>
                        <Typography.Text strong>{"Telefon: "}</Typography.Text>
                        <Typography.Text>+90 (538) 279-63-69</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text strong>{"E-mail: "}</Typography.Text>
                        <Typography.Text>1190606048@nku.edu.tr</Typography.Text>
                    </div>

                    <div>
                        <Typography.Title >{"Rauf Osman Çayan"}</Typography.Title>
                        <Typography.Text strong>{"Telefon: "}</Typography.Text>
                        <Typography.Text>+90 (507) 186-01-57</Typography.Text>
                    </div>
                    <div>
                        <Typography.Text strong>{"E-mail: "}</Typography.Text>
                        <Typography.Text>1190606049@nku.edu.tr</Typography.Text>
                    </div>
                    <div style={{ marginTop: 16 }}>
                        <Typography.Text strong>Sipariş teslimi: </Typography.Text>
                        <Typography.Text>
                            Siparişleriniz kurye aracılığıyla, ödeme onaylandıktan sonra  <br />en kısa zamanda size ulaştırılacaktır.
                        </Typography.Text>
                    </div>

                </Col>
                <Col span={12}>
                    <ContentTitle title={"Bize yazın"} />
                    <EmailForm />
                </Col>
            </Row>

        </ContentWrapper>
    );
};

export default Contacts;
