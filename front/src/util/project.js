import request from './request';


export default {
    getMenu, getCarousel, getVideo,
    logIn, regester, confirmCode, refind,
    checkJwt, changePassword, changeUserType,
    getPlayLineData, search, getDescrip,
    getMonthPlayData, getYearPlayData, getAllPlayData
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

export function changeUserType(params) {
    return request({
        methods:'GET',
        url:'/user/changeUserType',
        params: params
    })
}

export function changePassword(params) {
    return request({
        methods:'GET',
        url:'/user/changePassword',
        params: params
    })
}

export function getPlayLineData() {
    return request({
        methods:'GET',
        url:'/data/getPlayLineData',
    })
}

export function search(params) {
    return request({
        methods : 'GET',
        url: '/search/search',
        params : params
    })
}

export function getDescrip(params) {
    return request({
        methods : 'GET',
        url: '/search/getDescrip',
        params : params
    })
}

export function getAllPlayData() {
    return request({
        methods : 'GET',
        url: '/data/getAllPlayData'
    })
}

export function getYearPlayData(params) {
    return request({
        methods : 'GET',
        url: '/data/getYearPlayData',
        params : params
    })
}

export function getMonthPlayData() {
    return request({
        methods : 'GET',
        url: '/data/getMonthPlayData'
    })
}