import request from './request';

export default {
    getMenu, getCarousel
}

export function getMenu() {
    return request({
        methods:'GET',
        url:'/index/getMenu',
    })
}

export function getCarousel() {
    return request({
        methods:'GET',
        url:'/index/getCarousel',
    })
}
