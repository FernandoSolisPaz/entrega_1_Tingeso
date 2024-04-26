import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/api/repairs/');
}
const get = repairId => {
    return httpClient.get(`/api/repairs/${repairId}`);
}

const getByMotorAndName = (motorId, repairName ) => {
    return httpClient.get(`/api/repairs/byMotorAndName/${motorId}/${repairName}`)
}

const create = data => {
    return httpClient.post('/api/repairs/', data);
}

const update = data => {
    return httpClient.put('/api/repairs/', data);
}

const remove = repairId => {
    return httpClient.delete(`/api/repairs/${repairId}`)
}

export default {getAll, get, getByMotorAndName, create, update, remove};