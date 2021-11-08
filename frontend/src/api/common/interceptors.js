import store from '@/store/index'

export function setInterceptors(instance){
    instance.interceptors.request.use(
        function(config){
            //console.log(config)
            config.headers.Authorization = `Bearer ${store.state.accessToken}`
            return config;
        },
        function(error){
            return Promise.reject(error);
        }
    );

    instance.interceptors.response.use(
        function(response){
            return response;
        },
        function(error){
            return Promise.reject(error);
        }
    )
    return instance;
}