import http from "@/utils/http";

//添加分数
export const addScore = (json: string) => {
    return http({
        method:'post',
        data:json,
        url:'/score'
    })
}
//添加用户
export const addUser = (json: string) => {
    return http({
        method: 'post',
        data: json,
        url: '/user'
    })
}
//添加比赛
export const addContest = (json: string) => {
    return http({
        method: 'post',
        data: json,
        url: '/contests'
    })
}

//分页+模糊查询
export const getUserPage = (currentPage: number, pageSize: number, searchValue: string) => {
    return http({
        method: 'get',
        url: '/user/' + currentPage + "/" + pageSize,
        headers: {
            'Content-Type': 'text/plain'
        },
        params: {
            value: searchValue
        }
    })
}
//获取比赛信息
export const getContest=()=>{
    return http({
        method:'get',
        url:'/contests'
    })
}
//获取比赛详情信息（正式比赛时间）
export const getContestDuringDetail=(name:string)=>{
    return http({
        method:'get',
        url:'/contests/during',
        headers: {
            'Content-Type': 'text/plain'
        },
        params: {
            name: name
        }
    })
}
//获取比赛详情信息（赛后补题）
export const getContestAfterDetail=(name:string)=>{
    return http({
        method:'get',
        url:'/contests/after',
        headers: {
            'Content-Type': 'text/plain'
        },
        params: {
            name: name
        }
    })
}