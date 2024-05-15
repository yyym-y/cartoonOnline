import request from './request';


export default {
    getMenu, getCarousel, getVideo,
    logIn, regester, confirmCode, refind,
    checkJwt
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

export function logIn(params) {
    return request({
        methods:'GET',
        url:'/user/logIn',
        params: params
    })
}

export function regester(params) {
    return request({
        methods:'GET',
        url:'/user/register',
        params: params
    })
}

export function confirmCode(params) {
    return request({
        methods:'GET',
        url:'/user/confirmCode',
        params: params
    })
}

export function refind(params) {
    return request({
        methods:'GET',
        url:'/user/refind',
        params: params
    })
}

export function checkJwt(params) {
    return request({
        methods:'GET',
        url:'/user/checkJwt',
        params: params
    })
}
