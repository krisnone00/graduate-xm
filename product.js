import request from '@/utils/request'

// 1. 分页查询商品列表
export function getProductPage(params) {
    return request.get('/product/page', { params })
}

// 2. 新增或保存商品 (新增和修改都用这个，因为你的 Controller 是 save)
export function saveProduct(data) {
    return request.post('/product/save', data)
}

// 3. 删除商品
export function deleteProduct(id) {
    return request.delete(`/product/${id}`)
}