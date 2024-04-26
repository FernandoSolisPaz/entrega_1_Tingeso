import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/api/receipts/');
}

const get = id => {
    return httpClient.get(`/api/receipts/${id}`)
}

const create = (data, repairIds ) => {
    const repairIdsString = repairIds.join(',');

    const url = `/api/receipts/?repairIds=${repairIdsString}`;
    return httpClient.post(url, data);
}

const update = data => {
    return httpClient.put('/api/receipts/', data);
}

const remove = id => {
    return httpClient.delete(`/api/receipts/${id}`);
}

export default  {getAll, get, create, update, remove};