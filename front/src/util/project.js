import request from './request';


export default {
    getMenu, getCarousel, getVideo
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

export function getVideo(params) {
    return request({
        methods:'GET',
        url:'/play/getVideo',
        params: params
    })
}
