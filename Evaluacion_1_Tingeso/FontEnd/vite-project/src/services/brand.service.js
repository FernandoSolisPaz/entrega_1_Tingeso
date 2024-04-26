import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/api/car_brand/');
}
const get = brandId => {
    return httpClient.get(`/api/car_brand/${brandId}`);
}

const create = data => {
    return httpClient.post('/api/car_brand/', data);
}

const update = data => {
    return httpClient.put('/api/car_brand/', data);
}

const remove = brandId => {
    return httpClient.delete(`/api/car_brand/${brandId}`)
}

export default {getAll, get, create, update, remove};