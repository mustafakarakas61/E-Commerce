import React, { FC, memo, ReactElement } from "react";
import { CloseOutlined } from "@ant-design/icons";
import { Button } from "antd";

type PropsType = {
    productId: number;
    deleteFromCart: (productId: number) => void;
};

const RemoveButton: FC<PropsType> = memo(({ productId, deleteFromCart }): ReactElement => {

    return (
        <Button onClick={() => deleteFromCart(productId)} icon={<CloseOutlined />}>
            Kaldır
        </Button>
    );
});

export default RemoveButton;
