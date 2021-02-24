import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/friendship/";

const getFriends = () => {
    return axios.get(API_URL + "myfriendships", { headers: authHeader() });
};

const deleteFriend = (userId) => {
    return axios.post(API_URL + "delete", {
        userId
    },{headers: authHeader()});
};

const getUserBoard = () => {
    return axios.get(API_URL + "user", { headers: authHeader() });
};

export default {
    getFriends,
    deleteFriend
};