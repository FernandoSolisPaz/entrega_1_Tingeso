import httpClient from '../http-common';

const getTypeRepairReport = () => {
    return httpClient.get('/api/report_type_repair/')
}
const createTypeRepairReport = () => {
    return httpClient.get('/api/report_type_repair/calculate')
}

export default { getTypeRepairReport, createTypeRepairReport }