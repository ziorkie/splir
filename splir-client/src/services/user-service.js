import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/user/";

const getPublicContent = () => {
    return axios.get(API_URL + "getall");
};

const getUserBoard = () => {
    return axios.get(API_URL + "me", { headers: authHeader() });
};

const getModeratorBoard = () => {
    return axios.get(API_URL + "mod", { headers: authHeader() });
};

const getAdminBoard = () => {
    return axios.get(API_URL + "admin", { headers: authHeader() });
};

const getUserDetails = (userId) => {
    return axios.post(API_URL + "userdetails", {
        userId:userId
    },{headers: authHeader()});
};

// const getPaymentDetails = (userId) => {
//      return axios.post(API_URL + "paymentdetails", {
//         userId
//     },{headers: authHeader()});
// };

const getPaymentDetails = (userId) => {
    return axios.post(
        API_URL + "paymentdetails",
        { userId:userId },
        { headers: authHeader() }
    );
};


export default {
    getPublicContent,
    getUserBoard,
    getModeratorBoard,
    getAdminBoard,
    getUserDetails,
    getPaymentDetails,

};