import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/notifications/";

const getNotifications = () => {
    return axios.get(API_URL + "getnotifications", { headers: authHeader() });
};

const getSentNotifications = () => {
    return axios.get(API_URL + "getsentnotifications", { headers: authHeader() });
};
const setSeen = (notificationId) => {
    return axios.post(API_URL + "setseen", {
        id:notificationId
    },{headers: authHeader()});
};

const removeNotification = (notificationId) => {
    return axios.post(API_URL + "remove", {
        id:notificationId
    },{headers: authHeader()});
};


export default {
    getNotifications,
    getSentNotifications,
    setSeen,
    removeNotification

};




