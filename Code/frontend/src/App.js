
import {Layout, Typography} from "antd"
import {useState} from "react"
import LoginForm from "./components/LoginForm"
import FoodList from "./components/FoodList"
import MyCart from "./components/MyCart"
import SignupForm from "./components/SignupForm"


const { Header, Content } = Layout;
//  const 是 ES6 的语法，用于定义变量
// Header, Content 是 antd 组件库的组件
// Layout 是 antd 组件库的布局组件
// 通过 import 语句引入 antd 组件库的组件
const { Title } = Typography;
// Typography 是 antd 组件库的排版组件
// Title 是 antd 组件库的标题组件

function App() {
    const [authed, setAuthed] = useState(false);
    // useState 是 React 的内置 Hook 函数，用于定义组件的 state
    // authed 是 state 的名称，用于存储用户的登录状态
    // setAuthed 是用于更新 authed 的函数
    // useState(false) 表示 authed 的初始值为 false
    return (
        <Layout style={{ minHeight: '100vh', backgroundColor: 'black'}}>
            <Header style={{ backgroundColor: 'black'}}>
                <div className="header">
                    <Title level={2}
                           style={{color: "white", lineHeight: "inherit", marginBottom: 0}}
                    >
                        Eve Restaurant Order
                    </Title>
                    <div>{authed ? <MyCart /> : <SignupForm />}</div>
                </div>
            </Header>
            <Content
                style={{
                    padding: '50px',
                    maxHeight: "calc(100% - 64px)",
                    overflowY: "auto",
                }}
            >
                {
                    authed ?
                        (<FoodList />)
                        :
                        (<LoginForm onSuccess={() => setAuthed(true)}/> )
                }
            </Content>
      </Layout>
    );
}

export default App;
