import {signup} from "../utils"
import {Button, Form, Input, message, Modal} from "antd"
import React from "react";
import {LockOutlined, UserOutlined} from "@ant-design/icons";
class SignupForm extends React.Component{
    state = {
        displayModel: false,
    };

    handleCancle = () => {
        this.setState({
            displayModel: false,
        });
    };

    signupOnClick = () => {
        this.setState({
            displayModel: true,
        });
    }

    onFinish = (data) => {
        signup(data)
            .then(() => {
                this.setState({
                    displayModel: false,
                });
                message.success(`Successfully signed up`);
            })
            .catch((err) => {
                message.error(err.message);
            });
    };


    render() { // render 函数用于渲染组件的内容
        return(
            <>
                <Button shape = "round" type="primary" onClick={this.signupOnClick}>
                    Register
                </Button>
                <Modal
                    title="Register"
                    visible={this.state.displayModel}
                    onCancel={this.handleCancle}
                    footer={null}
                    destroyOnClose={true}
                >
                    <Form
                        name="normal_register"
                        initialValues={{remember: true}}
                        onFinish={this.onFinish}
                        preserve={false}
                    >
                        <Form.Item
                            name="email"
                            rules={[{ required: true, message: 'Please input your email!' }]}
                            >
                            <Input prefix={<UserOutlined />} placeholder="Email" />
                        </Form.Item>
                        <Form.Item
                            name="password"
                            rules={[{
                                required: true,
                                message: 'Please input your password!',
                            }]}
                        >
                            <Input prefix={<LockOutlined />} type="password" placeholder="Password" />
                        </Form.Item>
                        <Form.Item
                            name="firstName"
                            rules={[{
                                required: true,
                                message: 'Please input your first name!' ,
                            }]}
                        >
                            <Input placeholder="first Name" />
                        </Form.Item>
                        <Form.Item
                            name="lastName"
                            rules={[
                                {  required: true,
                                    message: 'Please input your last name!' ,
                                }
                                ]}
                        >
                            <Input placeholder="last Name" />
                        </Form.Item>

                        <Form.Item>
                            <Button type="primary" htmlType="submit">
                                Register
                            </Button>
                        </Form.Item>
                    </Form>
                </Modal>
            </>
        );
    };
}

export default SignupForm;