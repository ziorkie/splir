import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/invitations/";

const sendInvite = (userId) => {
    return axios.post(API_URL + "send", {
        userId
    },{headers: authHeader()});
};

const mySentInvites = () => {
    return axios.get(API_URL + "mysentinvites", {headers: authHeader()});
};

const myReceivedInvites = () => {
    return axios.get(API_URL + "myreceivedinvites", {headers: authHeader()});
};

const deleteInvite = (userId) => {
    return axios.post(API_URL + "delete", {
        userId
    },{headers: authHeader()});
};

const acceptInvite = (userId) => {
    return axios.post(API_URL + "accept", {
        userId
    },{headers: authHeader()});
};




export default {
    sendInvite,
    mySentInvites,
    myReceivedInvites,
    deleteInvite,
    acceptInvite,

};