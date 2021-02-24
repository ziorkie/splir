import axios from "axios";

const API_URL = "http://localhost:8080/auth/";

const register = (username, password, email, name, surname, phoneNumber, accountNumber) => {
    return axios.post(API_URL + "signup", {
        username,
        password,
        email,
        name,
        surname,
        phoneNumber,
        accountNumber
    });
};

const login = (usernameOrEmail, password) => {
    return axios
        .post(API_URL + "signin", {
            usernameOrEmail,
            password,
        })
        .then((response) => {
            if (response.data.accessToken) {
                localStorage.setItem("user", JSON.stringify(response.data));
            }

            return response.data;
        });
};

const logout = () => {
    localStorage.removeItem("user");
};

const getCurrentUser = () => {
    return JSON.parse(localStorage.getItem("user"));
};

export default {
    register,
    login,
    logout,
    getCurrentUser,
};