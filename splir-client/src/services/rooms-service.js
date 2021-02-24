import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/rooms/";

const getRooms = () => {
    return axios.get(API_URL + "getrooms", { headers: authHeader() });
};

const createRoom = (name) => {
    return axios.post(API_URL + "create", {
        name
    },{headers: authHeader()});
};

const closeRoom = (roomId) => {
    return axios.post(API_URL + "close", {
        roomId
    },{headers: authHeader()});
};


const getUserBoard = () => {
    return axios.get(API_URL + "user", { headers: authHeader() });
};

const getRoomExpenses = (roomId) => {
    return axios.post(API_URL + "roomexpenses", {
        roomId
    },{headers: authHeader()});
};

const getExpensePerUser = (roomId) => {
    return axios.post(API_URL + "getexpenseperuser", {
        roomId
    },{headers: authHeader()});
};
const getRoomUsers = (roomId) => {
    return axios.post(API_URL + "getroomusers", {
        roomId
    },{headers: authHeader()});
};


const addUserToRoom = (userId, roomId) => {
    return axios.post(API_URL + "adduser", {
        userId,
        roomId
    },{headers: authHeader()});
};



const addRoomExpense = (name, value, roomId) =>{
    return axios.post(API_URL + "addexpense", {
        name,
        value,
        roomId
    },{headers: authHeader()});
};

export default {
    getRooms,
    createRoom,
    closeRoom,
    getRoomExpenses,
    addRoomExpense,
    getExpensePerUser,
    addUserToRoom,
    getRoomUsers
};