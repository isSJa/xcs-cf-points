import axios from "axios";
axios.defaults.headers["Content-Type"] = "application/json ; charset=utf-8"
const http=axios.create({
    baseURL:'http://localhost:8312',
    timeout:5000,
})
export default http