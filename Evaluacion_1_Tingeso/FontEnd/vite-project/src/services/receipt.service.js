import httpClient from "../http-common";

const getAll = () => {
    return httpClient.get('/api/receipts/');
}

const get = id => {
    return httpClient.get(`/api/receipts/${id}`)
}

const getByCarPlate = plate => {
    return httpClient.get(`/api/receipts/byPlate/${plate}`);
}

const create = (data, repairIds ) => {
    const repairIdsString = repairIds.join(',');

    const url = `/api/receipts/?repairIds=${repairIdsString}`;
    return httpClient.post(url, data);
}

const update = data => {
    return httpClient.put('/api/receipts/', data);
}

const updateOutDate = (id, workshopOutDate, workshopOutHour) => {
    return httpClient.put(`/api/receipts/modify_out_date/${id}?workshopOutDate=${workshopOutDate}&workshopOutHour=${workshopOutHour}`);
}

const updatePickUpDate = (id, pickUpDate, pickUpHour) => {
    return httpClient.put(`/api/receipts/modify_pickUp_date/${id}?pickUpDate=${pickUpDate}&pickUpHour=${pickUpHour}`);
}

const remove = id => {
    return httpClient.delete(`/api/receipts/${id}`);
}

export default  {getAll, get, getByCarPlate, create, update, updateOutDate, updatePickUpDate, remove};