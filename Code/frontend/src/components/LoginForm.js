import {login} from "../utils";
import {Button, Form, Input, message} from "antd";
import {UserOutlined} from "@ant-design/icons";
import React from "react";


class LoginForm extends React.Component{
    // React.Component 是 React 的基类，用于定义组件类
    state = {
        // state 是组件内部的状态管理机制，用于存储组件内部的状态
        // 通过 this.state 访问
        loading: false,
        // loading 用于标识当前是否正在登录中
        // 通过 this.state.loading 访问
        // false 表示当前没有正在登录中的请求
    };

    onFinish = (data) => {
        // onFinish 是 Form 组件的回调函数，当用户点击登录按钮时，会调用 onFinish 函数
        // onFinish 函数的参数 data 是一个对象，包含了用户输入的用户名和密码
        this.setState({
            // setState 是 React 组件的内置方法，用于更新组件的 state
            // 通过 this.setState 更新 state
            loading: true, // 设置 loading 为 true，表示当前正在登录中
        });
        // 调用 login 函数，向服务器发送登录请求
        login(data)
            .then((res) => { // 登录成功
                message.success(`Login Successful`);
                this.props.onSuccess(); // 调用父组件传递过来的 onSuccess 函数
                // onSuccess 函数用于更新 App 组件的 state，将 isLogin 设置为 true
                // 从而触发 App 组件的 componentDidUpdate 函数
                // props 是组件的属性，通过 this.props 访问
            })
            .catch((err) => { // 登录失败
                message.error(`err.message`);
                // message 是 antd 组件库的消息提示组件
            })
            .finally(() => {
                // 无论登录成功还是失败，loading 都应该设置为 false
                this.setState({
                    // setState 是 React 组件的内置方法，用于更新组件的 state
                    // 通过 this.setState 更新 state
                    loading: false, // 设置 loading 为 false，表示当前没有正在登录中
                });
            });
    };

    render() { // render 函数用于渲染组件的内容
        return(
            <Form // Form 是 antd 组件库的表单组件
                name="normal_login" // 表单的名称
                onFinish={this.onFinish} // 表单提交的回调函数
                style={{ // 表单的样式
                    width: 300,
                    margin: "auto", // 居中显示
                }}
            >
                <Form.Item
                    name="username"
                    rules={[{
                        required: true,
                        message: "Please input your Username!",
                    }]}// 表单验证规则 rules
                    // rules 是一个数组，数组中包含了多个表单验证规则
                    // required 表示该表单项是必填项
                >
                    <Input prefix={<UserOutlined />} placeholder="Username" />
                    {/*// Input 是 antd 组件库的输入框组件*/}
                    {/*// prefix 表示输入框前面的图标*/}
                    {/*// placeholder 表示输入框的提示文字*/}
                    {/*// <UserOutlined /> 是 antd 组件库的图标组件*/}

                </Form.Item>

                <Form.Item
                    name="password"
                    rules={[{ required: true,
                        message: "Please input your Password!"
                    }]}
                >
                    <Input prefix={<UserOutlined />} type="password" placeholder="Password" />
                </Form.Item>

                <Form.Item>
                    <Button type="primary" htmlType="submit"
                        loading={this.state.loading}
                    >
                        Login
                    </Button>
                </Form.Item>
            </Form>
        );
    };
}

export default LoginForm;