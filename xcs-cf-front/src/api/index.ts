import http from "@/utils/http";
import {h} from "vue";

//添加分数
export const addScore = (json: string) => {
    return http({
        method: 'post',
        data: json,
        url: '/score'
    })
}
//添加用户
export const addStudent = (json: string) => {
    return http({
        method: 'post',
        data: json,
        url: '/student'
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
//获取所有社员信息
export const getAllStudent=()=>{
    return http({
        method:'get',
        url:'/student'
    })
}
//分页+模糊查询
export const getStudentPage = (currentPage: number, pageSize: number, searchValue: string) => {
    return http({
        method: 'get',
        url: '/student/' + currentPage + "/" + pageSize,
        headers: {
            'Content-Type': 'text/plain'
        },
        params: {
            value: searchValue
        }
    })
}
//获取比赛信息
export const getContest = () => {
    return http({
        method: 'get',
        url: '/contests'
    })
}
//获取比赛详情信息（正式比赛时间）
export const getContestDuringDetail = (name: string) => {
    return http({
        method: 'get',
        url: '/contests/during',
        headers: {
            'Content-Type': 'text/plain'
        },
        params: {
            name: name
        }
    })
}
//获取比赛详情信息（赛后补题）
export const getContestAfterDetail = (name: string) => {
    return http({
        method: 'get',
        url: '/contests/after',
        headers: {
            'Content-Type': 'text/plain'
        },
        params: {
            name: name
        }
    })
}
//获取某一个用户的所有比赛信息
export const getStudentContests = (id: number) => {
    return http({
        method: 'get',
        url: '/student/contests/' + id
    })
}
//获取所有用户的所有比赛信息
export const getAllStudentContests = () => {
    return http({
        method: 'get',
        url: '/student/contests'
    })
}
//获取用户信息
export const getStudentInfo = (id: number) => {
    return http({
        method: 'get',
        url: '/student/' + id
    })
}
//修改用户信息
export const updateStudent = (json: string) => {
    return http({
        method: 'put',
        url: '/student',
        data: json
    })
}
//注册
export const userRegister = (json: string) => {
    return http({
        method: 'post',
        url: '/user/register',
        data: json
    })
}
//登录
export const userLogin=(json:string)=>{
    return http({
        method:'post',
        url:'/user/login',
        data:json
    })
}
// 删除用户
export const deleteStudent=(id:number)=>{
    return http({
        method:'delete',
        url:'/student/'+id
    })
}
// 更新头像
export const changeAvatar=(file:any)=>{
    return http({
        method:'post',
        url:'/user/upload-avatar',
        params:{
            file:file
        }
    })
}
// 获取用户信息
export const getUser=(id:number)=>{
    return http({
        method:'get',
        url:'/user/'+id
    })
}
// 更新用户信息
export const updateUser=(json:string)=>{
    return http({
        method:'post',
        url:'/user/changeInfo',
        data:json
    })
}
// 修改用户密码
export const updatePwd=(json:string)=>{
    return http({
        method:'post',
        url:'/user/changePwd',
        data:json
    })
}
// 删除比赛
export const removeContest=(id:number)=>{
    return http({
        method:'delete',
        url:'/contests/'+id
    })
}
//修改比赛
export const updateContest=(json:string)=>{
    return http({
        method:'put',
        url:'/contests',
        data:json
    })
}
//验证token
export const checkToken=(token:string)=>{
    return http({
        method:'post',
        url:'/user/checkToken',
        params:{
            token:token
        }
    })
}