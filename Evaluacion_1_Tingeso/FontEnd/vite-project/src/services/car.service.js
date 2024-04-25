import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/api/cars/');
}

const create = data => {
    return httpClient.post('/api/cars/', data);
}

const update = data => {
    return httpClient.put('/api/cars/', data);
}

const remove = carPlate => {
    return httpClient.delete(`/api/cars/${carPlate}`);
}

export default  {getAll, create, update, remove};