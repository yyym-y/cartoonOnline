import request from './request';

export default {
    changeEpisode
}

export function changeEpisode(data) {
    return request({
        method:'POST',
        url:'/admin/changeEpisode',
        data : data
    })
}