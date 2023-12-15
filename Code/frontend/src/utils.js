export const login = (credential) => {
    // login 是一个函数，接受一个参数 credential
    // credential 是一个对象，包含 username 和 password 两个属性
    // 例如：{ username: 'Jerry', password: '123' }
    const loginUrl = `/login?username=${credential.username}&password=${credential.password}`;
    // loginUrl 是一个字符串，例如：'/login?username=Jerry&password=123'
    // 请注意，这里的 username 和 password 都是从 credential 对象中取出的

    return fetch(loginUrl, {
        // fetch 是浏览器提供的一个 API，用于发起 HTTP 请求
        method: 'POST', // HTTP 请求的方法是 POST
        headers: { // HTTP 请求的头部信息
            'Content-Type': 'application/json', // HTTP 请求的内容类型是 JSON
        },
        credentials: 'include', // fetch 默认不会发送 Cookie，需要设置 credentials 为 'include'
        // 请注意，这里的 body 是一个对象，需要转换成 JSON 字符串
    }).then((response) => {
        // fetch 的返回值是一个 Promise 对象
        if (response.status < 200 || response.status >= 300) {
            // 如果 HTTP 响应的状态码不是 2xx，就表示请求失败
            throw Error("Fail to log in");
        }
    });
};

export const signup = (data) => {
    // signup 是一个函数，接受一个参数 data
    // data 是一个对象，包含 username 和 password 两个属性
    const signupUrl = '/signup';
    // signupUrl 是一个字符串，表示注册的 API 接口
    // 例如：'/signup'

    return fetch(signupUrl, { // 使用 fetch 发起 HTTP 请求
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    }).then((response) => {
        if (response.status < 200 || response.status >= 300) {
            throw Error("Fail to sign up");
        }
    });
};

export const getMenus = (restId) => {
    return fetch(`/restaurant/${restId}/menu`).then((response) => {
        if (response.status < 200 || response.status >= 300) {
            throw Error("Fail to get menus");
        }
        return response.json();
    });
};

export const getRestaurants = () => {
    return fetch("/restaurants").then((response) => {
        if (response.status < 200 || response.status >= 300) {
            throw Error("Fail to get restaurants");
        }
        return response.json();
    });
};

export const getCart = () => {
    return fetch("/cart").then((response) => {
        if (response.status < 200 || response.status >= 300) {
            throw Error("Fail to get shopping cart data");
        }
        return response.json();
    });
};

export const checkout = () => {
    return fetch("/checkout").then((response) => {
        if (response.status < 200 || response.status >= 300) {
            throw Error("Fail to checkout");
        }
    });
};

export const addItemToCart = (itemId) => {
    return fetch(`/order/${itemId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        credentials: 'include',
    }).then((response) =>{
        if (response.status < 200 || response.status >= 300) {
            throw Error("Fail to add item to cart");
        }
    });
};

