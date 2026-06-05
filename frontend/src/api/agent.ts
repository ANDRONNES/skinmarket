import axios from "axios"

const API_URL = "http://localhost:8080/api"

const agent = axios.create({
    baseURL: API_URL,
    headers: {
        "Content-Type" : "application/json"
    }
});

agent.interceptors.response.use(
    (response) => response,
    (error) => {
        const errorMessage = error.response?.data?.message || "Something went wrong";
        console.error("API error occured", errorMessage);
        return Promise.reject(error);
    }
)

export default agent;