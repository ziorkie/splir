import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/solo/";

const getExpenses = () => {
    return axios.get(API_URL + "getexpenses", { headers: authHeader() });
};

const addExpense = (expenseName, expenseValue, localDate, isCyclic) =>{
    return axios.post(API_URL + "addexpense", {
        expenseName,
        expenseValue,
        localDate,
        isCyclic
    },{headers: authHeader()});
};

export default {
    getExpenses,
    addExpense,

};