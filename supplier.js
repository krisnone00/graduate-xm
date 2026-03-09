import request from '@/utils/request'

export function getSupplierPage(params) {
    return request.get('/supplier/page', { params })
}

export function getSupplierList() {
    return request.get('/supplier/list')
}

export function saveSupplier(data) {
    return request.post('/supplier', data)
}

export function deleteSupplier(id) {
    return request.delete(`/supplier/${id}`)
}