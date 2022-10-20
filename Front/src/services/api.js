import axios from "axios";

const api = axios.create({
    baseURL:"http://localhost:8080/",
})

api.defaults.headers.post['Access-Control-Allow-Origin'] = '*';

//Criar aqui as requisições para o backend
export default api;