import axios from 'axios';
const SERVER_URL = process.env.VUE_APP_SERVER_URL

const token = localStorage.getItem('token')
const config = {
    Authorization: `Bearer ${token}`
}
function getProfileInfo(){
    return axios({
        method:'get',
        url: `${SERVER_URL}/users/me`,
        headers: config
    })
}

export {
    getProfileInfo
}