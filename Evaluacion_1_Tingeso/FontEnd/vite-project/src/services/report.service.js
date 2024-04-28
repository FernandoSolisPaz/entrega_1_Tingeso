import httpClient from '../http-common';

const getTypeRepairReport = () => {
    return httpClient.get('/api/report_type_repair/')
}

const createTypeRepairReport = () => {
    return httpClient.get('/api/report_type_repair/calculate')
}

const getMotorRepairReport = () => {
    return httpClient.get('/api/report_motor_repair/')
}

const createMotorRepairReport = () => {
    return httpClient.get('/api/report_motor_repair/calculate')
}

export default { getTypeRepairReport, createTypeRepairReport, getMotorRepairReport, createMotorRepairReport }